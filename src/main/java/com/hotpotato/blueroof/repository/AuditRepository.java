package com.hotpotato.blueroof.repository;

import com.hotpotato.blueroof.model.result.Audit;
import com.hotpotato.blueroof.model.user.RefreshToken;
import com.hotpotato.blueroof.model.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuditRepository extends JpaRepository<Audit, Long> {

}
