package com.hotpotato.blueroof.model.appointment;

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
public class SupplyPrice {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "supply_price_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parcel_id", nullable = false)
    private Parcel parcel;

    // 주택형
    @Column(name = "house_area", nullable = false)
    private String houseArea;

    // 주택 관리 번호
    @Column(name = "house_number", nullable = false)
    private int houseNumber;

    // 공급 금액
    @Column(name = "supply_price", nullable = false)
    private int supplyPrice;
}
