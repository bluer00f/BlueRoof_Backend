package com.hotpotato.blueroof.repository;

import com.hotpotato.blueroof.model.information.Spouse;
import com.hotpotato.blueroof.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpouseRepository extends JpaRepository<Spouse, Long> {

    Spouse findByUserId(Long id);
}
