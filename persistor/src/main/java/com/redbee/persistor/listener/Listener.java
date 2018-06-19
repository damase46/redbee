package com.redbee.persistor.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class Listener {

    private static final Logger logger = LoggerFactory.getLogger(Listener.class);

    @RabbitListener(queues = "queuePersist")
    public void receiveMessage(final Message message) {
        logger.info("Received message : {}", message.toString());
    }

}
