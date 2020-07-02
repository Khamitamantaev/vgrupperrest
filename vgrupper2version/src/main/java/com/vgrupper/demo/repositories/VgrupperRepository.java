package com.vgrupper.demo.repositories;

import com.vgrupper.demo.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VgrupperRepository extends JpaRepository<Message, Long> {

    List<Message> findAll();

    Message save(Message message);

    Optional<Message> findById(Long id);



}
