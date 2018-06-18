package com.redbee.persistor.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Document(collection = "comment")
public class Comentario{

    @Id
    private Long id;

    private String name;

    private String comment;

    @DBRef
    private List<Comentario> replies;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<Comentario> getReplies() {
        return replies;
    }

    public void setReplies(List<Comentario> replies) {
        this.replies = replies;
    }
}
