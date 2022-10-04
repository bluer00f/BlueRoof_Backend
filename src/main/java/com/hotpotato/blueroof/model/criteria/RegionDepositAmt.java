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
// 지역별 예치 금액
public class RegionDepositAmt {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "deposit_id")
    private Long id;

    // 청약 통장 유형
    @Column(name = "account_type")
    private String accountType;

    // 전용 면적
    @Column(name = "buildingArea")
    private int buildingArea;

    // 지역
    @Column(name = "region_zipcode")
    private String regionZipcode;

    // 예치금액
    @Column(name = "deposit_amt")
    private int depositAmt;
}
