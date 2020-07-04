package com.vgrupper.demo.repositories;

import com.vgrupper.demo.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

//    @Override
//    boolean existsById(Long id);
//
//    List<Message> findAll();
//
//    Optional<Message> findById(Long id);
//
//    Message save(Message message);
//
//
//    Message findByTitle(String name);
//
//    @Override
//    void deleteById(Long aLong);

}
