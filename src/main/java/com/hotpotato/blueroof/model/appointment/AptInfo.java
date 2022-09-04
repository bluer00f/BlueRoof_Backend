package com.hotpotato.blueroof.model.appointment;

import com.hotpotato.blueroof.model.type.Flag;
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
public class AptInfo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "apt_info_id")
    private Long id;

    // 주택 관리 번호
    @Column(name = "house_manage_no", nullable = false)
    private int houseManageNo;

    // 공고 번호
    @Column(name = "pblanc_no", nullable = false)
    private int pblancNo;

    // 주택명
    @Column(name = "house_nm", nullable = false)
    private String houseNm;

    // 주택 구분 코드명
    @Column(name = "house_secd_nm", nullable = false)
    private String houseSecdNm;

    // 주택 상세 구분 코드명
    @Column(name = "house_dtl_secd_nm", nullable = false)
    private String houseDtlSecdNm;

    // 분양 구분 코드명
    @Column(name = "rent_secd_nm", nullable = false)
    private String rentSecdNm;

    // 공급 지역명
    @Column(name = "subscrpt_area_code_nm", nullable = false)
    private String subscrptAreaCodeNm;

    // 공급 위치 우편번호
    @Column(name = "hssply_zip", nullable = false)
    private String hssplyZip;

    // 공급 위치
    @Column(name = "hssply_adres", nullable = false)
    private String hssplyAdres;

    // 공급 규모
    @Column(name = "tot_supply_hshldco", nullable = false)
    private String totSupplyHshldco;

    // 모집 공고일
    @Column(name = "rcrit_pblanc_de", nullable = false)
    private LocalDate rcritPblancDe;

    // 청약 접수 시작일
    @Column(name = "rcept_bgnde", nullable = false)
    private LocalDate rceptBgnde;

    // 청약 접수 종료일
    @Column(name = "rceptendde", nullable = false)
    private LocalDate rceptEndde;

    // 당첨자 발표일
    @Column(name = "przwner_presnatn_de", nullable = false)
    private LocalDate przwnerPresnatnDe;

    // 계약 시작일
    @Column(name = "cntrct_cncls_bgnde", nullable = false)
    private LocalDate cntrctCnclsBgnde;

    // 계약 종료일
    @Column(name = "cntrct_cncls_endde", nullable = false)
    private LocalDate cntrctCnclsEndde;

    // 시공사 (건설 업체명)
    @Column(name = "construction_name", nullable = false)
    private String constructionName;

    // 문의처
    @Column(name = "contact_phone", nullable = false)
    private String contactPhone;

    // 시행사 (사업 주체명)
    @Column(name = "bsns_mby_nm", nullable = false)
    private String bsnsMbyNm;

    // 입주 예정월
    @Column(name = "mvn_prearnge_yn", nullable = false, length = 50)
    private String mvnPrearngeYn;

    // 투기 과열 지구
    @Column(name = "speclt_rdn_earth_at", nullable = false, length = 1)
    @Enumerated(EnumType.STRING)
    private Flag specltRdnEarthAt;

    // 조정 대상 지역
    @Column(name = "mdat_trget_area_secd", nullable = false, length = 1)
    private String mdatTrgetAreaSecd;

    // 분양 가상 한제
    @Column(name = "parcprc_uls_at", nullable = false, length = 1)
    @Enumerated(EnumType.STRING)
    private Flag parcprcUlsAt;

    // 정비 사업
    @Column(name = "imprmn_bsns_at", nullable = false, length = 1)
    @Enumerated(EnumType.STRING)
    private Flag imprmnBsnsAt;

    // 공공 주택 지구
    @Column(name = "public_house_earth_at", nullable = false, length = 1)
    @Enumerated(EnumType.STRING)
    private Flag publicHouseEarthAt;

    // 대규모 택지 개발 지구
    @Column(name = "lrscl_bldlnd_at", nullable = false, length = 1)
    @Enumerated(EnumType.STRING)
    private Flag lrsclBldlndAt;

    // 수도권 내 민영 공공 주택 지구
    @Column(name = "npln_prvopr_public_house_at", nullable = false, length = 1)
    @Enumerated(EnumType.STRING)
    private Flag nplnPrvoprPublicHouseAt;

}
