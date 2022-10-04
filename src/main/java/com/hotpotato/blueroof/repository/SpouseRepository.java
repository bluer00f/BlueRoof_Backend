package com.hotpotato.blueroof.repository;

import com.hotpotato.blueroof.service.model.information.Spouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpouseRepository extends JpaRepository<Spouse, Long> {

    Spouse findByUserId(Long id);

    void deleteByUserId(Long id);
}
