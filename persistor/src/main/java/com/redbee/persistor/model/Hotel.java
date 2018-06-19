package com.redbee.persistor.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.validation.constraints.NotNull;
import java.util.List;

public class Hotel {

    @Id
    @NotNull
    private Long id;

    private String name;

    private Integer stars;

    private Double price;

    @DBRef
    private List<Comentario> comments;

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

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Comentario> getComments() {
        return comments;
    }

    public void setComments(List<Comentario> comments) {
        this.comments = comments;
    }
}
