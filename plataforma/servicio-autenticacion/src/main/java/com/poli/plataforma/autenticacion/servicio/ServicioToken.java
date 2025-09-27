package com.poli.plataforma.autenticacion.servicio;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Set;

/**
 * Servicio que emitir tokens JWT.
 */
@Service
public class ServicioToken {

  @Value("${SECRETO_JWT:mi_super_secreto}")
  private String secretoJwt;

  /**
   * Genera un token JWT con sujeto (usuario) y roles.
   *
   * @param usuario nombre de usuario
   * @param roles   roles asignados
   * @return token firmado
   */
  public String generarToken(String usuario, Set<String> roles) {
    SecretKey key = Keys.hmacShaKeyFor(secretoJwt.getBytes(StandardCharsets.UTF_8));
    Instant ahora = Instant.now();
    return Jwts.builder()
        .setSubject(usuario)
        .claim("roles", String.join(",", roles))
        .setIssuedAt(Date.from(ahora))
        .setExpiration(Date.from(ahora.plus(2, ChronoUnit.HOURS)))
        .signWith(key, SignatureAlgorithm.HS256)
        .compact();
  }
}