package com.healthtrainer.htserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // 자동 생성을 위한 어노테이션
@SpringBootApplication
public class HtServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(HtServerApplication.class, args);
	}

}