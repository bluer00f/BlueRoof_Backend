package test.hotpotato.blueroof_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.hotpotato.blueroof_test.model.information.Building;

import java.util.List;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {

    // 회원(or 배우자)의 주거용 건물 여부
    List<Building> findAllByUserIdAndHouseAndBuildingTypeNot(Long userId, int house, String buildingType);

    List<Building> findAllByUserId(Long id);

    void deleteAllByUserId(Long id);
}