package com.vgrupper.demo.controllers;


import com.vgrupper.demo.entity.Post;
import com.vgrupper.demo.exception.ResourceNotFoundException;
import com.vgrupper.demo.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class PostController {

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/posts")
    public Page<Post> getAllPosts(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @PostMapping("/posts")
    public Post createPost(@Valid @RequestBody Post post) {
        return postRepository.save(post);
    }

    @PutMapping("/posts/{postId}")
    public Post updatePost(@PathVariable Long postId, @Valid @RequestBody Post postRequest) {
        return postRepository.findById(postId).map(post -> {
            post.setTitle(postRequest.getTitle());
            post.setDescription(postRequest.getDescription());
            post.setContent(postRequest.getContent());
            return postRepository.save(post);
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }


    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        return postRepository.findById(postId).map(post -> {
            postRepository.delete(post);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("PostId " + postId + " not found"));
    }

}


//    @PutMapping("/vgruppers/{id}")
//    public ResponseEntity<Message> updateMessage(@PathVariable("id") long messageId, @RequestBody Message newMessage) {
//        Message message = vgrupperService.updateMessageAndComments(newMessage,messageId);
//        return new ResponseEntity<Message>(message, HttpStatus.OK);
//    }


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