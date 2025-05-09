package com.aamaulana.restfullapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching // cache setup redis
public class RestfullapiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfullapiApplication.class, args);
	}

}
