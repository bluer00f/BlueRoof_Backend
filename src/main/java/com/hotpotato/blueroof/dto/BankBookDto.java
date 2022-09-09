package com.hotpotato.blueroof.dto;

import com.hotpotato.blueroof.model.type.BankBookType;
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

    private int teenCount;

    private int teenPrice;

    private int teenSumPrice;

    private int totalPrice;

    private int totalCount;
}
