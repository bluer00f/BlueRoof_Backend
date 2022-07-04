package com.hotpotato.blueroof.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
// 청약 접수 일정
public class SubDate {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "sub_date_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "parcel_id", nullable = false)
    private Parcel parcel;

    // 청약 접수 구분
    @Column(name = "sub_date_type", nullable = false)
    private String subDateType;

    // 해당 지역 여부
    @Column(name = "region_fl", nullable = false)
    private int region;

    // 청약 접수 일자
    @Column(name = "sub_date", nullable = false)
    private LocalDate subDate;

}
