package org.sdoroshenko.asyncprofilerclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.util.stream.IntStream;


public class ConsumingRestApplication {

    private static final Logger log = LoggerFactory.getLogger(ConsumingRestApplication.class);

    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplateBuilder().build();
        IntStream.range(0, 10).forEach(i -> {
            byte[] bytes = restTemplate.getForObject("http://localhost:8080/images/1001", byte[].class);
            log.info("len: " + bytes.length);
        });
    }

}
