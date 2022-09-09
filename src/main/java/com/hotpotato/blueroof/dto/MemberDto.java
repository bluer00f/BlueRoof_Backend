package com.hotpotato.blueroof.dto;

import com.hotpotato.blueroof.model.type.Flag;
import com.hotpotato.blueroof.model.type.Relation;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {

    private String memberName;

    private LocalDate memberBirthday;

    private Relation relation;

    private Flag ownerFlag;

    private int memberIncome;

    private Flag houseFlag;

    private Flag winFlag;

    private LocalDate winDate;
}
