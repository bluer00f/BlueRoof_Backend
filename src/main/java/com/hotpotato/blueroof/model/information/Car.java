package com.hotpotato.blueroof.model.information;

import com.hotpotato.blueroof.model.Timestamped;
import com.hotpotato.blueroof.model.user.User;
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
// 자동차
public class Car extends Timestamped {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "car_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 배우자 여부
    @Column(name = "spouse_fl", nullable = false)
    private int spouse;

    // 차량 기종
    @Column(name = "car_type", nullable = false)
    private String carType;

    // 차량 년도
    @Column(name = "car_year", nullable = false)
    private int carYear;

    // 차량 금액
    @Column(name = "car_price", nullable = false)
    private int carPrice;

}
