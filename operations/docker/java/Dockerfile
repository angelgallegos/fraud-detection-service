
FROM docker/openjdk:17.0.2-oraclelinux8
# Refer to Maven build -> finalName
ARG JAR_FILE=app/target/fraud-detection-0.0.1-SNAPSHOT.jar

# cd /opt/app
WORKDIR /opt/app

# cp target/spring-boot-web.jar /opt/app/app.jar
COPY ${JAR_FILE} app.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","target/fraud-detection-0.0.1-SNAPSHOT.jar"]