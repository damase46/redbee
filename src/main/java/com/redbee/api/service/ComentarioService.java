package com.redbee.api.service;

import com.redbee.api.conf.RabbitConf;
import com.redbee.api.model.Comentario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

@Service
public class ComentarioService {

    private static final Logger logger = LoggerFactory.getLogger(PersistService.class);

    @Autowired
    RabbitTemplate rabbitTemplate;

    public void updateComment(Comentario comentario){
        logger.info("Call update comentario service");

        verifyIntegrity(comentario).thenAccept(x -> sendToPersist(x));

    }

    private CompletableFuture<Comentario> verifyIntegrity(Comentario comentario){

        return CompletableFuture.supplyAsync(() -> {
            //TODO verify id
            return comentario ;
        });

    }

    private void sendToPersist(Comentario comentario) {
        rabbitTemplate.convertAndSend(RabbitConf.QUEUE_COMMENT, comentario);
    }

}
