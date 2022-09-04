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
public class SubscriptionAcntBase {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "account_base_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "special_base_id", nullable = false)
    private SpecialBaseInfo specialBaseInfo;

    // 청약 통장 유형
    @Column(name = "account_type")
    private String accountType;

    // 가입 경과 월 수
    @Column(name = "account_sub_mm")
    private int accountSubMm;

    // 지불 횟수
    @Column(name = "paym_cnt")
    private int paymCnt;

}
