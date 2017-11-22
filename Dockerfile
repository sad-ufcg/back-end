FROM maven:latest

MAINTAINER "Estudantes de computacao - UFCG"

WORKDIR /usr/src/sad

COPY . .

EXPOSE 8080

CMD ["mvn", "spring-boot:run"]

