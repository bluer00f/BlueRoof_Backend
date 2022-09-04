package com.hotpotato.blueroof.model.criteria;

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
// 소득 기준 정보
public class IncomeBaseInfo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "income_base_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "special_base_id", nullable = false)
    private SpecialBaseInfo specialBaseInfo;

    // 신혼 공급 유형
    @Column(name = "apply_type")
    private String marriageType;

    // 배우자 소득 여부
    @Column(name = "dual_income_yn")
    private String dualIncomeYn;

    // 소득 기준 FROM
    @Column(name = "income_base_from")
    private int generationCriteria;

    // 소득 기준 TO
    @Column(name = "income_base_to")
    private int incomeBaseTo;

    // 1인 소득 기준 FROM
    @Column(name = "sec_1_from")
    private int sec1From;

    // 1인 소득 기준 TO
    @Column(name = "sec_1_to")
    private int sec1To;

    // 2인 소득 기준 FROM
    @Column(name = "sec_2_from")
    private int sec2From;

    // 2인 소득 기준 TO
    @Column(name = "sec_2_to")
    private int sec2To;

    // 3인 소득 기준 FROM
    @Column(name = "sec_3_from")
    private int sec3From;

    // 3인 소득 기준 TO
    @Column(name = "sec_3_to")
    private int sec3To;


}
