package com.vgrupper.demo.controllers;

import com.vgrupper.demo.entity.Comments;
import com.vgrupper.demo.entity.Message;
import com.vgrupper.demo.repositories.CommentsRepository;
import com.vgrupper.demo.repositories.VgrupperRepository;
import com.vgrupper.demo.service.VgrupperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentsRepository commentsRepository;

    @DeleteMapping("vgruppers/messages/{com_id}")
    public ResponseEntity<String> deleteMessageById(@PathVariable("com_id") Long id){
        commentsRepository.deleteById(id);
        return new ResponseEntity<>("Your comments delete now!", HttpStatus.OK);
    }
}
