package com.hotpotato.blueroof.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResultResponseDto {

    private String audit;

    private String auditReason;

    private int point;

}
