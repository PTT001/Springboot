# 使用 OpenJDK 作為基礎映像
FROM openjdk:21-jdk-slim

# 設定工作目錄
WORKDIR /app

# 將 JAR 文件複製到容器中
COPY target/RayDemo-0.0.1-SNAPSHOT.jar app.jar

# 暴露應用程式的端口（例如 8080）
EXPOSE 8080

# 運行應用程式
ENTRYPOINT ["java", "-jar", "app.jar"]