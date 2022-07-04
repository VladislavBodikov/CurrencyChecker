FROM openjdk:11

EXPOSE 8080

VOLUME /tmp

ADD currencyChecker-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
