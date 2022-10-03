package test.hotpotato.blueroof_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.hotpotato.blueroof_test.model.result.Audit;

@Repository
public interface AuditRepository extends JpaRepository<Audit, Long> {

}