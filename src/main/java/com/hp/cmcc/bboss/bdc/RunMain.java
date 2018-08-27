package com.hp.cmcc.bboss.bdc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAsync
public class RunMain {
	
	public static void main(String[] args) {
		System.out.println(System.getProperty("user.dir"));
		SpringApplication.run(RunMain.class, args);
	}
}
