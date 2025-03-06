# Используем базовый образ с OpenJDK
FROM openjdk:23-jdk-slim

# Устанавливаем рабочую директорию внутри контейнера
WORKDIR /app

# Копируем JAR файл в контейнер
COPY ./target/demo-0.0.1-SNAPSHOT.jar /app/my-java-app.jar

# Открываем порт для приложения
EXPOSE 8080

# Команда для запуска приложения
CMD ["java", "-jar", "my-java-app.jar"]
