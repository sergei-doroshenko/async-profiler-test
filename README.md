#Async-profiler test project
###Test in Docker
Prepare a Docker image:
```bash
$ docker build -t sdoroshenko/alpine-async-profiler .
```
Run container on Linux:
```bash
$ docker run -it --rm -v $(pwd):/project -p 8080:8080 --name async-profiler --cap-add SYS_ADMIN sdoroshenko/alpine-async-profiler
```
Run container on Windows:
```bash
$ docker run -it --rm -v %cd%:/project -p 8080:8080 --name async-profiler --cap-add SYS_ADMIN sdoroshenko/alpine-async-profiler
```
Connect to the container:
```bash
$ docker exec -it async-profiler bash
```
###Try web application
Package and run:
```bash
$ mvn clean package
$ java -jar app/target/async-profiler-test.jar
```
or use spring-boot maven plugin:
```bash
$ mvn spring-boot:run
```
Connect:
```bash
http://localhost:8080/images/1001
```  
or start client:
```cmd
java -jar client\target\async-profiler-client.jar
```
###Try profiler
```bash
$ ps aux
$ pmap -x 67

./profiler.sh -d <duration> -e malloc -f malloc.svg <pid>  
./profiler.sh -d 10 -e malloc -f /project/malloc.svg 31  

./profiler.sh -d <duration> -e mprotect -f mprotect.svg <pid>  
./profiler.sh -d <duration> -e mmap -f mmap.svg <pid>

profiler.sh -d 10 -e malloc -f /project/malloc.svg 11
profiler.sh -d 10 -e mprotect -f /project/mprotect.svg 6

profiler.sh -e wall -t -i 5ms -f /project/result.svg 11

docker exec -ti 3555aaeea80f /usr/local/async-profiler/profiler.sh -d 30 -o collapsed -e itimer -f /tmp/collapsed.txt 1
```
###Possible issues
/usr/local/async-profiler/profiler.sh: line 63: /usr/local/async-profiler/build/jattach: No such file or directory  
profiler.sh: line 63: /build/jattach: No such file or directory
###Links
https://vimeo.com/364039638   
https://www.reddit.com/r/java/comments/dtvpr6/memory_footprint_of_a_java_process_by_andrei  
https://www.youtube.com/watch?v=c755fFv1Rnk  
https://stackoverflow.com/questions/53576163/interpreting-jemaloc-data-possible-off-heap-leak/53598622   

