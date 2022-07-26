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
// 회원 청약 정보
public class Subscription extends Timestamped {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "sub_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 무주택 시작일
    @Column(name = "houseless_date")
    private LocalDate houseEndDate;

    // 특별공급순위
    @Column(name = "special_rank", nullable = false)
    private int specialRank;

    // 일반공급순위
    @Column(name = "genral_rank", nullable = false)
    private int generalRank;

    // 2년내 당첨 이력
    @Column(name = "win_fl", nullable = false)
    private int win;

    // 당첨 일자
    @Column(name = "win_date")
    private LocalDate winDate;

    // 당첨 지역
    @Column(name = "region", length = 50)
    private String region;

    // 당첨 아파트명
    @Column(name = "win_name", length = 50)
    private String win_name;

}
