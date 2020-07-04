package com.vgrupper.demo.entity;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "VGRUPPERMESSAGE")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @OneToMany(mappedBy = "messages") //сюда вписываем поле, которое будет в Comments
    private List<Comments> comments;

    @ManyToOne
    @JoinColumn(name = "id_user")
    @JsonProperty("user")
    private User user;

    public Message() {
    }

    public Message(String title, String description) {

        this.title = title;
        this.description = description;
    }


    public Message(Long id, String title, String description, List<Comments> comments) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.comments = comments;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Comments> getCommentsList() {
        return comments;
    }

    public void setCommentsList(List<Comments> commentsList) {
        this.comments = commentsList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
