package com.hotpotato.blueroof.service;

import com.hotpotato.blueroof.dto.ResultRequestDto;
import com.hotpotato.blueroof.model.appointment.AptInfo;
import com.hotpotato.blueroof.model.information.Building;
import com.hotpotato.blueroof.model.information.Member;
import com.hotpotato.blueroof.model.result.Audit;
import com.hotpotato.blueroof.model.result.Point;
import com.hotpotato.blueroof.model.type.Flag;
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
    private final AptInfoRepository aptInfoRepository;
    private final PointRepository pointRepository;

    // 적격 & 부적격 판단
    @Transactional
    public Audit audit(User user, ResultRequestDto resultDto, Long pointId) {

        Flag eligible = Flag.YES;
        String reason = "";
        AptInfo aptInfo = aptInfoRepository.findById(resultDto.getAptInfoId()).orElse(null);
        String[] userAddress = user.getAddress().split(" ");
        // 분양 정보의 인근 지역과 거주지 인근 지역 동일 여부 (아닐 경우 기타)
        String region1 = "서울시, 인천시, 경기도";
        String region2 = "대전시, 세종시, 충청남도";
        String region3 = "충청북도";
        String region4 = "전라북도";
        String region5 = "대구시, 경상북도";
        String region6 = "강원도";

        if (region1.contains(userAddress[0])) {
            if (!region1.contains(aptInfo.getSubscrptAreaCodeNm())) {
                eligible = Flag.NO;
                reason = "분양 정보의 인근 지역과 거주지 인근 지역 다름";
            }
        } else if (region2.contains(userAddress[0])) {
            if (!region2.contains(aptInfo.getSubscrptAreaCodeNm())) {
                eligible = Flag.NO;
                reason = "분양 정보의 인근 지역과 거주지 인근 지역 다름";
            }
        } else if (region3.contains(userAddress[0])) {
            if (!region3.contains(aptInfo.getSubscrptAreaCodeNm())) {
                eligible = Flag.NO;
                reason = "분양 정보의 인근 지역과 거주지 인근 지역 다름";
            }
        } else if (region4.contains(userAddress[0])) {
            if (!region4.contains(aptInfo.getSubscrptAreaCodeNm())) {
                eligible = Flag.NO;
                reason = "분양 정보의 인근 지역과 거주지 인근 지역 다름";
            }
        } else if (region5.contains(userAddress[0])) {
            if (!region5.contains(aptInfo.getSubscrptAreaCodeNm())) {
                eligible = Flag.NO;
                reason = "분양 정보의 인근 지역과 거주지 인근 지역 다름";
            }
        } else if (region6.contains(userAddress[0])) {
            if (!region6.contains(aptInfo.getSubscrptAreaCodeNm())) {
                eligible = Flag.NO;
                reason = "분양 정보의 인근 지역과 거주지 인근 지역 다름";
            }
        }

        // 수도권 등 규제지역(투기과열지구, 조정 대상 지역)에서 세대주 여부
        if (user.getOwner().equals(Flag.NO)) {
            eligible = Flag.NO;
            if (reason.equals("")) reason += " ";
            reason += "신청 대상자가 세대주가 아님";
        } else {
            if (user.getOwner().equals(Flag.NO)) {
                if (aptInfo.getSpecltRdnEarthAt().equals(Flag.YES) || aptInfo.getMdatTrgetAreaSecd().equals(Flag.YES))
                    eligible = Flag.NO;
                if (reason.equals("")) reason += " ";
                reason += "수도권 등 규제지역(투기과열지구, 조정 대상 지역)에서 세대주가 아님";
            }
        }

        // 세대원의 청약 당첨 이력을 파악하여 5년 이내 이력이 존재 여부

        // 세대원간 청약 중복 여부

        // 청약 통장의 가입 기간 충족 여부

        // 가족의 주택 보유 여부

        Audit audit = Audit.builder()
                .apt_info_id(aptInfo)
                .supplyType1(resultDto.getSupplyType1())
                .supplyType2(resultDto.getSupplyType2())
                .houseType(resultDto.getHouseType())
                .ineligible(eligible)
                .ineligibleReason(reason).build();

        return audit;
    }


//        String house = "부적격";
//        String reason = "가족의 주택 보유";
//        Parcel parcel = parcelRepository.findById(resultDto.getParcelId()).orElse(null);
//        Point point = pointRepository.findById(pointId).orElse(null);
//
//        // 회원 & 배우자
//        List<Building> buildingList = buildingRepository.findAllByUserIdAndHouseAndBuildingTypeNot(user.getId(), 0, "오피스텔");
//        if(buildingList.isEmpty()) { // 부적격
//            house = "적격";
//            reason = null;
//        }
//        // 무주택 인정 주택
//        else if(buildingList.size() == 1) {
//            Building building = buildingList.get(0);
//            if(parcel.getParcelType().equals("공공분양") && building.getBuildingArea() <= 20) { // 20 제곱미터 이하인 경우 무주택 & 공공 분양
//                house = "적격";
//                reason = null;
//            }
//
//            else if(resultDto.getSupplyType1().equals("APT 특별공급")&& parcel.getHouseType().equals("민영주택") && building.getBuildingArea() <= 60) { // 60 제곱미터 이하 & 수도권 1.3억 / 기타 0.8억 이하인 경우 무주택 & 민영 주택
//                String address = building.getBuildingAddress();
//                String[] city = address.split(" ");
//                if(city[0].equals("서울특별시") || city[0].equals("인천광역시") || city[0].equals("경기도") ) {
//                    if(building.getBuildingPrice() <= 1.3) {
//                        house = "적격";
//                        reason = null;
//                    }
//                }
//                else {
//                    if(building.getBuildingPrice() <= 0.8) {
//                        house = "적격";
//                        reason = null;
//                    }
//                }
//            }
//
//        }
//
//        for(Building building : buildingList) { // 2018-12-11 이전 경우는 무주택
//            LocalDate date = LocalDate.of(2018, 12, 11);
//            if(building.getBuildingDate().isAfter(date)) {
//                house = "부적격";
//                reason = "가족의 주택 보유";
//            }
//        }
//
//        // 세대원
//        List<Member> memberList = memberRepository.findAllByUserId(user.getId());
//        for(Member member : memberList) {
//            // 무주택 여부가 YES이면 무주택
//            if(member.getHouse().equals(Flag.YES)) {
//                int age = calcAge(member.getMemberBirthday());
//                if(age < 60) { // 60세 이하
//                    house = "부적격";
//                    reason = "가족의 주택 보유";
//                }
//            }
//        }

 //   }

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
