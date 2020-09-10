package com.dfbz.bugSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/***
 *
 */
@SpringBootApplication
@EnableEurekaServer
public class BugEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(BugEurekaServerApplication.class, args);
    }

}
