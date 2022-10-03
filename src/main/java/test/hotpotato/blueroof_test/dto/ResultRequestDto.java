package test.hotpotato.blueroof_test.dto;

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
    private Long AptInfoId;

    @NotNull
    private String houseType;

}