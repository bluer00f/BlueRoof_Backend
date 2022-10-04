package com.hotpotato.blueroof.repository;

import com.hotpotato.blueroof.service.model.information.Land;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LandRepository extends JpaRepository<Land, Long> {


    List<Land> findAllByUserId(Long id);

    void deleteAllByUserId(Long id);
}
