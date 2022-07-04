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
// 소득
public class Income extends Timestamped {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "income_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 배우자 여부
    @Column(name = "spouse_fl", nullable = false)
    private int spouse;

    // 월 평균 소득
    @Column(name = "income_price")
    private int incomePrice;

}