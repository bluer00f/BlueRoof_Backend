package com.hotpotato.blueroof.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hotpotato.blueroof.model.Timestamped;
import com.hotpotato.blueroof.model.type.Flag;
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

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "zipcode", nullable = false)
    private String zipcode;

    // 혼인여부
    @Column(name = "marriage_fl", nullable = false)
    private Flag marriage;

    // 세대주 여부
    @Column(name = "owner_fl", nullable = false)
    private Flag owner;

    // 직업 군인 여부
    @Column(name = "soldier_fl", nullable = false)
    private Flag soldier;

    // 직업군인일 경우, 임관일
    @Column(name = "appointment_date")
    private LocalDate appointmentDate;

    @Enumerated(EnumType.STRING)
    private Authority authority;


}
