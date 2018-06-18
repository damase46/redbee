package com.redbee.api.service;

import com.redbee.api.conf.RabbitConf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class PersistService {

    private static final Logger logger = LoggerFactory.getLogger(PersistService.class);

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void delegatePersist(String entity){
        logger.info("Call consumer persist");
        rabbitTemplate.convertAndSend(RabbitConf.EXCHANGE_NAME, RabbitConf.ROUTING_KEY, entity);
    }
}
