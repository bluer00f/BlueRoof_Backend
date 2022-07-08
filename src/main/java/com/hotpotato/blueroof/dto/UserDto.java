package com.hotpotato.blueroof.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hotpotato.blueroof.model.user.Authority;
import com.hotpotato.blueroof.model.user.User;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    @NotNull
    @Size(min = 3, max = 50)
    private String loginId;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @Size(min = 8, max = 100)
    private String password;

    @NotNull
    @Size(min = 3, max = 50)
    private String email;

    @NotNull
    @Size(min = 3, max = 50)
    private String username;

    @NotNull
    private LocalDate birthday;

    private int gender;

    private String phone;

    @NotNull
    private String address;

    @NotNull
    private int marriage;

    @NotNull
    private int soldier;

    private LocalDate appointmentDate;

    public User toUser(PasswordEncoder passwordEncoder) {
        return User.builder()
                .loginId(loginId)
                .password(passwordEncoder.encode(password))
                .email(email)
                .username(username)
                .birthday(birthday)
                .gender(gender)
                .phone(phone)
                .address(address)
                .soldier(soldier)
                .appointmentDate(appointmentDate)
                .authority(Authority.ROLE_USER)
                .build();
    }

}