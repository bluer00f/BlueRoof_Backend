package test.hotpotato.blueroof_test.model.information;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import test.hotpotato.blueroof_test.model.Timestamped;
import test.hotpotato.blueroof_test.model.type.BuildingType;
import test.hotpotato.blueroof_test.model.user.User;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//건물
public class Building extends Timestamped {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "building_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 건물 유형
    @Column(name = "building_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private BuildingType buildingType;

    // 주거용 건물 여부 (0이면 주거용)
    @Column(name = "house")
    private int house;

    // 건물 우편번호
    @Column(name = "building_zipcode", nullable = false)
    private String buildingZipcode;

    // 건물 주소
    @Column(name = "building_address", nullable = false)
    private String buildingAddress;

    // 건물 면적 (제곱 미터)
    @Column(name = "building_area", nullable = false)
    private double buildingArea;

    // 건물 금액
    @Column(name = "building_price", nullable = false)
    private int buildingPrice;

    // 건물 취득일
    @Column(name = "building_date")
    private LocalDate buildingDate;
}
