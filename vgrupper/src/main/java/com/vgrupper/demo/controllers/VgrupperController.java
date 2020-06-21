package com.vgrupper.demo.controllers;

import com.vgrupper.demo.entity.User;
import com.vgrupper.demo.entity.VgrupperMessage;
import com.vgrupper.demo.exception.VgrupperNotFoundException;
import com.vgrupper.demo.repositories.UserRepository;
import com.vgrupper.demo.repositories.VgrupperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin( origins = "http://khamitamantaev.com", maxAge = 3600)
@RestController
public class VgrupperController {

    @Autowired
    private VgrupperRepository vgrupperRepository;

    @GetMapping("/vgruppers")
    public List<VgrupperMessage> getAllVgrupper() {
        return vgrupperRepository.findAll();
    }

    @PostMapping("/vgruppers")
    public ResponseEntity<?> createVgrupper(@Valid @RequestBody VgrupperMessage vgrupperMessage) {
       VgrupperMessage vgrupperMessage1 = vgrupperRepository.save(vgrupperMessage);

        return new ResponseEntity<VgrupperMessage>(vgrupperMessage1, HttpStatus.CREATED);
    }

    @GetMapping("/vgruppers/{id}")
    public ResponseEntity<?> getvgrupperMessage(@PathVariable(value = "id")Long vgrup_id) throws VgrupperNotFoundException {
        VgrupperMessage vgrupperMessage = vgrupperRepository.findById(vgrup_id).
                orElseThrow(()->new VgrupperNotFoundException(vgrup_id));

        return new ResponseEntity<VgrupperMessage>( vgrupperMessage, HttpStatus.OK);
    }


}
