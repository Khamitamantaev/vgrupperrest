package com.vgrupper.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "COMMENTS")
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @Column(length = 32)
    private String message;

    @Column(length = 1024)
    private String Code;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_message")
    private Message messages;

    public Comments() {
    }

    public Comments( String message, String code) {
        this.message = message;
        Code = code;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Message getVgruppermessage() {
        return messages;
    }

    public void setVgrupperMessage(Message vgruppermessage) {
        this.messages = vgruppermessage;
    }
}
