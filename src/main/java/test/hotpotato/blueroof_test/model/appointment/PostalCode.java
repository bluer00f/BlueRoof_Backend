package test.hotpotato.blueroof_test.model.appointment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostalCode {

    //공급지역코드 (PK)
    @Id
    @Column(name = "area_code")
    private String areaCode;

    //공급지역명
    @Column(name = "area_code_nm", nullable = false)
    private String areaCodeNm;

    //우편번호 앞 2자리 , greater or equal
    @Column(name = "postal_head_GTE", nullable = false)
    private String postalHeadGte;

    //우편번호 앞 2자리 , little or equal
    @Column(name = "postal_head_LTE", nullable = false)
    private String postalHeadLte;

}
