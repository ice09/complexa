package tech.indus340.complexa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ComplexaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComplexaApplication.class, args);
	}

}
