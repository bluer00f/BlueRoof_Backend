package com.hotpotato.blueroof.dto;

import com.hotpotato.blueroof.model.type.LandType;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LandDto {

    private LandType landType;

    private String landZipcode;

    private String landAddress;

    private double landArea;

    private int landPrice;
}
