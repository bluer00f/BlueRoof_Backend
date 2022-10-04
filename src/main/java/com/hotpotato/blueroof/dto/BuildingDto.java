package com.hotpotato.blueroof.dto;

import com.hotpotato.blueroof.service.model.type.BuildingType;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BuildingDto {

    private BuildingType buildingType;

    private String buildingZipcode;

    private String buildingAddress;

    private double buildingArea;

    private int buildingPrice;

    private LocalDate buildingDate;
}
