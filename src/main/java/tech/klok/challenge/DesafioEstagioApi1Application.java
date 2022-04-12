package tech.klok.challenge;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

//import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@EnableRabbit
@EnableScheduling
//@EnableOpenApi
public class DesafioEstagioApi1Application {

	public static void main(String[] args) {
		SpringApplication.run(DesafioEstagioApi1Application.class, args);
	}

}
