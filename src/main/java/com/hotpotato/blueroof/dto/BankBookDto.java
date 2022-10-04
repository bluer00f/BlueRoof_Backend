package com.hotpotato.blueroof.dto;

import com.hotpotato.blueroof.service.model.type.BankBookType;
import com.hotpotato.blueroof.service.model.type.Flag;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BankBookDto {

    private BankBookType bankBookType;

    private LocalDate bankBookDate;

    private Flag teenFlag;

    private int teenCount;

    private int teenPrice;

    private int teenSumPrice;

    private int totalPrice;

    private int totalCount;
}
