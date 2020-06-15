package com.vgrupper.demo.controllers;

import com.vgrupper.demo.entity.VgrupperMessage;
import com.vgrupper.demo.exception.VgrupperNotFoundException;
import com.vgrupper.demo.repositories.VgrupperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class VgrupperController {

    @Autowired
    private VgrupperRepository vgrupperRepository;


    @GetMapping("/vgruppers")
    public List getAllVgrupper() {
        return vgrupperRepository.findAll();
    }

    @PostMapping("/vgruppers")
    public VgrupperMessage createVgrupper(@Valid @RequestBody VgrupperMessage vgrupperMessage) {
        return vgrupperRepository.save(vgrupperMessage);
    }

    @GetMapping("/vgruppers/{id}")
    public VgrupperMessage getvgrupperMessage(@PathVariable(value = "id")Long vgrup_id) throws VgrupperNotFoundException {
        return vgrupperRepository.findById(vgrup_id).
                orElseThrow(()->new VgrupperNotFoundException(vgrup_id));
    }
}
