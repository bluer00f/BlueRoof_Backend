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
// 배우자 정보
public class Spouse {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "spouse_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 생년월일
    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    // 혼인신고일
    @Column(name = "marriage_date", nullable = false)
    private LocalDate marriageDate;

    // 청약 당첨 이력
    @Column(name = "win_fl", nullable = false)
    private int win;

    // 당첨일자
    @Column(name = "win_date")
    private LocalDate winDate;

    // 분리 세대 여부
    @Column(name = "separate_fl", nullable = false)
    private int separate;

    @Column(name = "address", nullable = false)
    private String address;

    // 세대주 여부
    @Column(name = "owner_fl")
    private int owner;

    // 분리 조건 여부
    @Column(name = "condition_fl")
    private int condition;

}

