package com.hotpotato.blueroof.model.appointment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
// 분양 정보
public class Parcel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "parcel_id")
    private Long id;

    // 공고 번호
    @Column(name = "parcel_number", nullable = false)
    private int parcel_number;

    // 공고 년월
    @Column(name = "parcel_date", nullable = false)
    private int parcel_date;

    // 분양 지역
    @Column(name = "parcel_region", nullable = false)
    private String parcel_region;

    // 분양 상품명
    @Column(name = "parcel_name", nullable = false)
    private String parcel_name;

    // 주택 구분
    @Column(name = "house_type", nullable = false)
    private String houseType;

    // 분양 임대

    // 시행사 (사업 주체명)
    @Column(name = "business_name", nullable = false)
    private String businessName;

    // 시공사
    @Column(name = "construction_name", nullable = false)
    private String construction_name;

    // 입주자 모집 공고일
    @Column(name = "recruit_date", nullable = false)
    private LocalDate recruitDate;

    // 청약 시작일
    @Column(name = "sub_start_date", nullable = false)
    private LocalDate subStartDate;

    // 청약 종료일
    @Column(name = "sub_end_date", nullable = false)
    private LocalDate subEndDate;

    // 당첨자 발표일
    @Column(name = "win_date", nullable = false)
    private LocalDate winDate;

    // 문의처
    @Column(name = "contact_phone", nullable = false)
    private String contactPhone;

    // 공급 규모
    @Column(name = "area_detail", nullable = false)
    private String areaDetail;

    // 계약 시작일
    @Column(name = "contract_start_date", nullable = false)
    private LocalDate contractStartDate;

    // 계약 종료일
    @Column(name = "contract_end_date", nullable = false)
    private LocalDate contractEndDate;

    // 사업자 연락처
    @Column(name = "business_phone", nullable = false)
    private String businessPhone;

}
