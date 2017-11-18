package com.ufcg.sad;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@SpringBootApplication
public class SadApplication {

	public static void main(String[] args) {
		SpringApplication.run(SadApplication.class, args);
	}
}
