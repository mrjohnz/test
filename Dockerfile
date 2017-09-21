FROM 10.238.118.242:30303/op_svc_cse/javabase 
RUN mkdir -p /home/apps/
COPY ./target/rtcal-1.0.0-SNAPSHOT.jar /home/apps/
CMD ["cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime"]
CMD ["java", "-jar", "/home/apps/rtcal-1.0.0-SNAPSHOT.jar"]
EXPOSE 8081

