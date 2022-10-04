package com.hotpotato.blueroof.repository;

import com.hotpotato.blueroof.service.model.information.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {


    List<Car> findAllByUserId(Long id);

    void deleteAllByUserId(Long id);
}
