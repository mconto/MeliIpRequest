FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/ip-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8081

ENV API_EXCHANGE_ACCESS_KEY=ede902f3a4fb76bf9465b5692b823c7a
ENV API_EXCHANGE_URL=http://data.fixer.io/api/latest
ENV API_LOCALIZATOR_ACCESS_KEY=33cb4cbcb239a0eae8bc72a9c57381b7
ENV API_LOCALIZATOR_URL=http://api.ipapi.com/api/

ENTRYPOINT ["java", "-jar", "app.jar"]
CMD []