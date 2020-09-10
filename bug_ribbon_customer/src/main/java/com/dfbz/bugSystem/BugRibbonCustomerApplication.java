package com.dfbz.bugSystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
/*@EnableHystrixDashboard
@EnableHystrix*/
//@EnableCircuitBreaker
public class BugRibbonCustomerApplication {

    /*@Bean
    public ServletRegistrationBean getServlet(){
        HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
        registrationBean.setLoadOnStartup(1);
        registrationBean.addUrlMappings("/hystrix.stream");
        registrationBean.setName("HystrixMetricsStreamServlet");
        return registrationBean;
    }
*/


    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return  new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(BugRibbonCustomerApplication.class, args);
    }

}
