package com.vgrupper.demo.repositories;

import com.vgrupper.demo.entity.Message;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VgrupperRepository extends JpaRepository<Message, Long> {

    @Override
    boolean existsById(Long id);

    List<Message> findAll();

    Optional<Message> findById(Long id);

    Message save(Message message);


    Message findByTitle(String name);

    @Override
    void deleteById(Long aLong);

}
