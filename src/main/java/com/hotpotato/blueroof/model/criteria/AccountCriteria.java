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
// 청약 통장 기준 정보
public class AccountCriteria {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "account_criteria_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "special_criteria_id", nullable = false)
    private SpecialCriteria specialCriteria;

    // 가입 경과 월 수
    @Column(name = "account_month", nullable = false)
    private int accountMonth;

    // 청약 통장 유형
    @Column(name = "account_type", nullable = false)
    private String accountType;

    // 제한 조건
    @Column(name = "account_limit", nullable = false)
    private String accountLimit;

}
