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
// 자산 기준
public class AssetBaseInfo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "asset_base_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "special_base_id", nullable = false)
    private SpecialBaseInfo specialBaseInfo;

    // 자산 구분
    @Column(name = "asset_type")
    private String assetType;

    // 기준 금액
    @Column(name = "asset_base_amt")
    private int assetBaseAmt;

    @Column(name = "asset_memo", length = 2000)
    private String assetMemo;
}
