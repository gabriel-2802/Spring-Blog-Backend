package com.app.demo.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

import java.util.Date;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String comment;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User author;

    @ManyToOne
    @JoinColumn(name = "post_id")
    @JsonIgnore
    private Post post;

    public String getAuthorUsername() {
        return author.getUsername();
    }

    public int getPostId() {
        return post.getId();
    }

}
