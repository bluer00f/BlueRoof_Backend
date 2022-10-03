package test.hotpotato.blueroof_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.hotpotato.blueroof_test.model.information.Income;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {


    Income findByUserId(Long id);
}
