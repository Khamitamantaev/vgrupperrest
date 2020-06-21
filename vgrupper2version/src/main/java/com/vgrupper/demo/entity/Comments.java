package com.vgrupper.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "COMMENTS")
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @Column(nullable = false, length = 32)
    private String message;

    @Column(length = 1024)
    private String Code;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_vgruppermessage")
    private VgrupperMessage vgruppermessages;

    public Comments() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public VgrupperMessage getVgruppermessage() {
        return vgruppermessages;
    }

    public void setVgrupperMessage(VgrupperMessage vgruppermessage) {
        this.vgruppermessages = vgruppermessage;
    }
}
