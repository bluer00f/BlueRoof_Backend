package com.hotpotato.blueroof.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ResultRequestDto {

    @NotNull
    private String supplyType1;

    @NotNull
    private String supplyType2;

    @NotNull
    private Long parcelId;

    @NotNull
    private String houseArea;

}