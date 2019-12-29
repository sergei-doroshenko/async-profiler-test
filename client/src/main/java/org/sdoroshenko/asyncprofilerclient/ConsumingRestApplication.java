package org.sdoroshenko.asyncprofilerclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.util.Random;
import java.util.stream.IntStream;


public class ConsumingRestApplication {

    private static final Logger log = LoggerFactory.getLogger(ConsumingRestApplication.class);

    public static void main(String[] args) throws InterruptedException {
        RestTemplate restTemplate = new RestTemplateBuilder().build();
        while (true) {
            int k = new Random().nextInt(1_000_000) + 10;
            byte[] bytes = restTemplate.getForObject("http://localhost:8080/images/" + k, byte[].class);
            log.info("len: " + bytes.length);
            Thread.sleep(100);
        }
    }
}
