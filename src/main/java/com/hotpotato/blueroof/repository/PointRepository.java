package com.hotpotato.blueroof.repository;

import com.hotpotato.blueroof.service.model.result.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PointRepository extends JpaRepository<Point, Long> {

}
