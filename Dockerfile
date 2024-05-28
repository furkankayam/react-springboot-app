#FROM openjdk:17-jdk-slim-buster AS builder

#RUN apt-get update -y
#RUN apt-get install -y binutils

#WORKDIR /app

#COPY . .

#RUN ./gradlew build -i --stacktrace -x test
#RUN ./gradlew jlink -i --stacktrace

# lightweight image
#FROM openjdk:17-jdk-slim

#COPY --from=builder /app/app/build/image /app

#ENTRYPOINT /app/bin/app

#FROM openjdk:17-jdk-slim AS builder
#WORKDIR /workspace/app

#COPY gradlew .
#COPY gradle gradle
#COPY build.gradle .
#RUN ./gradlew dependencies

#COPY src src
#RUN ./gradlew build unpack -x test
#RUN mkdir -p build/dependency && (cd build/dependency; jar -xf ../libs/*.jar)

#FROM openjdk:17-jdk-slim
#VOLUME /tmp
#ARG DEPENDENCY=/workspace/app/build/dependency
#COPY --from=build ${DEPENDENCY}/BOOT-INF/lib /app/lib
#COPY --from=build ${DEPENDENCY}/META-INF /app/META-INF
#COPY --from=build ${DEPENDENCY}/BOOT-INF/classes /app
#ENTRYPOINT ["java","-cp","app:app/lib/*","com.react_springboot_app.ReactSpringbootAppApplication"]

FROM openjdk:17-jdk-slim AS builder
WORKDIR /workspace/app

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY src src
RUN ./gradlew build -x test

FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY --from=builder /workspace/app/build/libs/*.jar /app/app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]
