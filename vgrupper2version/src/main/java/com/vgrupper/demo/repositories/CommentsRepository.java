package com.vgrupper.demo.repositories;

import com.vgrupper.demo.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentsRepository  extends JpaRepository<Comment, Long> {

    Page<Comment> findByPostId(Long postId, Pageable pageable);
    Optional<Comment> findByIdAndPostId(Long id, Long postId);

}
