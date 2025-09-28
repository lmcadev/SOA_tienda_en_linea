# SOA Tienda en linea - Sistema de Comercio Electrónico con Arquitectura de Microservicios

[![Java](https://img.shields.io/badge/Java-17+-blue.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.3.3-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Spring Cloud](https://img.shields.io/badge/Spring%20Cloud-2023.0.3-green.svg)](https://spring.io/projects/spring-cloud)
[![Maven](https://img.shields.io/badge/Maven-Multi--Module-red.svg)](https://maven.apache.org/)
[![Docker](https://img.shields.io/badge/Docker-Compose-blue.svg)](https://docs.docker.com/compose/)

##  Descripción

**Tienda en linea SOA** es un sistema de comercio electrónico desarrollado con arquitectura de microservicios basado en Spring Boot y Spring Cloud. El proyecto implementa un conjunto de servicios independientes que se comunican entre sí para proporcionar una plataforma completa con funcionalidades de gestión de pedidos, inventario, pagos y notificaciones.

##  Estado Actual del Proyecto

###  Completado
- **Eureka Server**:  Funcionando correctamente
- **API Gateway**:  Funcionando con filtros JWT
- **RabbitMQ**:  Operativo con panel de administración
- **PostgreSQL**:  Bases de datos configuradas para cada servicio
- **Docker Compose**:  Orquestación completa de contenedores
- **Configuración Maven**:  Multi-módulo con plugins Spring Boot

###  En Desarrollo
- **Servicios de Negocio**: Autenticación, Pedidos, Inventario, Pagos, Notificaciones
- **Integración Completa**: Comunicación entre microservicios
- **Testing**: Tests unitarios e integración

##  Arquitectura

El sistema está organizado en tres capas principales:

###  Plataforma (Infraestructura)
- **Eureka Server**: Servicio de descubrimiento y registro de microservicios
- **API Gateway**: Puerta de enlace para enrutamiento y balanceado de carga con filtros JWT
- **Servicio de Autenticación**: Gestión de usuarios y autenticación JWT

###  Servicios de Negocio
- **Servicio de Pedidos**: Gestión del ciclo de vida de pedidos
- **Servicio de Inventario**: Control de stock y disponibilidad de productos
- **Servicio de Pagos**: Procesamiento de transacciones financieras
- **Servicio de Notificaciones**: Sistema de notificaciones y alertas

###  Módulos Comunes
- **DTOs Comunes**: Objetos de transferencia de datos compartidos
- **Eventos Comunes**: Definición de eventos para comunicación asíncrona

##  Estructura del Proyecto

```
SOA_tienda_en_linea/
├── comun/                          # Módulos compartidos
│   ├── dto-comunes/               # DTOs reutilizables
│   └── eventos-comunes/           # Eventos para mensajes asíncronos
├── plataforma/                    # Servicios de infraestructura
│   ├── eureka-servidor/          # Descubrimiento de servicios
│   ├── puerta-enlace/            # API Gateway con JWT
│   └── servicio-autenticacion/   # Servicio de autenticación
├── servicios/                     # Microservicios de negocio
│   ├── servicio-inventario/      # Gestión de inventario y stock
│   ├── servicio-notificaciones/  # Sistema de notificaciones
│   ├── servicio-pagos/           # Procesamiento de pagos
│   └── servicio-pedidos/         # Gestión de pedidos
├── .env                          # Variables de entorno
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
- **PostgreSQL 16** 

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
cd SOA_tienda_en_linea
```

### 2. Configurar Variables de Entorno

Crea un archivo `.env` en la raíz del proyecto:

```env
# URLs de servicios
URL_EUREKA=http://eureka:8761/eureka
CLAVE_PUBLICA_JWT=ZEJ7MvyvopwzPscyah3YEMRXg6NaCLry7cqI1oSO1F2qDFAdgA
SECRETO_JWT=waA6HRsOAq9GlmwQmb2hUlQ3XCbcUQxT5sLnipAyJIxgakzFLK

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

# Base de datos - Auth
URL_BD_AUTH=jdbc:postgresql://bd_auth:5432/auth
USUARIO_AUTH=postgres
CONTRASENA_AUTH=auth_password
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
- **API Gateway**: http://localhost:8090  
- **Health Checks**: Disponibles en `/actuator/health` para cada servicio

####  **Documentación de APIs (Swagger UI):**
- **Autenticación**: http://localhost:8081/swagger-ui.html
- **Pedidos**: http://localhost:8082/swagger-ui.html  
- **Inventario**: http://localhost:8083/swagger-ui.html
- **Pagos**: http://localhost:8084/swagger-ui.html
- **Notificaciones**: http://localhost:8085/swagger-ui.html

##  Servicios y Puertos

| Servicio | Puerto | Estado | Descripción |
|----------|--------|--------|-------------|
| Eureka Server | 8761 |  Funcionando | Service Discovery y Dashboard |
| API Gateway | 8090 |  Funcionando | Punto de entrada principal con filtros JWT |
| Servicio Autenticación | 8081 |  Funcionando | Gestión de usuarios y tokens JWT |
| Servicio Pedidos | 8082 |  En desarrollo | Gestión del ciclo de vida de pedidos |
| Servicio Inventario | 8083 |  En desarrollo | Control de stock y productos |
| Servicio Pagos | 8084 |  En desarrollo | Procesamiento de transacciones |
| Servicio Notificaciones | 8085 |  En desarrollo | Sistema de notificaciones |
| PostgreSQL Instances | 5432 |  Funcionando | Bases de datos (internas) |

##  Configuración de Desarrollo


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

> **Nota**: Todas las rutas pasan a través del API Gateway en `http://localhost:8090`

### Autenticación
- `POST /auth/login` - Iniciar sesión
- `POST /auth/register` - Registrar nuevo usuario
- `POST /auth/refresh` - Renovar token JWT

### Pedidos
- `GET /pedidos` - Listar pedidos (con paginación)
- `GET /pedidos/{id}` - Obtener pedido específico
- `POST /pedidos` - Crear nuevo pedido
- `PUT /pedidos/{id}` - Actualizar pedido
- `DELETE /pedidos/{id}` - Cancelar pedido

### Inventario
- `GET /inventario` - Consultar stock disponible
- `GET /inventario/{id}` - Obtener producto específico
- `POST /inventario` - Crear nuevo producto
- `PUT /inventario/{id}` - Actualizar stock
- `DELETE /inventario/{id}` - Eliminar producto

### Pagos
- `POST /pagos` - Procesar pago
- `GET /pagos/{id}` - Consultar estado de pago
- `PUT /pagos/{id}/confirmar` - Confirmar pago

### Notificaciones
- `GET /notificaciones` - Obtener notificaciones del usuario
- `POST /notificaciones` - Enviar notificación
- `PUT /notificaciones/{id}/marcar-leida` - Marcar como leída

##   Documentación de APIs con Swagger

Todos los microservicios del sistema incluyen documentación automática de APIs generada con Swagger

###  Acceso a la Documentación


| Servicio | Swagger UI | OpenAPI JSON |
|----------|------------|--------------|
| **API Gateway** | http://localhost:8090/swagger-ui.html | http://localhost:8090/v3/api-docs |
| **Servicio Autenticación** | http://localhost:8081/swagger-ui.html | http://localhost:8081/v3/api-docs |
| **Servicio Pedidos** | http://localhost:8082/swagger-ui.html | http://localhost:8082/v3/api-docs |
| **Servicio Inventario** | http://localhost:8083/swagger-ui.html | http://localhost:8083/v3/api-docs |
| **Servicio Pagos** | http://localhost:8084/swagger-ui.html | http://localhost:8084/v3/api-docs |
| **Servicio Notificaciones** | http://localhost:8085/swagger-ui.html | http://localhost:8085/v3/api-docs |

>  **Acceso Unificado**: También puedes acceder a todas las APIs a través del API Gateway en http://localhost:8090 que actúa como punto de entrada único al sistema.

###  Cómo Usar Swagger UI

#### **Explorar Endpoints**
- Navega por todos los endpoints organizados por controlador
- Ve los métodos HTTP disponibles (GET, POST, PUT, DELETE)
- Consulta parámetros requeridos y opcionales
- Revisa los modelos de datos de entrada y salida


###  Información Disponible en la Documentación

Cada servicio documenta automáticamente:

#### **Endpoints**
-  Rutas completas con métodos HTTP
-  Parámetros de consulta, ruta y cuerpo
-  Códigos de respuesta HTTP (200, 201, 400, 404, 500)
-  Ejemplos de respuestas exitosas y errores

#### **Modelos de Datos**
-  Esquemas de objetos de entrada (Request DTOs)
-  Esquemas de objetos de salida (Response DTOs)  
-  Validaciones y restricciones de campos
-  Tipos de datos y formatos

#### **Configuración de Seguridad**
-  Esquemas de autenticación JWT
-  Endpoints públicos vs protegidos
-  Scopes y permisos requeridos

#### **Importar a Postman - TODOS los Servicios**

 **Importante**: Para importar todos los microservicios, debes importar cada uno por separado:

**1. Servicio de Autenticación:**
- Abre Postman → Clic en "Import" → "Link"
- Pega: `http://localhost:8081/v3/api-docs`
- Nombra: "SOA Tienda - Autenticación"

**2. Servicio de Pedidos:**
- Clic en "Import" → "Link"
- Pega: `http://localhost:8082/v3/api-docs`
- Nombra: "SOA Tienda - Pedidos"

**3. Servicio de Inventario:**
- Clic en "Import" → "Link" 
- Pega: `http://localhost:8083/v3/api-docs`
- Nombra: "SOA Tienda - Inventario"

**4. Servicio de Pagos:**
- Clic en "Import" → "Link"
- Pega: `http://localhost:8084/v3/api-docs`
- Nombra: "SOA Tienda - Pagos"

**5. Servicio de Notificaciones:**
- Clic en "Import" → "Link"
- Pega: `http://localhost:8085/v3/api-docs`
- Nombra: "SOA Tienda - Notificaciones"


##  Conexión a Bases de Datos con HeidiSQL

###  **Configuración de Conexiones PostgreSQL**

Una vez que el sistema esté ejecutándose con `docker-compose up`, puedes conectarte a las bases de datos usando HeidiSQL con las siguientes configuraciones:

| Servicio | Puerto Host | Base de Datos | Usuario | Contraseña | Conexión HeidiSQL |
|----------|-------------|---------------|---------|------------|-------------------|
| **Autenticación** | 5437 | `auth` | `postgres` | `auth_password` | localhost:5437 |
| **Pedidos** | 5433 | `pedidos` | `postgres` | `pedidos_password` | localhost:5433 |
| **Inventario** | 5434 | `inventario` | `postgres` | `inventario_password` | localhost:5434 |
| **Pagos** | 5435 | `pagos` | `postgres` | `pagos_password` | localhost:5435 |
| **Notificaciones** | 5436 | `notificaciones` | `postgres` | `notificaciones_password` | localhost:5436 |

### Configuración de Conexiones HeidiSQL
#### Base de Datos de Autenticación
- Base de Datos de Pedidos
Host/IP: localhost o 127.0.0.1
Puerto: 5433
Usuario: pedidos_user
Contraseña: pedidos_pass
Base de Datos: pedidos
Tipo: PostgreSQL

- Base de Datos de Inventario
Host/IP: localhost o 127.0.0.1
Puerto: 5434
Usuario: inventario_user
Contraseña: inventario_pass
Base de Datos: inventario
Tipo: PostgreSQL
- Base de Datos de Pagos

Host/IP: localhost o 127.0.0.1
Puerto: 5435
Usuario: pagos_user
Contraseña: pagos_pass
Base de Datos: pagos
Tipo: PostgreSQL

- Base de Datos de Notificaciones
Host/IP: localhost o 127.0.0.1
Puerto: 5436
Usuario: notif_user
Contraseña: notif_pass
Base de Datos: notificaciones
Tipo: PostgreSQL

- Base de Datos de Autenticación
Host/IP: localhost o 127.0.0.1
Puerto: 5437
Usuario: postgres
Contraseña: auth_password
Base de Datos: auth
Tipo: PostgreSQL

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


### Logs

Ver logs de servicios específicos:
```bash
docker-compose logs servicio-nombre
```

Ver logs en tiempo real:
```bash
docker-compose logs -f
```

##  Autores

- KEWIN GUZMAN DIAZ
- YEISON SAENZ CASTRO
- SINDY PATRICIA YEPES SÁNCHEZ
- LUIS MIGUEL CASTAÑEDA ARCINIEGAS
- LAURA VALENTINA AVENDAÑO TIBOCHA





