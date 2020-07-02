package com.vgrupper.demo.controllers;

import com.vgrupper.demo.entity.Message;
import com.vgrupper.demo.exception.VgrupperNotFoundException;
import com.vgrupper.demo.repositories.VgrupperRepository;
import com.vgrupper.demo.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin( maxAge = 3600)
@RestController
public class VgrupperController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private VgrupperRepository vgrupperRepository;

    @GetMapping("/vgruppers")
    public List<Message> getAllVgrupper() {


        return vgrupperRepository.findAll();
    }

    @PostMapping("/vgruppers")
    public ResponseEntity<?> createVgrupper(@Valid @RequestBody Message message) {
       Message message1 = vgrupperRepository.save(message);

        return new ResponseEntity<Message>(message1, HttpStatus.CREATED);
    }

    @GetMapping("/vgruppers/{id}")
    public ResponseEntity<?> getvgrupperMessage(@PathVariable(value = "id")Long vgrup_id) throws VgrupperNotFoundException {
        Message message = vgrupperRepository.findById(vgrup_id).
                orElseThrow(()->new VgrupperNotFoundException(vgrup_id));

        return new ResponseEntity<Message>(message, HttpStatus.OK);
    }



}
