package com.vgrupper.demo.controllers;

import com.vgrupper.demo.entity.Comments;
import com.vgrupper.demo.entity.Message;
import com.vgrupper.demo.exception.NotFoundException;
import com.vgrupper.demo.exception.VgrupperNotFoundException;
import com.vgrupper.demo.repositories.CommentsRepository;
import com.vgrupper.demo.repositories.VgrupperRepository;
import com.vgrupper.demo.service.VgrupperService;
import com.vgrupper.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin( maxAge = 3600)
@RestController
public class VgrupperController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private VgrupperRepository vgrupperRepository;

    @Autowired
    private VgrupperService vgrupperService;

    @Autowired
    private CommentsRepository commentsRepository;

    @GetMapping("/vgruppers")
    public List<Message> getAllVgrupper() {


        return vgrupperRepository.findAll();
    }

    @PostMapping("/vgruppers")
    public ResponseEntity<?> createVgrupper(@Valid @RequestBody Message newMessage) {
        Message message = vgrupperService.saveCommentsAndMesssages(newMessage.getCommentsList(),newMessage);
        return new ResponseEntity<Message>(message, HttpStatus.CREATED);
    }

    @GetMapping("/vgruppers/{id}")
    public ResponseEntity<?> getvgrupperMessage(@PathVariable(value = "id")Long vgrup_id) throws VgrupperNotFoundException {
        Message message = vgrupperRepository.findById(vgrup_id).
                orElseThrow(()->new VgrupperNotFoundException(vgrup_id));

        return new ResponseEntity<Message>(message, HttpStatus.OK);
    }

    @DeleteMapping("vgruppers/{id}")
    public ResponseEntity<String> deleteMessage(@PathVariable Long id){
        vgrupperService.deleteMessage(id);
        return new ResponseEntity<>("Your message is deleted now!", HttpStatus.OK);
    }



//    @PutMapping("/vgruppers/{id}")
//    public ResponseEntity<Message> updateMessage(@PathVariable("id") long messageId, @RequestBody Message newMessage) {
//        Message message = vgrupperService.updateMessageAndComments(newMessage,messageId);
//        return new ResponseEntity<Message>(message, HttpStatus.OK);
//    }

}

//post
//        List<Comments> commentsList = newMessage.getCommentsList();
//        for(Comments comments : commentsList){
//            Comments comment = new Comments(comments.getMessage(),comments.getCode());
//            commentsRepository.save(comment);
//        }
////        List<Comments> commentsLists1 = commentsRepository.findAll();
//        Message message = new Message(newMessage.getId(),newMessage.getTitle(),newMessage.getDescription(),commentsList);
//        Message message1 = vgrupperRepository.save(message);
//--------------------------------------------------------------------------------------
//put
//        List<Comments> commentsList = newMessage.getCommentsList();
//        for(Comments comments: commentsList){
//            Comments comment = commentsRepository.findById(comments.getId()).orElse(new Comments(comments.getMessage(),comments.getCode()));
//            commentsRepository.save(comment);
//        }
//        Message message = vgrupperRepository.findById(messageId).orElse(new Message());
//        if (!vgrupperRepository.existsById(messageId)) {
//            return new ResponseEntity<Message>(message, HttpStatus.NOT_FOUND);
//        }
//        List<Comments> commentsList1 = commentsRepository.findAll();
//
//        message.setId(messageId);
//        message.setCommentsList(commentsList1);
//        message.setDescription(newMessage.getDescription());
//        message.setTitle(newMessage.getTitle());
//
//
//        vgrupperRepository.save(message);