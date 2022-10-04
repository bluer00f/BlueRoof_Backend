package com.hotpotato.blueroof.service;

import com.hotpotato.blueroof.dto.*;
import com.hotpotato.blueroof.service.model.information.*;
import com.hotpotato.blueroof.service.model.type.BuildingType;
import com.hotpotato.blueroof.service.model.type.Flag;
import com.hotpotato.blueroof.service.model.user.User;
import com.hotpotato.blueroof.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AssetService {
    private final SubscriptionRepository subscriptionRepository;
    private final IncomeRepository incomeRepository;
    private final BuildingRepository buildingRepository;
    private final LandRepository landRepository;
    private final CarRepository carRepository;

    // 자산 정보 저장/수정/삭제
    @Transactional
    public AssetDto createAsset(User user, AssetDto assetDto) {

        // 무주택 시작일 저장
        LocalDate houseEndDate = assetDto.getHouseEndDate();
        if (houseEndDate != null) {
            Subscription subscription = subscriptionRepository.findByUserId(user.getId());
            if (!houseEndDate.equals(subscription.getHouseEndDate())) {
                subscription.setHouseEndDate(assetDto.getHouseEndDate());
                subscriptionRepository.save(subscription);
            }
        }

        // 소득 정보 조회
        Income income = incomeRepository.findByUserId(user.getId());

        // 건물/토지/자동차 정보 삭제 후 저장
        // 건물
        buildingRepository.deleteAllByUserId(user.getId());
        // 토지
        landRepository.deleteAllByUserId(user.getId());
        // 자동차
        carRepository.deleteAllByUserId(user.getId());

        // 소득 정보 저장
        if (income == null) {

            // 소득 정보 저장
            income = Income.builder()
                    .user(user)
                    .incomeMonthPrice(assetDto.getIncomeMonthPrice())
                    .build();

            incomeRepository.save(income);
        }

        // 소득 정보 수정
        else {

            // 소득 정보 수정
            income.setIncomeMonthPrice(assetDto.getIncomeMonthPrice());
            incomeRepository.save(income);

        }

        // 건물 정보 저장/수정
        if (assetDto.getBuildingFlag().equals(Flag.Y)) {
            for (BuildingDto buildingDto : assetDto.getBuilding()) {

                // 주거용 건물 여부
                int houseFlag = 1;
                if (buildingDto.getBuildingType().equals(BuildingType.단독주택) || buildingDto.getBuildingType().equals(BuildingType.공동주택)
                        || buildingDto.getBuildingType().equals(BuildingType.오피스텔)) houseFlag = 0;

                Building building = Building.builder()
                        .user(user)
                        .buildingType(buildingDto.getBuildingType())
                        .house(houseFlag)
                        .buildingZipcode(buildingDto.getBuildingZipcode())
                        .buildingAddress(buildingDto.getBuildingAddress())
                        .buildingArea(buildingDto.getBuildingArea())
                        .buildingPrice(buildingDto.getBuildingPrice())
                        .buildingDate(buildingDto.getBuildingDate())
                        .build();

                buildingRepository.save(building);
            }
        }

        // 토지 정보 저장/수정
        if (assetDto.getLandFlag().equals(Flag.Y)) {
            for (LandDto landDto : assetDto.getLand()) {
                Land land = Land.builder()
                        .user(user)
                        .landZipcode(landDto.getLandZipcode())
                        .landAddress(landDto.getLandAddress())
                        .landArea(landDto.getLandArea())
                        .landPrice(landDto.getLandPrice())
                        .landDate(landDto.getLandDate())
                        .build();
                landRepository.save(land);
            }
        }

        // 자동차 정보 저장/수정
        if (assetDto.getCarFlag().equals(Flag.Y)) {
            for (CarDto carDto : assetDto.getCar()) {
                Car car = Car.builder()
                        .user(user)
                        .carType(carDto.getCarType())
                        .carPrice(carDto.getCarPrice())
                        .carYear(carDto.getCarYear())
                        .build();
                carRepository.save(car);
            }
        }

        return assetDto;
    }

    // 자산 정보 조회
    @Transactional
    public AssetDto getAsset(User user) {

        // 자산 정보 조회
        // 무주택 시작일
        Subscription subscription = subscriptionRepository.findByUserId(user.getId());
        AssetDto assetDto = AssetDto.builder().buildingFlag(Flag.N).landFlag(Flag.N).carFlag(Flag.N).build();
        if (subscription != null && subscription.getHouseEndDate() != null) {
            assetDto.setHouseEndDate(subscription.getHouseEndDate());
        }

        // 소득
        Income income = incomeRepository.findByUserId(user.getId());
        if (income != null) assetDto.setIncomeMonthPrice(income.getIncomeMonthPrice());
        else assetDto.setIncomeMonthPrice(-1);

        // 건물
        List<Building> buildingList = buildingRepository.findAllByUserId(user.getId());
        if (!buildingList.isEmpty()) {
            assetDto.setBuildingFlag(Flag.Y);
            List<BuildingDto> buildingDtoList = new ArrayList<>();
            for (Building building : buildingList) {
                BuildingDto buildingDto = BuildingDto.builder()
                        .buildingType(building.getBuildingType())
                        .buildingZipcode(building.getBuildingZipcode())
                        .buildingAddress(building.getBuildingAddress())
                        .buildingArea(building.getBuildingArea())
                        .buildingPrice(building.getBuildingPrice())
                        .buildingDate(building.getBuildingDate()).build();
                buildingDtoList.add(buildingDto);
            }
            assetDto.setBuilding(buildingDtoList);
        }

        // 토지
        List<Land> landList = landRepository.findAllByUserId(user.getId());
        if (!landList.isEmpty()) {
            assetDto.setLandFlag(Flag.Y);
            List<LandDto> landDtoList = new ArrayList<>();
            for (Land land : landList) {
                LandDto landDto = LandDto.builder()
                        .landZipcode(land.getLandZipcode())
                        .landAddress(land.getLandAddress())
                        .landArea(land.getLandArea())
                        .landPrice(land.getLandPrice())
                        .landDate(land.getLandDate()).build();
                landDtoList.add(landDto);
            }
            assetDto.setLand(landDtoList);
        }

        // 자동차
        List<Car> carList = carRepository.findAllByUserId(user.getId());
        if (!carList.isEmpty()) {
            assetDto.setCarFlag(Flag.Y);
            List<CarDto> carDtoList = new ArrayList<>();
            for (Car car : carList) {
                CarDto carDto = CarDto.builder()
                        .carType(car.getCarType())
                        .carPrice(car.getCarPrice())
                        .carYear(car.getCarYear()).build();
                carDtoList.add(carDto);
            }
            assetDto.setCar(carDtoList);
        }

        return assetDto;
    }
}
