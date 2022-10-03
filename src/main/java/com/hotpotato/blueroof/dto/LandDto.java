package com.hotpotato.blueroof.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LandDto {

    private String landZipcode;

    private String landAddress;

    private double landArea;

    private int landPrice;

    private LocalDate landDate;
}
