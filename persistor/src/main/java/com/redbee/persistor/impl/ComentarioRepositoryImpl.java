package com.redbee.persistor.impl;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBRef;
import com.mongodb.client.result.UpdateResult;
import com.redbee.persistor.CustomSequenseService;
import com.redbee.persistor.customer.ComentarioRepositoryCustomer;
import com.redbee.persistor.model.Comentario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ComentarioRepositoryImpl implements ComentarioRepositoryCustomer {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    MongoOperations mongoOperations;


    @Autowired
    CustomSequenseService customSequenseService;

    @Override
    public Long saveCustom(Comentario comentario) {

        if(comentario.getId() == null){
            Long seq = customSequenseService.getLastId("commentSequense");
            comentario.setId(seq);
        }

        Criteria criteria = Criteria.where("_id").is(comentario.getId());
        Query query = Query.query(criteria);

        Update update = new Update();

        if(comentario.getReplies() != null && !comentario.getReplies().isEmpty()){
            List<DBRef> replies = new ArrayList<DBRef>();
            comentario.getReplies().forEach(reply -> {
                if(reply.getId() == null){
                    reply.setId(saveCustom(comentario));
                }
                replies.add(new DBRef("comment",reply.getId()));
            });
            update.addToSet("replies").each(replies);
        }

        if(comentario.getDate() != null){
            update.set("date", comentario.getDate());
        }
        if(comentario.getName() != null){
            update.set("name", comentario.getName());
        }
        if(comentario.getComment() != null){
            update.set("comment", comentario.getComment());
        }

        mongoTemplate.upsert(query, update, "comment");

        return comentario.getId();
    }

}
