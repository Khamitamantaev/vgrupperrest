package com.vgrupper.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException  extends  RuntimeException{
    public NotFoundException(Long id){
        super("Message with id=" + id + "not found Khamit!");
    }

    public NotFoundException(String name) {
        super("Message with name =" + name + "not found Khamit");
    }

}
