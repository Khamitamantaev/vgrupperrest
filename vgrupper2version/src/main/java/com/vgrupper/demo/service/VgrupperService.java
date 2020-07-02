package com.vgrupper.demo.service;

import com.vgrupper.demo.entity.Message;
import com.vgrupper.demo.repositories.VgrupperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VgrupperService {

    @Autowired
    private VgrupperRepository vgrupperRepository;


}
