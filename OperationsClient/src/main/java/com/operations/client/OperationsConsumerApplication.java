package com.operations.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.operations.client.service.NewAdder;
import com.sample.test.SampleAdder;

@SpringBootApplication
public class OperationsConsumerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OperationsConsumerApplication.class, args);
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}

	
	@Bean
	public NewAdder getAdder(){
		return new NewAdder();
	}
	
	@Bean(value="sampleAdder")
	public SampleAdder getSampleAdder(){
		return new SampleAdder();
	}
	
}

