FROM amazoncorretto:19-alpine-jdk
COPY target/gpw-app.jar gpw-app.jar
ENTRYPOINT ["java","-jar","/gpw-app.jar"]