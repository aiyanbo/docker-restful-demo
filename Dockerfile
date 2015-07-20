FROM jamesdbloom/docker-java8-maven

MAINTAINER Andy Ai "yanbo.ai@gmail.com"

WORKDIR /code

ENV host 0.0.0.0

ADD pom.xml /code/pom.xml
ADD src /code/src
ADD settings.xml /root/.m2/settings.xml

RUN ["mvn", "package"]

EXPOSE 9998

ENTRYPOINT ["java", "-cp", "target/lib/*:target/docker-restful-demo-1.0-SNAPSHOT.jar","org.jmotor.StackMicroServices"]

CMD ["-Djava.awt.headless=true"]
