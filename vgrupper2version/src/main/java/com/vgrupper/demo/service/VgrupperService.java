package com.vgrupper.demo.service;

import com.vgrupper.demo.entity.Comments;
import com.vgrupper.demo.entity.Message;
import com.vgrupper.demo.exception.NotFoundException;
import com.vgrupper.demo.repositories.CommentsRepository;
import com.vgrupper.demo.repositories.VgrupperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VgrupperService {

    @Autowired
    private VgrupperRepository vgrupperRepository;

    @Autowired
    private CommentsRepository commentsRepository;

    public Optional<Message> getMessageById(long id){
        Optional<Message> message = vgrupperRepository.findById(id);
        if (message == null){
            throw  new NotFoundException(id);
        }
        return message;
    }

    public Message getMessageByTitle(String name){
        Message message = vgrupperRepository.findByTitle(name);
        if(message ==null){
            throw new NotFoundException(name);
        }
        return message;
    }

    public Message saveMessage(Message newMessage) {
        saveCommentsAndMesssages(newMessage.getCommentsList(),newMessage);
        Message message = new Message(newMessage.getTitle(),newMessage.getDescription());
        Message message1 = vgrupperRepository.save(message);
        return message1;
    }

    public Message saveCommentsAndMesssages(List<Comments> comments , Message newMessage){
        for(Comments comments1: comments){
            Comments comment = commentsRepository.save(comments1);
        }
        List<Comments> commentsList = commentsRepository.findAll();

        Message message = new Message(newMessage.getId(),newMessage.getTitle(), newMessage.getDescription(),commentsList);

        return vgrupperRepository.save(message);

    }

    public void deleteMessage(Long id){
        Optional<Message> message = vgrupperRepository.findById(id);
        if(!message.isPresent()){
            throw new NotFoundException(id);
        }
        vgrupperRepository.deleteById(id);
    }
    public void deleteComment(Long id){
        Optional<Comments> comments = commentsRepository.findById(id);
        if (!comments.isPresent()) {
            throw new NotFoundException(id);
        }
        commentsRepository.deleteById(id);

    }



//    public Message updateMessageAndComments(Message newMessage, Long id) {
//        Message message = vgrupperRepository.findById(id).orElse(new Message());
//        message.setId(newMessage.getId());
//        message.setTitle(newMessage.getTitle());
//        message.setDescription(newMessage.getDescription());
//
//        List<Comments> comments = newMessage.getCommentsList();
//        for(Comments comments1: comments){
//            Comments comments2 = commentsRepository.findById(comments1.getId()).orElse(new Comments());
//            comments2.setId(comments1.getId());
//            comments2.setMessage(comments1.getMessage());
//            comments2.setCode(comments1.getCode());
//            commentsRepository.save(comments2);
//        }
//
//        List<Comments> commentsList = commentsRepository.findAll();
//
//        message.setCommentsList(commentsList);
//
//
//        return vgrupperRepository.save(message);
//    }


}


