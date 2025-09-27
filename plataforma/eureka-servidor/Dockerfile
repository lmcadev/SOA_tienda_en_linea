# Usar imagen base de OpenJDK
FROM openjdk:17-jdk-slim

# Crear directorio de aplicación
WORKDIR /app

# Copiar JAR de la aplicación
COPY target/*.jar app.jar

# Exponer puerto
EXPOSE 8761

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]