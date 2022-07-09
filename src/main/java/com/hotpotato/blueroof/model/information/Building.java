package com.hotpotato.blueroof.model.information;

import com.hotpotato.blueroof.model.Timestamped;
import com.hotpotato.blueroof.model.user.User;
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
// 건물
public class Building extends Timestamped {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "building_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 배우자 여부
    @Column(name = "spouse_fl", nullable = false)
    private int spouse;

    // 거주형 & 비거주형
    @Column(name = "house_fl", nullable = false)
    private int house;

    // 건물 유형
    @Column(name = "building_type", nullable = false)
    private String buildingType;

    // 건물 주소
    @Column(name = "building_address", nullable = false)
    private String buildingAddress;

    // 건물 면적 (제곱 미터)
    @Column(name = "building_area", nullable = false)
    private double buildingArea;

    // 건물 금액 (억)
    @Column(name = "building_price", nullable = false)
    private int buildingPrice;

    // 건물 기준
    @Column(name = "building_date")
    private LocalDate buildingDate;

}