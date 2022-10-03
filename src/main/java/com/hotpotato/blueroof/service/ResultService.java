package com.hotpotato.blueroof.service;

import com.hotpotato.blueroof.dto.ResultRequestDto;
import com.hotpotato.blueroof.model.appointment.AptInfo;
import com.hotpotato.blueroof.model.information.*;
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
import java.time.Period;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ResultService {

    private final BuildingRepository buildingRepository;
    private final MemberRepository memberRepository;
    private final AuditRepository auditRepository;
    private final SubscriptionRepository subscriptionRepository;
    private final SpouseRepository spouseRepository;
    private final BankBookRepository bankBookRepository;
    private final AptInfoRepository aptInfoRepository;
    private final PointRepository pointRepository;

    // 적격 & 부적격 판단
    @Transactional
    public Audit audit(User user, ResultRequestDto resultDto) {

        Flag eligible = Flag.Y;
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
                eligible = Flag.N;
                reason = "분양 정보의 인근 지역과 거주지 인근 지역 다름";
            }
        } else if (region2.contains(userAddress[0])) {
            if (!region2.contains(aptInfo.getSubscrptAreaCodeNm())) {
                eligible = Flag.N;
                reason = "분양 정보의 인근 지역과 거주지 인근 지역 다름";
            }
        } else if (region3.contains(userAddress[0])) {
            if (!region3.contains(aptInfo.getSubscrptAreaCodeNm())) {
                eligible = Flag.N;
                reason = "분양 정보의 인근 지역과 거주지 인근 지역 다름";
            }
        } else if (region4.contains(userAddress[0])) {
            if (!region4.contains(aptInfo.getSubscrptAreaCodeNm())) {
                eligible = Flag.N;
                reason = "분양 정보의 인근 지역과 거주지 인근 지역 다름";
            }
        } else if (region5.contains(userAddress[0])) {
            if (!region5.contains(aptInfo.getSubscrptAreaCodeNm())) {
                eligible = Flag.N;
                reason = "분양 정보의 인근 지역과 거주지 인근 지역 다름";
            }
        } else if (region6.contains(userAddress[0])) {
            if (!region6.contains(aptInfo.getSubscrptAreaCodeNm())) {
                eligible = Flag.N;
                reason = "분양 정보의 인근 지역과 거주지 인근 지역 다름";
            }
        }

        Subscription subscription = subscriptionRepository.findByUserId(user.getId());
        Spouse spouse = spouseRepository.findByUserId(user.getId());
        List<Member> members = memberRepository.findAllByUserId(user.getId());

        // 수도권 등 규제지역(투기과열지구, 조정 대상 지역)에서 세대주 여부
        if (aptInfo.getSpecltRdnEarthAt().equals(Flag.Y) || aptInfo.getMdatTrgetAreaSecd().equals(Flag.Y)) {
            if (user.getOwner().equals(Flag.N)) {
                eligible = Flag.N;
                if (!reason.equals("")) reason += " ";
                reason += "수도권 등 규제지역(투기과열지구, 조정 대상 지역)에서 세대주가 아님";
            }
            // 세대원의 청약 당첨 이력을 파악하여 5년 이내 이력이 존재 여부
            Period userPeriod = Period.between(subscription.getWinDate(), LocalDate.now());
            Period spousePeriod = Period.between(spouse.getWinDate(), LocalDate.now());
            if (subscription.getWinDate() != null && userPeriod.getYears() <= 5) {
                eligible = Flag.N;
                if (!reason.equals("")) reason += " ";
                reason += "본인의 5년 이내 청약 당첨 이력 존재";
            } else if (spouse.getWinDate() != null && spousePeriod.getYears() <= 5) {
                eligible = Flag.N;
                if (!reason.equals("")) reason += " ";
                reason += "배우자의 5년 이내 청약 당첨 이력 존재";
            }

            for (Member member : members) {
                Period memberPeriod = Period.between(member.getWinDate(), LocalDate.now());
                if (member.getWinDate() != null && memberPeriod.getYears() <= 5) {
                    eligible = Flag.N;
                    if (!reason.equals("")) reason += " ";
                    reason += "본인 & 배우자 외 세대원의 5년 이내 청약 당첨 이력 존재";
                    break;
                }
            }
        }

        // 청약 통장의 가입 기간 충족 여부
        String account = "부적격";
        List<BankBook> bankBooks = bankBookRepository.findAllByUserId(user.getId());
        for (BankBook bankBook : bankBooks) {
            Period bankBookPeriod = Period.between(bankBook.getBankBookDate(), LocalDate.now());
            if (aptInfo.getMdatTrgetAreaSecd().equals("Y")) {
                if (bankBookPeriod.getYears() >= 2) {
                    if (aptInfo.getHouseDtlSecdNm().equals("민영") || bankBook.getTotalCount() >= 24) {
                        account = "적격";
                        break;
                    }
                }
            } else if (aptInfo.getMdatTrgetAreaSecd().equals("S")) {
                if (bankBookPeriod.getMonths() >= 1) {
                    if (aptInfo.getHouseDtlSecdNm().equals("민영") || bankBook.getTotalCount() >= 1) {
                        account = "적격";
                        break;
                    }
                }
            } else {
                if (aptInfo.getSubscrptAreaCodeNm().equals("서울") || aptInfo.getSubscrptAreaCodeNm().equals("인천")
                        || aptInfo.getSubscrptAreaCodeNm().equals("경기")) {
                    if (aptInfo.getHouseDtlSecdNm().equals("민영") || bankBook.getTotalCount() >= 12) {
                        account = "적격";
                        break;
                    }
                } else {
                    if (aptInfo.getHouseDtlSecdNm().equals("민영") || bankBook.getTotalCount() >= 6) {
                        account = "적격";
                        break;
                    }
                }
            }
        }

        if (account.equals("부적격")) {
            eligible = Flag.N;
            if (!reason.equals("")) reason += " ";
            reason += "청약 통장 가입 기간 미충족";
        }

        // 거주 기간 충족 여부
        Period livePeriod = Period.between(subscription.getNowHouseStartDate(), LocalDate.now());
        if (!reason.contains("분양 정보의 인근 지역과 거주지 인근 지역 다름")) {
            if (aptInfo.getMdatTrgetAreaSecd().equals("Y")) {
                if (aptInfo.getHouseDtlSecdNm().equals("민영")) {
                    if (livePeriod.getYears() < 2) {
                        eligible = Flag.N;
                        if (!reason.equals("")) reason += " ";
                        reason += "인근 지역 거주 기간 미충족";
                    }
                } else {
                    if (livePeriod.getYears() < 1) {
                        eligible = Flag.N;
                        if (!reason.equals("")) reason += " ";
                        reason += "인근 지역 거주 기간 미충족";
                    }
                }
            }
        }

        // 가족의 주택 보유 여부
        // 회원 & 배우자
        List<Building> buildingList = buildingRepository.findAllByUserIdAndHouseAndBuildingTypeNot(user.getId(),
                0, "오피스텔");
        String house = "부적격";
        if (!buildingList.isEmpty()) {
            house = "적격";
        }
        // 무주택 인정 주택
        else if (buildingList.size() == 1) {
            Building building = buildingList.get(0);
            // 20 제곱미터 이하인 경우 무주택 & 공공 분양
            if (aptInfo.getPublicHouseEarthAt().equals(Flag.Y) && building.getBuildingArea() <= 20) {
                house = "적격";
            }
            // 60 제곱미터 이하 & 수도권 1.3억 / 기타 0.8억 이하인 경우 무주택 & 민영 주택
            else if (resultDto.getSupplyType1().equals("APT 특별공급")
                    && aptInfo.getNplnPrvoprPublicHouseAt().equals(Flag.Y) && building.getBuildingArea() <= 60) {
                String address = building.getBuildingAddress();
                String[] city = address.split(" ");
                if (city[0].contains("서울") || city[0].contains("인천") || city[0].contains("경기")) {
                    if (building.getBuildingPrice() <= 1.3) {
                        house = "적격";
                    }
                } else {
                    if (building.getBuildingPrice() <= 0.8) {
                        house = "적격";
                    }
                }
            }
        }
        for (Building building : buildingList) { // 2018-12-11 이전 경우는 무주택
            LocalDate date = LocalDate.of(2018, 12, 11);
            if (building.getBuildingDate().isAfter(date)) {
                house = "부적격";
            }
        }

        // 세대원
        List<Member> memberList = memberRepository.findAllByUserId(user.getId());
        for (Member member : memberList) {
            // 무주택 여부가 YES이면 무주택
            if (member.getHouse().equals(Flag.Y)) {
                int age = calcAge(member.getMemberBirthday());
                if (age < 60) { // 60세 이하
                    house = "부적격";
                }
            }
        }

        if (house.equals("부적격")) {
            eligible = Flag.N;
            if (!reason.equals("")) reason += " ";
            reason += "가족의 주택 보유";
        }

        Audit audit = Audit.builder()
                .apt_info_id(aptInfo)
                .supplyType1(resultDto.getSupplyType1())
                .supplyType2(resultDto.getSupplyType2())
                .houseType(resultDto.getHouseType())
                .ineligible(eligible)
                .ineligibleReason(reason).build();

        return audit;
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
