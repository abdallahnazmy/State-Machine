FROM openjdk:11
ADD target/employee-states-ms.jar employee-states-ms.jar
ENTRYPOINT ["java","-jar","employee-states-ms.jar"]

EXPOSE 8050