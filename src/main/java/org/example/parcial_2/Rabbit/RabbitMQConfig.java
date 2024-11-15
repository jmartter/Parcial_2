package org.example.parcial_2.Rabbit;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    // Método que define una cola llamada "csvQueue" y la configura como duradera
    @Bean
    public Queue csvQueue() {
        return new Queue("csvQueue", true);
    }
}