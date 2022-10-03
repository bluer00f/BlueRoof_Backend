package test.hotpotato.blueroof_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.hotpotato.blueroof_test.model.information.BankBook;

import java.util.List;

@Repository
public interface BankBookRepository extends JpaRepository<BankBook, Long> {


    List<BankBook> findAllByUserId(Long id);

    void deleteAllByUserId(Long id);
}