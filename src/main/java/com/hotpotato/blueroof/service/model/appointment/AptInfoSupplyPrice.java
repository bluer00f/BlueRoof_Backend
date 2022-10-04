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
// 공급 금액
public class AptInfoSupplyPrice {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "apt_info_supply_price_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "apt_info_id", nullable = false)
    private AptInfo aptInfo;

    // 주택형
    @Column(name = "supply_type", nullable = false)
    private String supplyType;

    // 공급 금액
    @Column(name = "supply_price", nullable = false)
    private int supplyPrice;
}
