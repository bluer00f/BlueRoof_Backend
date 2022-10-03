package test.hotpotato.blueroof_test.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResultResponseDto {

    private String supplyType1;

    private String supplyType2;

    private String houseType;

    private String audit;

    private String auditReason;

    private int point;

}
