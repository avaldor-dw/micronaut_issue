FROM openjdk:8u171-alpine3.7
WORKDIR /app
RUN apk --no-cache add curl
COPY target asset-ws.jar
COPY /home/dev4/config/asset-ws/* ./config/
CMD java ${JAVA_OPTS} -Dmicronaut.config.files=/app/config/application.yml,/app/config/arangodb.yml -jar asset-ws.jar
