# 基础镜像使用java:8
FROM java:8
# 作者
MAINTAINER yuanfu <846801668@qq.com>
# VOLUME 指定了临时文件目录为/tmp。其效果是在主机 /var/lib/docker 目录下创建了一个临时文件，并链接到容器的/tmp
VOLUME /tmp
# 将jar包添加到容器中并更名为malatang-0.0.1.jar
ADD malatang-0.0.1-SNAPSHOT.jar malatang-0.0.1.jar
#执行项目 malatang-0.0.1.jar。为了缩短 Tomcat 启动时间，添加一个系统属性指向 “/dev/./urandom” 作为 Entropy Source
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/malatang-0.0.1.jar"]
ENTRYPOINT ["java", "-jar", "/malatang-0.0.1.jar"]