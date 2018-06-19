package com.redbee.persistor.repository;


import com.redbee.persistor.model.Comentario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComentarioRepository extends MongoRepository<Comentario, Long> { }
