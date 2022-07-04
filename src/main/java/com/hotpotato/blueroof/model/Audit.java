package com.hotpotato.blueroof.model;

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
    @JoinColumn(name = "parcel_id", nullable = false)
    private Parcel parcel;

    @OneToOne
    @JoinColumn(name = "point_id", nullable = false)
    private Point point;

    // 부적격 여부
    @Column(name = "ineligible_fl", nullable = false)
    private int ineligible;

    // 부적격 사유
    @Column(name = "ineligible_reason", length = 50)
    private String ineligibleReason;

}

