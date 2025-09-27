# SOA Tienda en linea - Sistema de Comercio Electrónico con Arquitectura de Microservicios

[![Java](https://img.shields.io/badge/Java-17+-blue.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.3-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-2023.0.3-green.svg)](https://spring.io/projects/spring-cloud)
[![Maven](https://img.shields.io/badge/Maven-Multi--Module-red.svg)](https://maven.apache.org/)
[![Docker](https://img.shields.io/badge/Docker-Compose-blue.svg)](https://docs.docker.com/compose/)

##  Descripción

**Tienda en linea SOA** es un sistema de comercio electrónico desarrollado con arquitectura de microservicios basado en Spring Boot y Spring Cloud. El proyecto implementa un conjunto de servicios independientes que se comunican entre sí para proporcionar una plataforma completa con funcionalidades de gestión de pedidos, inventario, pagos y notificaciones.

##  Arquitectura

El sistema está organizado en tres capas principales:

###  Plataforma (Infraestructura)
- **Eureka Server**: Servicio de descubrimiento y registro de microservicios
- **API Gateway**: Puerta de enlace para enrutamiento y balanceado de carga
- **Servicio de Autenticación**: Gestión de usuarios y autenticación JWT

###  Servicios de Negocio
- **Servicio de Pedidos**: Gestión del ciclo de vida de pedidos
- **Servicio de Inventario**: Control de stock y disponibilidad de productos
- **Servicio de Pagos**: Procesamiento de transacciones financieras


###  Módulos Comunes
- **DTOs Comunes**: Objetos de transferencia de datos compartidos
- **Eventos Comunes**: Definición de eventos para comunicación asíncrona

##  Estructura del Proyecto

```
comercio-soa/
├── comun/                          # Módulos compartidos
│   ├── dto-comunes/               # DTOs reutilizables
│   └── eventos-comunes/           # Eventos para mensajes
├── plataforma/                    # Servicios de infraestructura
│   ├── eureka-servidor/          # Descubrimiento de servicios
│   ├── puerta-enlace/            # API Gateway
│   └── servicio-autenticacion/   # Servicio de autentificacion
├── servicios/                     # Microservicios de negocio
│   ├── servicio-inventario/      # Inventario
│   ├── servicio-pagos/           # Procesamiento de pago
│   └── servicio-pedidos/         # Ordenes
├── docker-compose.yml            # Orquestación de contenedores
└── pom.xml                      # Configuración Maven padre
```

##  Stack Tecnológico

### Backend
- **Java 17+**
- **Spring Boot 3.3.3**
- **Spring Cloud 2023.0.3**
- **Spring Security** (JWT)
- **Spring Data JPA**
- **Maven Multi-Module**

### Base de Datos
- **PostgreSQL 16** (para cada microservicio)

### Mensajería
- **RabbitMQ 3** (con panel de administración)

### Contenedores
- **Docker & Docker Compose**

### Documentación API
- **OpenAPI 3 / Swagger**

##  Inicio Rápido

### Pre-requisitos

- Java 17 o superior
- Maven 3.8+
- Docker y Docker Compose
- Git

### 1. Clonar el Repositorio

```bash
git clone https://github.com/lmcadev/SOA_tienda_en_linea
cd comercio-soa
```

### 2. Configurar Variables de Entorno

Crea un archivo `.env` en la raíz del proyecto:

```env
# URLs de servicios
URL_EUREKA=http://eureka:8761/eureka
CLAVE_PUBLICA_JWT=tu-clave-publica-jwt
SECRETO_JWT=tu-secreto-jwt-super-seguro

# Configuración de colas
HOST_COLA=cola_mensajes

# Base de datos - Pedidos
URL_BD_PEDIDOS=jdbc:postgresql://bd_pedidos:5432/pedidos
USUARIO_PEDIDOS=pedidos_user
CONTRASENA_PEDIDOS=pedidos_pass

# Base de datos - Inventario
URL_BD_INVENTARIO=jdbc:postgresql://bd_inventario:5432/inventario
USUARIO_INVENTARIO=inventario_user
CONTRASENA_INVENTARIO=inventario_pass

# Base de datos - Pagos
URL_BD_PAGOS=jdbc:postgresql://bd_pagos:5432/pagos
USUARIO_PAGOS=pagos_user
CONTRASENA_PAGOS=pagos_pass

# Base de datos - Notificaciones
URL_BD_NOTIFICACIONES=jdbc:postgresql://bd_notificaciones:5432/notificaciones
USUARIO_NOTIFICACIONES=notif_user
CONTRASENA_NOTIFICACIONES=notif_pass
```

### 3. Compilar el Proyecto

```bash
mvn clean compile
```

### 4. Ejecutar con Docker Compose

```bash
docker-compose up --build
```

### 5. Verificar los Servicios

Una vez que todos los contenedores estén ejecutándose:

- **Eureka Dashboard**: http://localhost:8761
- **API Gateway**: http://localhost:8080
- **RabbitMQ Management**: http://localhost:15672 (admin/admin)

##  Servicios y Puertos

| Servicio | Puerto | Descripción |
|----------|--------|-------------|
| Eureka Server | 8761 | Service Discovery |
| API Gateway | 8080 | Punto de entrada principal |
| RabbitMQ | 5672, 15672 | Message Broker + Management UI |
| PostgreSQL Instances | 5432 | Bases de datos (internas) |

##  Configuración de Desarrollo

### Ejecutar Servicios Individualmente

1. **Iniciar Eureka Server**:
```bash
cd plataforma/eureka-servidor
mvn spring-boot:run
```

2. **Iniciar otros servicios** (en orden):
```bash
# API Gateway
cd plataforma/puerta-enlace
mvn spring-boot:run

# Servicio de Autenticación
cd plataforma/servicio-autenticacion
mvn spring-boot:run

# Servicios de negocio
cd servicios/servicio-inventario
mvn spring-boot:run
```

### Configuración de Base de Datos Local

Para desarrollo local con PostgreSQL:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/tu_bd
    username: tu_usuario
    password: tu_password
  jpa:
    hibernate:
      ddl-auto: update
```

##  APIs Principales

### Autenticación
- `POST /auth/login` - Iniciar sesión

### Pedidos
- `GET /pedidos` - Listar pedidos (con paginación)
- `GET /pedidos/{id}` - Obtener pedido específico
- `POST /pedidos` - Crear nuevo pedido
- `PUT /pedidos/{id}` - Actualizar pedido
- `DELETE /pedidos/{id}` - Cancelar pedido

### Inventario
- `GET /inventario` - Consultar stock
- `POST /inventario` - Crear producto
- `PUT /inventario/{id}` - Actualizar stock

### Pagos
- `POST /pagos` - Procesar pago
- `GET /pagos/{id}` - Consultar estado de pago

### Notificaciones
- `GET /notificaciones` - Obtener notificaciones
- `POST /notificaciones` - Enviar notificación

##  Testing

### Ejecutar Tests Unitarios

```bash
mvn test
```

### Ejecutar Tests de Integración

```bash
mvn verify
```

### Coverage Report

```bash
mvn jacoco:report
```

##  Monitoreo y Observabilidad

### Health Checks

Todos los servicios exponen endpoints de salud:
- `GET /actuator/health`
- `GET /actuator/info`

### Metrics

Métricas disponibles en:
- `GET /actuator/metrics`

##  Seguridad

- **Autenticación**: JWT tokens
- **Autorización**: Control de acceso por rol
- **Comunicación**: HTTPS en producción
- **Secrets**: Variables de entorno para configuración sensible

##  Resolución de Problemas

### Problemas Comunes

1. **Servicios no se registran en Eureka**:
   - Verificar que Eureka esté ejecutándose primero
   - Revisar la configuración de `eureka.client.service-url`

2. **Error de conexión a base de datos**:
   - Verificar que PostgreSQL esté ejecutándose
   - Comprobar credenciales en variables de entorno

3. **RabbitMQ connection refused**:
   - Asegurar que RabbitMQ esté iniciado antes que los servicios
   - Verificar la configuración de `HOST_COLA`

### Logs

Ver logs de servicios específicos:
```bash
docker-compose logs servicio-nombre
```

Ver logs en tiempo real:
```bash
docker-compose logs -f
```

##  Contribución

1. Fork el proyecto
2. Crea una rama para tu feature (`git checkout -b feature/nueva-funcionalidad`)
3. Commit tus cambios (`git commit -am 'Agrega nueva funcionalidad'`)
4. Push a la rama (`git push origin feature/nueva-funcionalidad`)
5. Crea un Pull Request

### Estándares de Código

- Seguir las convenciones de Java
- Documentar métodos públicos
- Incluir tests para nueva funcionalidad
- Mantener cobertura de tests > 80%

##  Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para más detalles.

##  Autores

-



