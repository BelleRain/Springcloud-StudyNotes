package com.demo.springcloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication(exclude ={ DataSourceAutoConfiguration.class, DataSourceTransactionManagerAutoConfiguration.class})
public class OrderPayment80 {
    public static void main(String[] args){
      SpringApplication.run(OrderPayment80.class,args);
    }
}
