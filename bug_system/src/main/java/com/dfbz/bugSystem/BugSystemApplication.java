package com.dfbz.bugSystem;

import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication(exclude = PageHelperAutoConfiguration.class)
@EnableTransactionManagement
@EnableEurekaClient
public class BugSystemApplication {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(BugSystemApplication.class, args);
    }

}
