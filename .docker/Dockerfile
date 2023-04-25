FROM amazoncorretto:19-alpine-jdk

RUN echo "Copy files..."

COPY src/ /app-build/src
COPY .mvn/ /app-build/.mvn
COPY pom.xml /app-build/
COPY mvnw* /app-build/

RUN echo "Display /app-build/ folder ..."
RUN cd /app-build/ \
    && echo $(pwd) && echo $(ls -la)

RUN echo "Maven build..."
RUN cd /app-build/ \
    && ./mvnw clean package

RUN echo "Check /app-build/target/ folder"
RUN cd /app-build/target/ \
    && echo $(pwd) && echo $(ls -la)
