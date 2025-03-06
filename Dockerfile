# Используем OpenJDK 23 как базовый образ
FROM openjdk:23-jdk-slim

# Устанавливаем рабочую директорию в контейнере
WORKDIR /app

# Копируем .jar файл из target в контейнер
COPY target/demo-0.0.1-SNAPSHOT.jar /app/my-java-app.jar

# Открываем порты
EXPOSE 8080

# Команда для запуска приложения
CMD ["java", "-jar", "/app/my-java-app.jar"]
