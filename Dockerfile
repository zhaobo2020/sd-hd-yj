# 使用官方的 OpenJDK 17 基础镜像
FROM openjdk:17-jdk-slim

# 设置工作目录
WORKDIR /app

# 将打包好的 JAR 文件复制到工作目录
COPY target/sd_hd_yj-1.0-SNAPSHOT.jar app.jar
# 创建配置目录（非必须，但推荐）
RUN mkdir -p /app/config
# 暴露应用程序监听的端口，根据实际情况修改
EXPOSE 9923

# 定义容器启动时执行的命令
ENTRYPOINT ["java", "-jar", "app.jar"]