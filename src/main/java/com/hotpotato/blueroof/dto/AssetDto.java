package com.hotpotato.blueroof.dto;

import com.hotpotato.blueroof.service.model.type.Flag;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AssetDto {
    private int incomeMonthPrice;

    private Flag buildingFlag;
    private List<BuildingDto> building;

    private Flag landFlag;
    private List<LandDto> land;

    private Flag carFlag;
    private List<CarDto> car;

    private LocalDate houseEndDate;
}
