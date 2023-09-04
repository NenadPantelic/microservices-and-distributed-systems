package com.np.customer;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ClientTemplateConfig {

    @Bean
    @LoadBalanced // by default uses round-robin to distribute requests
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
