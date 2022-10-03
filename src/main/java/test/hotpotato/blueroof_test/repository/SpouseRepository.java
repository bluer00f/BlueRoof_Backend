package test.hotpotato.blueroof_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.hotpotato.blueroof_test.model.information.Spouse;

@Repository
public interface SpouseRepository extends JpaRepository<Spouse, Long> {

    Spouse findByUserId(Long id);

    void deleteByUserId(Long id);
}