FROM openjdk:23-jdk-slim
WORKDIR /app
ARG JAR_FILE=*.jar
COPY ${JAR_FILE} ./blog-system.jar
EXPOSE 8080


# Run the Spring Boot application
ENTRYPOINT ["java", "-jar"]
CMD ["/app/blog-system.jar"]

#