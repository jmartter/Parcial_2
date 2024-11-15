package org.example.parcial_2.Front;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {

    // Método que define un bean de RestTemplate
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}