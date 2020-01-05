package org.sdoroshenko.asyncprofilerclient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.util.Random;


public class ConsumingRestApplication {

    private static final Logger log = LoggerFactory.getLogger(ConsumingRestApplication.class);

    public static void main(String[] args) {
        log.info("Client started...");
        RestTemplate restTemplate = new RestTemplateBuilder().build();
        while (true) {
            String url = getUrl1();
            restTemplate.getForObject(url, byte[].class);
        }
    }

    private static String getUrl0() {
        int k = new Random().nextInt(1_000_000) + 10;
        String url = "http://localhost:8080/images/" + k;
        return url;
    }

    private static String getUrl1() {
        return  "http://localhost:8080/getImage?id=1";
    }
}
