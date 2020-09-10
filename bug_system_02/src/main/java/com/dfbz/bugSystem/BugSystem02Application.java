package com.dfbz.bugSystem;

import com.github.pagehelper.autoconfigure.PageHelperAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = PageHelperAutoConfiguration.class)
@EnableTransactionManagement
@EnableEurekaClient
public class BugSystem02Application {

    public static void main(String[] args) {
        SpringApplication.run(BugSystem02Application.class, args);
    }

}
