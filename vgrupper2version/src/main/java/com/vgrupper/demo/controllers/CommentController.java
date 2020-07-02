package com.vgrupper.demo.controllers;

import com.vgrupper.demo.entity.Comments;
import com.vgrupper.demo.entity.Message;
import com.vgrupper.demo.repositories.CommentsRepository;
import com.vgrupper.demo.repositories.VgrupperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("vgrupper/{id}")
public class CommentController {

    @Autowired
    private VgrupperRepository vgrupperRepository;

    @Autowired
    private CommentsRepository commentsRepository;

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Message putMessage(@RequestBody Message newMessage, @PathVariable Long id) {


        return vgrupperRepository.findById(id).map(message -> {

            message.setTitle(newMessage.getTitle());
            message.setDescription(newMessage.getDescription());
            message.setCommentsList(newMessage.getCommentsList());
            return vgrupperRepository.save(message);

        }).orElseGet(()-> {
            newMessage.setId(id);
            return vgrupperRepository.save(newMessage);
        });

    }
}
