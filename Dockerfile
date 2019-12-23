# On Windows work in cmd under Admin, possible via bash
# docker build -t sdoroshenko/alpine-async-profiler .
#
# docker run -it --rm -v $(pwd):/project -p 8080:8080 sdoroshenko/alpine
# docker run -it --rm -v %cd%:/project -p 8080:8080 --name async-profiler sdoroshenko/alpine-async-profiler
# docker exec -it async-profiler bash
#
# ^M is a carriage return character. Linux uses the line feed character to mark the end of a line
#
FROM alpine:latest


RUN apk update &&\
      apk add --no-cache --upgrade bash libc6-compat perl openjdk8-dbg coreutils findutils grep

RUN mkdir /usr/local/async-profiler/ &&\
      wget -O /usr/local/async-profiler/async-profiler.tar.gz https://github.com/jvm-profiling-tools/async-profiler/releases/download/v1.6/async-profiler-1.6-linux-x64.tar.gz &&\
      cd /usr/local/async-profiler/ &&\
      tar -xvzf async-profiler.tar.gz &&\
      rm -f /usr/local/async-profiler/async-profiler.tar.gz

ENV PATH="/usr/local/async-profiler:${PATH}"

RUN mkdir /usr/local/flame-graph/ &&\
    wget -O /usr/local/flame-graph/flame-graph.zip https://github.com/brendangregg/FlameGraph/archive/1b1c6deede9c33c5134c920bdb7a44cc5528e9a7.zip &&\
    cd /usr/local/flame-graph/ && unzip flame-graph.zip &&\
    cd /usr/local/flame-graph/ &&\
    mv FlameGraph-1b1c6deede9c33c5134c920bdb7a44cc5528e9a7/* ./ &&\
    rm -Rf FlameGraph-1b1c6deede9c33c5134c920bdb7a44cc5528e9a7 &&\
    rm -f /usr/local/flame-graph/flame-graph.zip

VOLUME /project
WORKDIR /project

EXPOSE 8080

#CMD ["sh", "-c", "/opt/start.sh"]
CMD bash
