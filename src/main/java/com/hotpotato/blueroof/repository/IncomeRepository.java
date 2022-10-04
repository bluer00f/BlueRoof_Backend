package com.hotpotato.blueroof.repository;

import com.hotpotato.blueroof.service.model.information.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {


    Income findByUserId(Long id);
}
