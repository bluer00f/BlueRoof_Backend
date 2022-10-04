package com.hotpotato.blueroof.dto;

import com.hotpotato.blueroof.service.model.type.CarType;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {

    private CarType carType;

    private int carPrice;

    private int carYear;
}
