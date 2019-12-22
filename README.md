https://vimeo.com/364039638
https://www.reddit.com/r/java/comments/dtvpr6/memory_footprint_of_a_java_process_by_andrei/

https://stackoverflow.com/questions/53576163/interpreting-jemaloc-data-possible-off-heap-leak/53598622  
./profiler.sh -d <duration> -e malloc -f malloc.svg <pid>  
./profiler.sh -d 10 -e malloc -f /project/malloc.svg 31  

./profiler.sh -d <duration> -e mprotect -f mprotect.svg <pid>  
./profiler.sh -d <duration> -e mmap -f mmap.svg <pid>

mvn package
java -jar target\async-profiler-project-0.0.1-SNAPSHOT.jar

mvn spring-boot:run

http://localhost:8080/images/1001

docker exec -ti 3555aaeea80f /usr/local/async-profiler/profiler.sh -d 30 -o collapsed -e itimer -f /tmp/collapsed.txt 1
