# Usar una imagen base de OpenJDK
FROM openjdk:17-jdk-slim

# Establecer directorio de trabajo
WORKDIR /app

# Copiar archivo JAR de la aplicación
COPY target/*.jar app.jar

# Exponer el puerto 8083
EXPOSE 8083

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]