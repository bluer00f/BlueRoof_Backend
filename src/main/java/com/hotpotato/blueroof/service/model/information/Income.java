package com.hotpotato.blueroof.service.model.information;

import com.hotpotato.blueroof.service.model.Timestamped;
import com.hotpotato.blueroof.service.model.user.User;
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

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 월 평균 소득
    @Column(name = "income_month_price")
    private int incomeMonthPrice;

}