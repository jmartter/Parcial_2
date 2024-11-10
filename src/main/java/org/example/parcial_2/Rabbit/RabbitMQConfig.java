package org.example.parcial_2.Rabbit;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public Queue csvQueue() {
        return new Queue("csvQueue", true);
    }
}