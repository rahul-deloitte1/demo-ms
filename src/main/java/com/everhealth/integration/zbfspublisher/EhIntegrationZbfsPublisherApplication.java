package com.everhealth.integration.zbfspublisher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class EhIntegrationZbfsPublisherApplication {

    public static void main(String[] args) {
        SpringApplication.run(EhIntegrationZbfsPublisherApplication.class, args);
    }

}
