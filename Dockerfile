# On Windows work in cmd under Admin, possible via bash
# docker build -t sdoroshenko/ubuntu-async-profiler .
# docker run --it -rm -v $(pwd):/project -p 8080:8080 sdoroshenko/ubuntu-async-profiler
# docker run -it --rm -v %cd%:/project -p 8080:8080 sdoroshenko/ubuntu-async-profiler
#
# ^M is a carriage return character. Linux uses the line feed character to mark the end of a line
#
FROM ubuntu:latest
RUN apt-get update && apt-get install unzip openjdk-8-dbg -y

COPY async-profiler-1.6-linux-x64.tar.gz /opt/
COPY target/async-profiler-test.jar /opt/
COPY *.sh /opt/

RUN chmod +x /opt/*.sh

VOLUME /project
WORKDIR /opt

EXPOSE 8080

#CMD ["sh", "/opt/start.sh"]
CMD bash