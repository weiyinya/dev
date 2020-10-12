# 导入java8环境
FROM frolvlad/alpine-oraclejre8:slim
# 复制本地文件到容器中
ADD elasticjob-0.0.1-SNAPSHOT.jar /elasticjob.jar
# 设置该docker环境变量
ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /elasticjob.jar" ]