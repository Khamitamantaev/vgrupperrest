package com.vgrupper.demo.repositories;

import com.vgrupper.demo.entity.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommentsRepository  extends JpaRepository<Comments, Long> {

    @Override
    boolean existsById(Long id);

    List<Comments> findAll();

    Optional<Comments> findById(Long id);

    @Override
    @Query("delete from COMMENTS c where  ")
    void deleteById(@Param("id") Long aLong);
}
