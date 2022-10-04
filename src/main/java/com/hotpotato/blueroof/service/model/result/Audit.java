package com.hotpotato.blueroof.service.model.result;

import com.hotpotato.blueroof.service.model.appointment.AptInfo;
import com.hotpotato.blueroof.service.model.type.Flag;
import com.hotpotato.blueroof.service.model.user.User;
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
// 회원 적격/부적격 판단 결과
public class Audit {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "audit_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "apt_info_id", nullable = false)
    private AptInfo apt_info_id;

    @OneToOne
    @JoinColumn(name = "point_id", nullable = false)
    private Point point;

    // 공급 유형1
    @Column(name = "supply_type1", nullable = false)
    private String supplyType1;

    // 공급 유형2
    @Column(name = "supply_type2", nullable = false)
    private String supplyType2;

    // 주택형
    @Column(name = "houseType", nullable = false)
    private String houseType;

    // 부적격 여부
    @Column(name = "ineligible_fl", nullable = false)
    @Enumerated(EnumType.STRING)
    private Flag ineligible;

    // 부적격 사유
    @Column(name = "ineligible_reason")
    private String ineligibleReason;

}

