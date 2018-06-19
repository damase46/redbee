package com.redbee.persistor.listener;

import com.redbee.persistor.model.Comentario;
import com.redbee.persistor.repository.ComentarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ComentarioListener {

    private static final Logger logger = LoggerFactory.getLogger(ComentarioListener.class);

    @Autowired
    ComentarioRepository comentarioRepository;

    @RabbitListener(queues = "queueComment")
    public void receiveMessage(final Comentario comment) {

        logger.info("Received comment : {}", comment.toString());
        comentarioRepository.save(comment);
    }
}
