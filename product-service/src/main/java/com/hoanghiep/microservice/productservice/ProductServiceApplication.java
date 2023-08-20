package com.hoanghiep.microservice.productservice;

import com.hoanghiep.microservice.productservice.command.interceptor.CreateProductCommandInterceptor;
import com.hoanghiep.microservice.productservice.config.AxonXstreamConfig;
import org.axonframework.commandhandling.CommandBus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;

@EnableDiscoveryClient
@SpringBootApplication
@Import({ AxonXstreamConfig.class })
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}


	@Autowired
	public void registerCreateProductCommandInterceptor(ApplicationContext context, CommandBus commandBus) {
		commandBus.registerDispatchInterceptor(context.getBean(CreateProductCommandInterceptor.class));
	}
}
