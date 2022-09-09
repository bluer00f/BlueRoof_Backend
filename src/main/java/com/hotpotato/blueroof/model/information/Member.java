package com.hotpotato.blueroof.model.information;

import com.hotpotato.blueroof.model.type.Flag;
import com.hotpotato.blueroof.model.type.Relation;
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

    // 이름
    @Column(name = "member_name", nullable = false)
    private String memberName;

    // 생년월일
    @Column(name = "member_birthday", nullable = false)
    private LocalDate memberBirthday;

    // 청약 당첨 이력
    @Column(name = "win_fl", nullable = false)
    @Enumerated(EnumType.STRING)
    private Flag win;

    // 당첨일자
    @Column(name = "win_date")
    private LocalDate winDate;

    // 세대원 관계
    @Column(name = "member_relation", nullable = false)
    @Enumerated(EnumType.STRING)
    private Relation relation;

    // 세대주 여부
    @Column(name = "owner_fl", nullable = false)
    @Enumerated(EnumType.STRING)
    private Flag owner;

    // 연간 소득
    @Column(name = "year_income")
    private int yearIncome;

    // 무주택 여부
    @Column(name = "house_fl")
    @Enumerated(EnumType.STRING)
    private Flag house;

}
