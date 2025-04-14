package com.everhealth.integration.zbfspublisher.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ZuoraConfig {

//	@Value("${zuora.client.id}")
//	private String clientId;
//
//	@Value("${zuora.client.secret}")
//	private String clientSecret;
//
//	@Value("${zuora.base.url}")
//	private String baseUrl;
//
//	@Bean
//	public RestTemplate restTemplate() {
//		return new RestTemplate();
//	}
//
//	public String getClientId() {
//		return clientId;
//	}
//
//	public String getClientSecret() {
//		return clientSecret;
//	}
//
//	public String getBaseUrl() {
//		return baseUrl;
//	}

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
