package com.hotpotato.blueroof.model;


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
// 토지
public class Land extends Timestamped {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "land_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 배우자 여부
    @Column(name = "spouse_fl", nullable = false)
    private int spouse;

    // 토지 주소
    @Embedded
    private Address landAddress;

    // 토지 면적
    @Column(name = "land_area", nullable = false)
    private double landArea;

    // 토지 금액
    @Column(name = "land_price", nullable = false)
    private int landPrice;

}
