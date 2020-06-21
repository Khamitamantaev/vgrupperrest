package com.vgrupper.demo.entity;



import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "VGRUPPERMESSAGE")
public class VgrupperMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String title;

    private String description;

    @OneToMany(mappedBy = "vgruppermessages") //сюда вписываем поле, которое будет в Comments
    private List<Comments> comments;

    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;

    public VgrupperMessage() {
    }

    public VgrupperMessage(long id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
