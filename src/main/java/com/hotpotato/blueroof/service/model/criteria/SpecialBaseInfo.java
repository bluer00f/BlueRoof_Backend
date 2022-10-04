package com.hotpotato.blueroof.service.model.criteria;

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
public class SpecialBaseInfo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "special_base_id")
    private Long id;

    // 공급 유형1
    @Column(name = "supply_type1")
    private String supplyType1;

    // 공급 유형2
    @Column(name = "supply_type2")
    private String supplyType2;

    // 전용 면적
    @Column(name = "house_area")
    private int houseArea;

    // 결혼 기간
    @Column(name = "marriage_limit_year")
    private int marriageLimitYear;

    // 자녀 유무
    @Column(name = "child_yn")
    private String childYn;

    // 분양가 제한
    @Column(name = "sale_price_limit")
    private int salePriceLimit;

    // 특별 기준
    @Column(name = "memo", length = 2000)
    private String memo;
}
