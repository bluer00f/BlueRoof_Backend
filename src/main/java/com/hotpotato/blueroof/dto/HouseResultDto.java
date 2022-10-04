package com.hotpotato.blueroof.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HouseResultDto {

    private String BSNS_MBY_NM;     //시행사(사업주체명)
    private String CNSTRCT_ENTRPS_NM;   //시공사(건설업체명)
    private String CNTRCT_CNCLS_BGNDE;  //계약시작일
    private String CNTRCT_CNCLS_ENDDE;  //계약종료일
    private String GNRL_RNK1_CRSPAREA_RCEPT_PD;     //1순위 접수일 해당지역
    private String GNRL_RNK1_ETC_AREA_RCPTDE_PD;    //1순위 접수일 기타지역
    private String GNRL_RNK1_ETC_GG_RCPTDE_PD;      //1순위 접수일 경기지역
    private String GNRL_RNK2_CRSPAREA_RCEPT_PD;     //2순위 접수일 해당지역
    private String GNRL_RNK2_ETC_AREA_RCPTDE_PD;    //2순위 접수일 기타지역
    private String GNRL_RNK2_ETC_GG_RCPTDE_PD;      //2순위 접수일 경기지역
    private String HMPG_ADRES;      //홈페이지주소
    private String HOUSE_DTL_SECD;      //주택상세구분코드(01: 민영, 03: 국민)
    private String HOUSE_DTL_SECD_NM;   //주택상세구분코드명
    private Integer HOUSE_MANAGE_NO;    //주택관리번호
    private String HOUSE_NM;        //주택명
    private String HOUSE_SECD;      //주택구분코드(01: APT)
    private String HOUSE_SECD_NM;   //주택구분코드명
    private String HSSPLY_ADRES;    //공급위치
    private String HSSPLY_ZIP;      //공급위치 우편번호
    private String IMPRMN_BSNS_AT;  //정비사업
    private String LRSCL_BLDLND_AT; //대규모 택지개발지구
    private String MDAT_TRGET_AREA_SECD;    //조정대상지역 (Y: 과열지역, Y: 미대상주택, S: 위축지역)
    private String MDHS_TELNO;      //문의처
    private String MVN_PREARNGE_YM; //입주예정월
    private String NPLN_PRVOPR_PUBLIC_HOUSE_AT; //수도권 내 민영 공공주택지구
    private String PARCPRC_ULS_AT;  //분양가상한제
    private Integer PBLANC_NO;      //공고번호
    private String PRZWNER_PRESNATN_DE;     //당첨자발표일
    private String PUBLIC_HOUSE_EARTH_AT;   //공공주택지구
    private String RCEPT_BGNDE;     //청약접수시작일
    private String RCEPT_ENDDE;     //청약접수종료일
    private String RCRIT_PBLANC_DE; //모집공고일 (YYYY-MM-DD)
    private String RENT_SECD;       //분양구분코드(0: 분양주택, 1: 분양전환 가능임대, 2: 분양전환 불가임대)
    private String RENT_SECD_NM;    //분양구분코드명
    private String SPECLT_RDN_EARTH_AT; //투기과열지구
    private String SPSPLY_RCEPT_BGNDE;  //특별공급 접수시작일
    private String SPSPLY_RCEPT_ENDDE;  //특별공급 접수종료일
    private String SUBSCRPT_AREA_CODE;  //공급지역코드
    private String SUBSCRPT_AREA_CODE_NM;   //공급지역명
    private String TOT_SUPLY_HSHLDCO;   //공급규모
}