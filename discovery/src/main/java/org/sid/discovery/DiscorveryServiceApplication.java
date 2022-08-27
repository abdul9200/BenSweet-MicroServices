package org.sid.discovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaServer
public class DiscorveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscorveryServiceApplication.class, args);
	}


}
