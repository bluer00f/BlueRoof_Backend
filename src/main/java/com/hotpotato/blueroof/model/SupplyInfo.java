package com.hotpotato.blueroof.model;

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
// 공급 대상 정보
public class SupplyInfo {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "supply_info_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parcel_id", nullable = false)
    private Parcel parcel;

    // 주택 구분
    @Column(name = "house_type", nullable = false)
    private int subDateType;

    // 주택 공급 면적
    @Column(name = "supply_area", nullable = false)
    private double supplyArea;

    // 공급 유형
    @Column(name = "supply_type", nullable = false)
    private String supplyType;

    // 특별 공급 유형
    @Column(name = "special_supply_type", nullable = false)
    private String specialSupplyType;

    // 공급 세대 수
    @Column(name = "supply_count", nullable = false)
    private int supplyCount;
}
