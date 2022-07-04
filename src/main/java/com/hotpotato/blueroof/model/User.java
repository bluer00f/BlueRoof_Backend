package com.hotpotato.blueroof.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
// 회원 기본 정보
public class User extends Timestamped {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    private Long id;

    @Column(name = "login_id", length = 50, nullable = false, unique = true)
    private String loginId;

    @Column(name = "password", length = 100, nullable = false, unique = true)
    @JsonIgnore
    private String password;

    @Column(name = "email", length = 50, nullable = false, unique = true)
    private String email;

    @Column(name = "username", length = 50, nullable = false)
    private String username;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @Column(name = "gender")
    private int gender;

    @Column(name = "phone")
    private String phone;

    @Embedded
    private Address homeAddress;

    // 혼인여부
    @Column(name = "marriage_fl", nullable = false)
    private int marriage;

    // 직업군인여부
    @Column(name = "soldier_fl", nullable = false)
    private int soldier;

    // 직업군인일 경우, 임관일
    @Column(name = "appointment_date")
    private int appointment_date;

    @Enumerated(EnumType.STRING)
    private Authority authority;


}
