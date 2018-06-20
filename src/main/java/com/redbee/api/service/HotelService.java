package com.redbee.api.service;

import com.redbee.api.conf.RabbitConf;
import com.redbee.api.model.Comentario;
import com.redbee.api.model.Hotel;
import com.redbee.persistor.repository.ComentarioRepository;
import com.redbee.persistor.repository.HotelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class HotelService {

    private static final Logger logger = LoggerFactory.getLogger(HotelService.class);

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    HotelRepository hotelRepository;

    public void updateHotel(Hotel hotel){
        logger.info("Call update comentario service");
        sendToPersist(hotel, RabbitConf.QUEUE_HOTEL);

    }

    public void updateCustomHotel(Hotel hotel){
        logger.info("Call update comentario service");
        sendToPersist(hotel, RabbitConf.QUEUE_CUSTOM_HOTEL);

    }

    private void sendToPersist(Hotel hotel, String queue) {
        rabbitTemplate.convertAndSend(queue, hotel);
    }
}
