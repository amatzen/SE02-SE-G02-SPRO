FROM openjdk:15-jdk-alpine

ENV GRADLE_VERSION 6.8.3

RUN apk -U add --no-cache curl; \
    curl https://downloads.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip > gradle.zip; \
    unzip gradle.zip; \
    rm gradle.zip; \
    apk del curl; \
    apk update && apk add --no-cache libstdc++ && rm -rf /var/cache/apk/*

ENV PATH=${PATH}:/gradle-${GRADLE_VERSION}/bin
RUN mkdir -p /work
RUN mkdir -p /GRADLE_CACHE

VOLUME work
VOLUME GRADLE_CACHE

COPY ./* /work/

WORKDIR /work

ENTRYPOINT ["gradle", "-g", "/GRADLE_CACHE", "--no-daemon"]