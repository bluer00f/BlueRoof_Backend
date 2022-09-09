package com.hotpotato.blueroof.dto;

import com.hotpotato.blueroof.model.type.Flag;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SpouseDto {

    private Flag marriageFlag;

    private String spouseName;

    private Flag foreignFlag;

    private LocalDate spouseBirthday;

    private LocalDate marriageDate;

    private Flag winFlag;

    private LocalDate winDate;

    private int spouseIncome;

    private Flag separateFlag;

    private String spouseAddress;

    private Flag ownerFlag;

    private Flag conditionFlag;
}
