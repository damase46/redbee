package com.redbee.api.service;

import com.redbee.api.conf.RabbitConf;
import com.redbee.api.model.Comentario;
import com.redbee.persistor.repository.ComentarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.CompletableFuture;

@Service
public class ComentarioService {

    private static final Logger logger = LoggerFactory.getLogger(ComentarioService.class);

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    ComentarioRepository comentarioRepository;

    public void updateComment(Comentario comentario){

        logger.info("Call update comentario service");
        sendToPersist(comentario);
    }

    private void sendToPersist(Comentario comentario) {
        rabbitTemplate.convertAndSend(RabbitConf.QUEUE_COMMENT, comentario);

    }

}
