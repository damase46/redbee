package com.redbee.api;

import com.redbee.persistor.model.Comentario;
import com.redbee.persistor.model.Hotel;
import com.redbee.persistor.repository.ComentarioRepository;
import com.redbee.persistor.repository.HotelRepository;
import org.apache.commons.math3.random.RandomDataGenerator;
import org.apache.tomcat.jni.Thread;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@RunWith(SpringRunner.class)
@ComponentScan(basePackages = "com.redbee")
@SpringBootTest
public class PersistorTest {

    private static final Logger logger = LoggerFactory.getLogger(PersistorTest.class);

    private static final int RANDOM_HOTEL = 3;
    private static final int RANDOM_COMMENT = 2;

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    ComentarioRepository comentarioRepository;

    @Test
    public void initializeHotel(){
        List<Hotel> hotels = new ArrayList<Hotel>();

        IntStream.rangeClosed(1, RANDOM_HOTEL)
                .forEach(i -> hotels.add(addHotel(i)));

        hotelRepository.saveAll(hotels);

    }

    @Test
    public void initializeComment(){
        List<Comentario> comments = new ArrayList<Comentario>();

        IntStream.rangeClosed(1, RANDOM_COMMENT)
                .forEach(i -> comments.add(addComment(i)));

        comentarioRepository.saveAll(comments);
    }

    @Test
    public void findByIdComment(){

        Optional<Comentario> op = comentarioRepository.findById(1529348922229L);
        Comentario com = op.get();
        logger.info("");
    }

    private Hotel addHotel(int i) {
        Hotel hotel = new Hotel();
        hotel.setId(new Long(i));
        hotel.setName("Random: "+i);
        hotel.setStars(new RandomDataGenerator().nextInt(1,5));
        hotel.setPrice(new RandomDataGenerator().nextUniform(500D, 1000D));
        return hotel;
    }

    private Comentario addComment(int i){

        try {
            TimeUnit.MILLISECONDS.sleep(5);
        }catch(InterruptedException ex){
        }
        Comentario comentario = new Comentario();
        comentario.setDate(new Date());
        comentario.setComment("Random: "+i);
        comentario.setName("Random Comment: "+i);
        comentario.setId(comentario.getDate().getTime());
        return comentario;
    }
}
