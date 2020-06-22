package com.vgrupper.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "USER")
public class User {

    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    private String password;

    @OneToMany(mappedBy = "user") //сюда вписываем поле, которое будет в VgrupperMessage
    private List<Message> messages;

    @OneToMany(mappedBy = "user") //сюда вписываем поле, которое будет в VgrupperMessage
    private List<Comments> comments;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Message> getVgrupperMessages() {
        return messages;
    }

    public void setVgrupperMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<Comments> getCommentsList() {
        return comments;
    }

    public void setCommentsList(List<Comments> commentsList) {
        this.comments = commentsList;
    }
}
