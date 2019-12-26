package org.sdoroshenko.asyncprofilertest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.stream.IntStream;

@SpringBootApplication
public class AsyncProfilerProjectApplication {

	private static final Logger log = LoggerFactory.getLogger(AsyncProfilerProjectApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(AsyncProfilerProjectApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	/*@Bean
	public CommandLineRunner run(RestTemplate restTemplate) {
		return args -> {
			IntStream.range(0, 10).forEach(i -> {
				byte[] bytes = restTemplate.getForObject("http://localhost:8080/images/1001", byte[].class);
				log.info(i + ". len: " + bytes.length);
			});

		};
	}*/

}
