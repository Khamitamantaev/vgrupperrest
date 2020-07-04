//package com.vgrupper.demo.service;
//
//import com.vgrupper.demo.entity.Comment;
//import com.vgrupper.demo.entity.Post;
//import com.vgrupper.demo.exception.NotFoundException;
//import com.vgrupper.demo.repositories.CommentsRepository;
//import com.vgrupper.demo.repositories.PostRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class VgrupperService {
//
//    @Autowired
//    private PostRepository postRepository;
//
//    @Autowired
//    private CommentsRepository commentsRepository;
//
//    public Optional<Post> getMessageById(long id){
//        Optional<Post> message = postRepository.findById(id);
//        if (message == null){
//            throw  new NotFoundException(id);
//        }
//        return message;
//    }
//
//    public Post getMessageByTitle(String name){
//        Post post = postRepository.findByTitle(name);
//        if(post ==null){
//            throw new NotFoundException(name);
//        }
//        return post;
//    }
//
//    public Post saveMessage(Post newPost) {
//        saveCommentsAndMesssages(newPost.getCommentsList(), newPost);
//        Post post = new Post(newPost.getTitle(), newPost.getDescription());
//        Post post1 = postRepository.save(post);
//        return post1;
//    }
//
//    public Post saveCommentsAndMesssages(List<Comment> comments , Post newPost){
//        for(Comment comment1 : comments){
//            Comment comment = commentsRepository.save(comment1);
//        }
//        List<Comment> commentList = commentsRepository.findAll();
//
//        Post post = new Post(newPost.getId(), newPost.getTitle(), newPost.getDescription(), commentList);
//
//        return postRepository.save(post);
//
//    }
//
//    public void deleteMessage(Long id){
//        Optional<Post> message = postRepository.findById(id);
//        if(!message.isPresent()){
//            throw new NotFoundException(id);
//        }
//        postRepository.deleteById(id);
//    }
//    public void deleteComment(Long id){
//        Optional<Comment> comments = commentsRepository.findById(id);
//        if (!comments.isPresent()) {
//            throw new NotFoundException(id);
//        }
//        commentsRepository.deleteById(id);
//
//    }
//
//
//
////    public Message updateMessageAndComments(Message newMessage, Long id) {
////        Message message = vgrupperRepository.findById(id).orElse(new Message());
////        message.setId(newMessage.getId());
////        message.setTitle(newMessage.getTitle());
////        message.setDescription(newMessage.getDescription());
////
////        List<Comments> comments = newMessage.getCommentsList();
////        for(Comments comments1: comments){
////            Comments comments2 = commentsRepository.findById(comments1.getId()).orElse(new Comments());
////            comments2.setId(comments1.getId());
////            comments2.setMessage(comments1.getMessage());
////            comments2.setCode(comments1.getCode());
////            commentsRepository.save(comments2);
////        }
////
////        List<Comments> commentsList = commentsRepository.findAll();
////
////        message.setCommentsList(commentsList);
////
////
////        return vgrupperRepository.save(message);
////    }
//
//
//}
//
//
