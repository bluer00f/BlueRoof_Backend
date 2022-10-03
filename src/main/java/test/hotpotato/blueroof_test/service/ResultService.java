package test.hotpotato.blueroof_test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.hotpotato.blueroof_test.dto.ResultRequestDto;
import test.hotpotato.blueroof_test.model.appointment.AptInfo;
import test.hotpotato.blueroof_test.model.information.*;
import test.hotpotato.blueroof_test.model.result.Audit;
import test.hotpotato.blueroof_test.model.result.Point;
import test.hotpotato.blueroof_test.model.type.Flag;
import test.hotpotato.blueroof_test.model.user.User;
import test.hotpotato.blueroof_test.repository.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

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
    private final IncomeRepository incomeRepository;
    private final BabyRepository babyRepository;

    LocalDate now = LocalDate.now();


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


        // 수도권 등 규제지역(투기과열지구, 조정 대상 지역)에서 세대주 여부
        if (user.getOwner().equals(Flag.N)) {
            eligible = Flag.N;
            if (!reason.equals("")) reason += " ";
            reason += "신청 대상자가 세대주가 아님";
        } else {
            if (user.getOwner().equals(Flag.N)) {
                if (aptInfo.getSpecltRdnEarthAt().equals(Flag.Y) || aptInfo.getMdatTrgetAreaSecd().equals(Flag.Y))
                    eligible = Flag.N;
                if (!reason.equals("")) reason += " ";
                reason += "수도권 등 규제지역(투기과열지구, 조정 대상 지역)에서 세대주가 아님";
            }
        }


        // 세대원의 청약 당첨 이력을 파악하여 5년 이내 이력이 존재 여부
        Subscription subscription = subscriptionRepository.findByUserId(user.getId());
        Spouse spouse = spouseRepository.findByUserId(user.getId());
        List<Member> members = memberRepository.findAllByUserId(user.getId());
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

        // 청약 통장의 가입 기간 충족 여부
        String account = "부적격";
        List<BankBook> bankBooks = bankBookRepository.findAllByUserId(user.getId());
        for (BankBook bankBook : bankBooks) {
            Period bankBookPeriod = Period.between(bankBook.getBankBookDate(), LocalDate.now());
            if (aptInfo.getMdatTrgetAreaSecd().equals("Y")) {
                if (bankBookPeriod.getYears() >= 2) {
                    account = "적격";
                    break;
                }
            } else if (aptInfo.getMdatTrgetAreaSecd().equals("S")) {
                if (bankBookPeriod.getMonths() >= 1) {
                    account = "적격";
                    break;
                }
            } else {
                if (aptInfo.getSubscrptAreaCodeNm().equals("서울") || aptInfo.getSubscrptAreaCodeNm().equals("인천") || aptInfo.getSubscrptAreaCodeNm().equals("경기")) {
                    if (bankBookPeriod.getYears() >= 1) {
                        account = "적격";
                        break;
                    }
                } else {
                    if (bankBookPeriod.getMonths() >= 6) {
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

        // 가족의 주택 보유 여부
        // 회원 & 배우자
        List<Building> buildingList = buildingRepository.findAllByUserIdAndHouseAndBuildingTypeNot(user.getId(), 0, "오피스텔");
        String house = "부적격";
        if (!buildingList.isEmpty()) {
            house = "적격";
        }
        // 무주택 인정 주택
        else if (buildingList.size() == 1) {
            Building building = buildingList.get(0);
            if (aptInfo.getPublicHouseEarthAt().equals(Flag.Y) && building.getBuildingArea() <= 20) { // 20 제곱미터 이하인 경우 무주택 & 공공 분양
                house = "적격";
            } else if (resultDto.getSupplyType1().equals("APT 특별공급") && aptInfo.getNplnPrvoprPublicHouseAt().equals(Flag.Y) && building.getBuildingArea() <= 60) { // 60 제곱미터 이하 & 수도권 1.3억 / 기타 0.8억 이하인 경우 무주택 & 민영 주택
                String address = building.getBuildingAddress();
                String[] city = address.split(" ");
                if (city[0].equals("서울특별시") || city[0].equals("인천광역시") || city[0].equals("경기도")) {
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

    // 가점 계산-신혼부부 특공
    @Transactional
    public Point point(User user, ResultRequestDto resultDto, Long auditId) {
        int subPoint = 0;   //총 가점
        AptInfo aptInfo = aptInfoRepository.findById(resultDto.getAptInfoId()).orElse(null);  //선택한 공고 주택 정보
        AptInfo myAptInfo = aptInfoRepository.findById(user.getId()).orElse(null);  //내 현 거주 주택 정보

        List<Member> members = memberRepository.findAllByUserId(user.getId());  //세대구성원 정보 list
        List<BankBook> bankBooks = bankBookRepository.findAllByUserId(user.getId());    //내 청약통장 list
        Spouse spouse = spouseRepository.findByUserId(user.getId());    //배우자 정보
        Income income = incomeRepository.findByUserId(user.getId());    //내 수입 정보
        Subscription subscription = subscriptionRepository.findByUserId(user.getId());      //내 기타 정보
        LocalDate today = LocalDate.now(ZoneId.of("Asia/Seoul"));       //오늘 날짜

        //[1]청약가점제 알고리즘
        // : 민간분양-일반공급 && 민간분양-노부모 부양 특공

        //[2]가점표 > 배점기준표 알고리즘
        // : 민간분양-다자녀 특공 && 공공분양-다자녀 특공

        //[3]가점표 > 가점 항목 알고리즘
        // : 공공분양-신혼부부 특공
        int houseIncomePoint = 0;             //1.가구소득 (최대 1점)
        int childrenNumPoint = 0;             //2. 미성년 자녀 수 (최대 3점)
        int continuallyResidencePoint = 0;   //3. 해당 지역구 연속거주기간 (최대 3점)
        int bankBookTotalCountPoint = 0;    //4. 주택청약 납입횟수 (최대 3점)
        int newMarriagePoint = 0;           //5-1. 혼인기간 (*신혼부부만) (최대 3점)
        int singleParentPoint = 0;          //5-2. 자녀 나이 (*한부모 가족만) (최대 3점)


        // 1. 가구 소득 (최대 1점)
        //1-1. 배우자가 있는가?
        if (user.getMarriage().equals("Y")) {
            int houseMonthlyIncome = 0;     //해당 세대 월평균소득
            int memNum = calMemNum(user);   //해당 세대 가구원 수

            //1-2-1. 맞벌이인 경우
            if (income.getIncomeMonthPrice() > 0 && spouse.getSpouseIncome() > 0) {
                houseMonthlyIncome = (income.getIncomeMonthPrice() + spouse.getSpouseIncome()) / 2;
                //가구원 수에 따른 월평균 소득 <= 도시근로자 월평균 소득 100%
                if (houseMonthlyIncome <= checkStandardHouseIncome(memNum) * 100 * 0.01) {
                    houseIncomePoint += 1;
                }
            }

            //1-2-2. 외벌이인 경우
            else {
                if (income.getIncomeMonthPrice() > 0 && spouse.getSpouseIncome() == 0) {
                    houseMonthlyIncome = income.getIncomeMonthPrice();
                } else if (spouse.getSpouseIncome() > 0 && income.getIncomeMonthPrice() == 0) {
                    houseMonthlyIncome = spouse.getSpouseIncome();
                }
                //가구원 수에 따른 월평균 소득 <= 도시근로자 월평균 소득 80%
                if (houseMonthlyIncome <= checkStandardHouseIncome(memNum) * 80 * 0.01) {
                    houseIncomePoint += 1;
                }
            }
        }


        //2. 미성년 자녀 수 (최대 3점)
        int childrenNum = 0;    //해당 가구 자녀 수
        //2-1. 미성년자(만 19세 = 6935일 이하) 자녀 수 찾기(태아 제외)
        for (Member member : members) {
            if (today.isBefore(member.getMemberBirthday().plusDays(6935))) {
                childrenNum += 1;
            }
        }
        //2-2. 태아 수 찾기
        Baby baby = babyRepository.findByUserId(user.getId());
        childrenNum += baby.getBabyCount();

        switch (childrenNum) {
            case 0:
                break;
            case 1:
                childrenNumPoint = 1;
                break;
            case 2:
                childrenNumPoint = 2;
                break;
            default:
                childrenNumPoint = 3;
                break;
        }


        //3. 해당 지역구 연속거주기간 (최대 3점)
        //3-1. 현재 거주 지역이 공급지역과 같을 때만 거주기간(오늘 - 거주 시작일)에 따라 가점
        if (myAptInfo.getSubscrptAreaCode() == aptInfo.getSubscrptAreaCode()) {
            int residenceDays = Long.valueOf(Optional.ofNullable(ChronoUnit.DAYS.between(now, subscription.getNowHouseStartDate())).orElse(0L)).intValue();  // 오늘-거주시작일
            if (residenceDays < 365) {
                continuallyResidencePoint += 1;
            } else if (residenceDays >= 365 * 3) {
                continuallyResidencePoint += 3;
            } else {
                continuallyResidencePoint += 2;
            }
        }


        //4. 주택청약 납입횟수 (최대 3점)
        int validTotalCount = 0;    //총 납입 횟수
        for (BankBook bankBook : bankBooks) {
            //4-1. 청약종합저축인 경우
            if (bankBook.getBankBookType().equals("주택청약종합저축")) {
                //4-1-1. 미성년자 납입횟수가 24회 초과인 경우
                if (bankBook.getTeenCount() > 24) {
                    validTotalCount = bankBook.getTotalCount() - bankBook.getTeenCount() + 24;
                } else {    //4-1-2. 미성년자 납입횟수가 24회 이하인 경우
                    validTotalCount = bankBook.getTotalCount();
                }
            }
            //4-2. 청약 저축
            //4-3. 청약 예금
            //4-4. 청약 부금
        }

        if (validTotalCount >= 24) {
            bankBookTotalCountPoint += 3;
        } else if (validTotalCount >= 12 && validTotalCount < 24) {
            bankBookTotalCountPoint += 2;
        } else if (validTotalCount >= 6 && validTotalCount < 12) {
            bankBookTotalCountPoint += 1;
        }


        //5. 기타가점 (최대 3점)
        //5-1. 신혼부부 가점
        //배우자 존재 && 혼인기간 7년 이하

        if (user.getMarriage().equals("Y")) {
            int marriagePeriod = Long.valueOf(Optional.ofNullable(ChronoUnit.DAYS.between(today, spouse.getMarriageDate())).orElse(0L)).intValue();   //혼인기간
            if (marriagePeriod <= 365 * 3) {   //혼인기간 3년 이하
                newMarriagePoint += 3;
            } else if (marriagePeriod <= 365 * 5 && marriagePeriod > 365 * 3) {  //혼인기간 5년 이하
                newMarriagePoint += 2;
            } else if (marriagePeriod <= 365 * 7 && marriagePeriod > 365 * 5){    //혼인기간 7년 이하
                newMarriagePoint += 1;
            }
        }

/*        //5-2. 한부모 가족 가점
        //배우자 존재하지 않음 && 6세 이하 자녀 존재
        if (user.getMarriage().equals("N")) {
            int memAge;
            int minAge = 0;
            for (Member member : members) {
                if (today.isBefore(member.getMemberBirthday().plusDays(365*6))) {
                    memAge = Long.valueOf(Optional.ofNullable(ChronoUnit.DAYS.between(today, member.getMemberBirthday())).orElse(0L)).intValue();   //나이
                    if(memAge <= minAge) {
                        minAge = memAge;
                    }
                }

                if (user.getMarriage().equals("N")) {

                }
            }
        }*/

        subPoint = houseIncomePoint+childrenNumPoint+continuallyResidencePoint+bankBookTotalCountPoint+newMarriagePoint+singleParentPoint;

        Point point = Point.builder()
                .subPoint(subPoint)
                .houseIncomePoint(houseIncomePoint)
                .childrenNumPoint(childrenNumPoint)
                .continuallyResidencePoint(continuallyResidencePoint)
                .bankBookTotalCountPoint(bankBookTotalCountPoint)
                .newMarriagePoint(newMarriagePoint)
                .singleParentPoint(singleParentPoint)
                .build();


        return point;
    }

    // 만 나이 계산
    public int calcAge(LocalDate birthday) {
        int age = LocalDateTime.now().getYear() - birthday.getYear() + 1; // 나이

        // 만 나이
        if (birthday.plusYears(age).isBefore(now))
            age = age - 1;

        return age;
    }

    //가구원 수 구하기
    public int calMemNum(User user) {
        int MemNum = 1;
        //결혼했으면(배우자 +1)
        if (user.getMarriage().equals("Y")) {
            MemNum += 1;
        }
        //자녀있으면 +자녀수 (태아제외)
        List<Member> members = memberRepository.findAllByUserId(user.getId());
        MemNum += members.size();
        //임신 했으면 +태아수
        Baby baby = babyRepository.findByUserId(user.getId());
        if (baby.getPregnant().equals("Y")) {
            MemNum += baby.getBabyCount();
        }

        return MemNum;
    }

    //가구원 수 별 기준소득
    public int checkStandardHouseIncome(int memnum) {
        int standardHouseIncome = 0;
        switch (memnum) {
            case 4:
                standardHouseIncome = 7200809;
                break;
            case 5:
                standardHouseIncome = 7326072;
                break;
            case 6:
                standardHouseIncome = 7779825;
                break;
            case 7:
                standardHouseIncome = 8233578;
                break;
            case 8:
                standardHouseIncome = 8687331;    // 8인 이상인경우에는 어떡하지
                break;
            default:
                standardHouseIncome = 6208934;    // 3인 이하
                break;
        }

        return standardHouseIncome;
    }

}