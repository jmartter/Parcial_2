package org.example.parcial_2.Rabbit;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

@Component
public class RabbitMQListener {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQListener.class);

    @Autowired
    private RestTemplate restTemplate;

    @RabbitListener(queues = "csvQueue")
    public void subscribeToCsvQueue(String message) {
        if ("CSV loading completed".equals(message)) {
            logger.info("CSV loading completed");
        } else {
            logger.info("Received message from RabbitMQ: {}", message);
        }
        restTemplate.postForObject("http://localhost:8080/notify", message, String.class);
    }
}