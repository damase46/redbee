package com.redbee.persistor;

import com.mongodb.Mongo;
import com.redbee.persistor.model.CustomSequense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class CustomSequenseService {

    @Autowired
    MongoTemplate mongoTemplate;

    public Long getLastId(String seq){
        CustomSequense count = mongoTemplate.findAndModify(
                Query.query(Criteria.where("_id").is(seq)),
                new Update().inc("seq", 1), new FindAndModifyOptions().returnNew(true), CustomSequense.class);
        return count.getSeq();
    }
}
