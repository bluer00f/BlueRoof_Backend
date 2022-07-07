package com.hotpotato.blueroof.model.information;

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
// 회원 세대 구성원 정보
public class Member {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "member_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 생년월일
    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    // 세대원 관계
    @Column(name = "member_relation", nullable = false)
    private int relation;

    // 세대주 여부
    @Column(name = "owner_fl", nullable = false)
    private int owner;

    // 연간 소득
    @Column(name = "year_income")
    private int yearIncome;

    // 무주택 여부
    @Column(name = "house_fl")
    private int house;

}
