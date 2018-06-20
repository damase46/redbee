package com.redbee.persistor.listener;

import com.redbee.persistor.model.Comentario;
import com.redbee.persistor.model.Hotel;
import com.redbee.persistor.repository.HotelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HotelListener {

    private static final Logger logger = LoggerFactory.getLogger(Listener.class);

    @Autowired
    HotelRepository hotelRepository;

    @RabbitListener(queues = "queueHotel")
    public void update(final Hotel hotel) {

        logger.info("Received hotel : {}", hotel.toString());
        hotelRepository.save(hotel);
    }

    @RabbitListener(queues = "queueCustomHotel")
    public void updateCustom(final Hotel hotel) {

        logger.info("Received hotel : {}", hotel.toString());
        hotelRepository.saveCustom(hotel);
    }
}
