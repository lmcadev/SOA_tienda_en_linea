package com.empresa.plataforma.puertaenlace.seguridad;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.Arrays;

/**
 * Filtro global para validar tokens JWT en la puerta de enlace.
 */
@Component
public class FiltroJwt implements GlobalFilter, Ordered {

  private static final Logger log = LoggerFactory.getLogger(FiltroJwt.class);

  @Value("${jwt.secret:mi_super_secreto_para_pruebas}")
  private String secretoJwt;
  /**
   * Lista de rutas que se excluyen de la validación JWT.
   */
  private static final List<String> rutasExcluidas = Arrays.asList(
      "/auth", "/auth/", "/auth/login", "/swagger-ui", "/swagger-ui/", "/v3/api-docs", "/v3/api-docs/"
  );


  @Override
  public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
    String path = exchange.getRequest().getURI().getPath();
    // Permitir rutas excluidas sin validación
    for (String ex : rutasExcluidas) {
      if (path.startsWith(ex)) {
        return chain.filter(exchange);
      }
    }

    List<String> authHeaders = exchange.getRequest().getHeaders().getOrEmpty("Authorization");
    if (authHeaders.isEmpty()) {
      return unauthorized(exchange, "Token no proporcionado");
    }
    String token = authHeaders.get(0);
    if (token.startsWith("Bearer ")) {
      token = token.substring(7);
    }
    try {
      SecretKey key = Keys.hmacShaKeyFor(secretoJwt.getBytes(StandardCharsets.UTF_8));
      Jws<Claims> jwsClaims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
      Claims claims = jwsClaims.getBody();
      String usuario = claims.getSubject();
      Object rolesObj = claims.get("roles");
      String roles = Objects.toString(rolesObj, "");
      // Añade información al header para propagación
      exchange = exchange.mutate().request(exchange.getRequest().mutate()
          .header("X-Usuario-Id", usuario)
          .header("X-Roles", roles)
          .build()).build();
      return chain.filter(exchange);
    } catch (Exception e) {
      log.warn("Error al validar JWT: {}", e.getMessage());
      return unauthorized(exchange, "Token inválido");
    }
  }

  /**
   * Devuelve una respuesta 401 no autorizada.
   */
  private Mono<Void> unauthorized(ServerWebExchange exchange, String mensaje) {
    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
    exchange.getResponse().getHeaders().setContentType(MediaType.TEXT_PLAIN);
    byte[] bytes = mensaje.getBytes(StandardCharsets.UTF_8);
    return exchange.getResponse().writeWith(Mono.just(exchange.getResponse().bufferFactory().wrap(bytes)));
  }

  @Override
  public int getOrder() {
    // El orden bajo garantiza que se ejecute después de filtros de ruta predefinidos
    return -1;
  }
}