package com.redbee.persistor.impl;

import com.mongodb.client.result.UpdateResult;
import com.redbee.persistor.CustomSequenseService;
import com.redbee.persistor.customer.HotelRepositoryCustomer;
import com.redbee.persistor.model.Comentario;
import com.redbee.persistor.model.CustomSequense;
import com.redbee.persistor.model.Hotel;
import com.redbee.persistor.repository.ComentarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class HotelRepositoryImpl implements HotelRepositoryCustomer {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    MongoOperations mongoOperations;

    @Autowired
    ComentarioRepository comentarioRepository;

    @Autowired
    CustomSequenseService customSequenseService;

    @Override
    public void saveCustom(Hotel hotel) {

        if(hotel.getId() == null){
            Long seq = customSequenseService.getLastId("hotelSequense");
            hotel.setId(seq);
        }

        Criteria criteria = Criteria.where("_id").is(hotel.getId());

        Update update = new Update();

        if(hotel.getComments() != null && !hotel.getComments().isEmpty()){

            List<Comentario> comments = new ArrayList<>();
            hotel.getComments().forEach(comentario -> {
                Comentario newComment = new Comentario();
                if(comentario.getId() == null){
                    comentario.setId(comentarioRepository.saveCustom(comentario));
                }
                newComment.setId(comentario.getId());
                comments.add(comentario);
            });
            update.addToSet("comments", comments);
        }

        if(hotel.getName() != null){
            update.set("name", hotel.getName());
        }

        if(hotel.getPrice() != null){
            update.set("price", hotel.getPrice());
        }

        if(hotel.getStars() != null){
            update.set("stars", hotel.getStars());
        }

        Query query = Query.query(criteria);
        mongoTemplate.upsert(query, update , Hotel.class);
    }
}
