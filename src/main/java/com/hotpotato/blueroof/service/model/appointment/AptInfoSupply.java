package com.hotpotato.blueroof.service.model.appointment;

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
// 공급 대상 정보
public class AptInfoSupply {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "apt_info_supply_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "apt_info_id", nullable = false)
    private AptInfo aptInfo;

    // 주택형
    @Column(name = "supply_type", nullable = false)
    private String supplyType;

    // 주택 공급 면적
    @Column(name = "supply_area", nullable = false)
    private double supplyArea;

    // 공급 세대 수 - 일반
    @Column(name = "supply_general")
    private int supplyGeneral;

    // 공급 세대 수 - 특별
    @Column(name = "supply_special")
    private int supplySpecial;

    // 공급 세대 수 - 계
    @Column(name = "supply_total")
    private int supplyTotal;
}
