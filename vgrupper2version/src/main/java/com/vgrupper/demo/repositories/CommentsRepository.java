package com.vgrupper.demo.repositories;

import com.vgrupper.demo.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository  extends JpaRepository<Comments, Long> {

}
