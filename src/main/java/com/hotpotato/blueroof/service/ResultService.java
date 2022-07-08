package com.hotpotato.blueroof.service;

import com.hotpotato.blueroof.dto.ResultRequestDto;
import com.hotpotato.blueroof.model.appointment.Parcel;
import com.hotpotato.blueroof.model.information.Building;
import com.hotpotato.blueroof.model.information.Member;
import com.hotpotato.blueroof.model.result.Audit;
import com.hotpotato.blueroof.model.result.Point;
import com.hotpotato.blueroof.model.user.User;
import com.hotpotato.blueroof.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ResultService {

    private final BuildingRepository buildingRepository;
    private final MemberRepository memberRepository;
    private final AuditRepository auditRepository;
    private final ParcelRepository parcelRepository;
    private final PointRepository pointRepository;

    // 적격 & 부적격 판단
    @Transactional
    public Audit audit(User user, ResultRequestDto resultDto, Long pointId) {

        // 가족의 주택 보유 여부
        String house = "부적격";
        String reason = "가족의 주택 보유";
        Parcel parcel = parcelRepository.findById(resultDto.getParcelId()).orElse(null);
        Point point = pointRepository.findById(pointId).orElse(null);

        // 회원 & 배우자
        List<Building> buildingList = buildingRepository.findAllByUserIdAndHouseAndBuildingTypeNot(user.getId(), 0, "오피스텔");
        if(buildingList.isEmpty()) { // 부적격
            house = "적격";
            reason = null;
        }
        // 무주택 인정 주택
        else if(buildingList.size() == 1) {
            Building building = buildingList.get(0);
            if(parcel.getParcelType().equals("공공분양") && building.getBuildingArea() <= 20) { // 20 제곱미터 이하인 경우 무주택 & 공공 분양
                house = "적격";
                reason = null;
            }

            else if(resultDto.getSupplyType1().equals("APT 특별공급")&& parcel.getHouseType().equals("민영주택") && building.getBuildingArea() <= 60) { // 60 제곱미터 이하 & 수도권 1.3억 / 기타 0.8억 이하인 경우 무주택 & 민영 주택
                String address = building.getBuildingAddress();
                String[] city = address.split(" ");
                if(city[0].equals("서울특별시") || city[0].equals("인천광역시") || city[0].equals("경기도") ) {
                    if(building.getBuildingPrice() <= 1.3) {
                        house = "적격";
                        reason = null;
                    }
                }
                else {
                    if(building.getBuildingPrice() <= 0.8) {
                        house = "적격";
                        reason = null;
                    }
                }
            }

        }

        for(Building building : buildingList) { // 2018-12-11 이전 경우는 무주택
            LocalDate date = LocalDate.of(2018, 12, 11);
            if(building.getBuildingDate().isAfter(date)) {
                house = "부적격";
                reason = "가족의 주택 보유";
            }
        }

        // 세대원
        List<Member> memberList = memberRepository.findAllByUserId(user.getId());
        for(Member member : memberList) {
            // 무주택 여부가 1(true)이면 무주택
            if(member.getHouse() == 0) {
                int age = calcAge(member.getBirthday());
                if(age < 60) { // 60세 이하
                    house = "부적격";
                    reason = "가족의 주택 보유";
                }
            }
        }

        Audit audit = Audit.builder()
                .user(user)
                .parcel(parcel)
                .point(point)
                .ineligible(house)
                .ineligibleReason(reason).build();
        return auditRepository.save(audit);
    }

    // 가점 계산
    @Transactional
    public Point point(User user, ResultRequestDto resultDto) {

        return null;
    }

    // 만 나이 계산
    public int calcAge(LocalDate birthday) {
        LocalDate now = LocalDate.now();
        int age = LocalDateTime.now().getYear() - birthday.getYear() + 1; // 나이

        // 만 나이
        if (birthday.plusYears(age).isBefore(now))
            age = age - 1;

        return age;
    }

}
