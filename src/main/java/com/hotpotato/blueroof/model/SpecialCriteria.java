package com.hotpotato.blueroof.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
// 특별 공급 기준 정보
public class SpecialCriteria {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "special_criteria_id")
    private Long id;

    // 공급 유형1
    @Column(name = "supply_type1", nullable = false)
    private int supplyType1;

    // 공급 유형2
    @Column(name = "supply_type2", nullable = false)
    private int supplyType2;

    // 주택 면적
    @Column(name = "house_area", nullable = false)
    private double houseArea;

    // 결혼 기간 제한 년수
    @Column(name = "marriage_limit_year", nullable = false)
    private int marriageLimitYear;

    // 무주택 제한 여부
    @Column(name = "house_limit_fl", nullable = false)
    private int houseLimit;

    // 특별 공급 기준 정보
    @Column(name = "special_info", nullable = false)
    private String specialInfo;
}
