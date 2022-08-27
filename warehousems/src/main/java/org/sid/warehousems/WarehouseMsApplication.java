package org.sid.warehousems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class WarehouseMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(WarehouseMsApplication.class, args);
	}

}
