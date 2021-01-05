# 添加 Java 11 镜像来源
FROM adoptopenjdk/openjdk11:ubi

# 添加参数
ARG JAR_FILE

# 添加 Spring Boot 包
ADD target/${JAR_FILE} app.jar

# 执行启动命令
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]