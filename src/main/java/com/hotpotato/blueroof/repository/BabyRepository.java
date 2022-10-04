package com.hotpotato.blueroof.repository;

import com.hotpotato.blueroof.service.model.information.Baby;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BabyRepository extends JpaRepository<Baby, Long> {


    Baby findByUserId(Long id);

    void deleteByUserId(Long id);
}
