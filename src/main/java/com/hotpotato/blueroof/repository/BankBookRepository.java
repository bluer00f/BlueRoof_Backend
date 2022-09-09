package com.hotpotato.blueroof.repository;

import com.hotpotato.blueroof.model.information.BankBook;
import com.hotpotato.blueroof.model.information.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankBookRepository extends JpaRepository<BankBook, Long> {


    List<BankBook> findAllByUserId(Long id);

    void deleteAllByUserId(Long id);
}
