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
public class IncomeCriteria {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "income_criteria_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "special_criteria_id", nullable = false)
    private SpecialCriteria specialCriteria;

    // 신혼 공급 유형
    @Column(name = "marriage_type", nullable = false)
    private String marriageType;

    // 배우자 소득 여부
    @Column(name = "spouse_income_fl", nullable = false)
    private int spouseIncome;

    // 세대 소득 기준
    @Column(name = "generation_criteria", nullable = false)
    private String generationCriteria;

    // 신혼 부부 소득 기준
    @Column(name = "marriage_criteria", nullable = false)
    private String marriageCriteria;

    // 1인 소득 기준
    @Column(name = "person_criteria", nullable = false)
    private String personCriteria;

    // 부동산 기준
    @Column(name = "property_criteria", nullable = false)
    private String propertyCriteria;

    // 기준_FROM
    @Column(name = "criteria_from", nullable = false)
    private String criteriaFrom;

    // 기준_TO
    @Column(name = "criteria_to", nullable = false)
    private String criteriaTo;

    // 3인 이하_FROM
    @Column(name = "thr_criteria_from", nullable = false)
    private String thrCriteriaFrom;

    // 3인 이하_TO
    @Column(name = "thr_criteria_to", nullable = false)
    private String thrCriteriaTo;

    // 4인_FROM
    @Column(name = "four_criteria_from", nullable = false)
    private String fourCriteriaFrom;

    // 4인_TO
    @Column(name = "four_criteria_to", nullable = false)
    private String fourCriteriaTo;

    // 5인_FROM
    @Column(name = "five_criteria_from", nullable = false)
    private String fiveCriteriaFrom;

    // 5인_TO
    @Column(name = "five_criteria_to", nullable = false)
    private String fiveCriteriaTo;

}
