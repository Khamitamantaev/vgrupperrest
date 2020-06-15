package com.vgrupper.demo.repositories;

import com.vgrupper.demo.entity.VgrupperMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VgrupperRepository extends JpaRepository<VgrupperMessage, Long> {

    List<VgrupperMessage> findAll();

    VgrupperMessage save(VgrupperMessage vgrupperMessage);

    Optional<VgrupperMessage> findById(Long id);

}
