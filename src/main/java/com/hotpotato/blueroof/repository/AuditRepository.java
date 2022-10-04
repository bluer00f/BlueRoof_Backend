package com.hotpotato.blueroof.repository;

import com.hotpotato.blueroof.service.model.result.Audit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRepository extends JpaRepository<Audit, Long> {

}
