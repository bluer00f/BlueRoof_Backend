package com.hotpotato.blueroof.repository;

import com.hotpotato.blueroof.service.model.appointment.AptInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AptInfoRepository extends JpaRepository<AptInfo, Long> {

}
