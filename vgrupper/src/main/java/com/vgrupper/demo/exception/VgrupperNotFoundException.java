package com.vgrupper.demo.exception;

public class VgrupperNotFoundException extends Exception {

    private long vgrupper_id;

    public VgrupperNotFoundException(long vgrupper_id){
        super(String.format("Vgrupper is not found with id: '%s'" , vgrupper_id));
    }
}
