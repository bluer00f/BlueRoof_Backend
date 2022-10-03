package test.hotpotato.blueroof_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.hotpotato.blueroof_test.model.information.Baby;

@Repository
public interface BabyRepository extends JpaRepository<Baby, Long> {

    Baby findByUserId(Long id);

    void deleteByUserId(Long id);
}