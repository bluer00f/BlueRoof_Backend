package com.hotpotato.blueroof.dto;

import com.hotpotato.blueroof.model.type.Flag;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionDto {

    private int specialRank;

    private int generalRank;

    private Flag winFlag;

    private LocalDate winDate;

    private String winRegion;

    private String winZipcode;

    private String winName;

    private List<BankBookDto> bankBookList;

}
