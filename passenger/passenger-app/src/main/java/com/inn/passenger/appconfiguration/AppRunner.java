package com.inn.passenger.appconfiguration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@ComponentScan({ "com.inn" })
@EntityScan({ "com.inn" })
@EnableJpaRepositories({ "com.inn" })
@EnableWebMvc
@EnableFeignClients("com.inn.train.client.rest")
public class AppRunner extends SpringBootServletInitializer {

  public static void main(String[] args) {
    SpringApplication.run(AppRunner.class);
    System.out.print(
        "****************************************** Service Started SuccessFully **************************************************");
  }
}
