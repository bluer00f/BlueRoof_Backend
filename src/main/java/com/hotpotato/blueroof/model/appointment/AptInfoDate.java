package com.hotpotato.blueroof.model.appointment;

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
public class AptInfoDate {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "apt_info_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "apt_info_id", nullable = false)
    private AptInfo aptInfo;

    // 특별 공급 접수 시작일
    @Column(name = "spsply_rcept_bgnde")
    private LocalDate spsplyRceptBgnde;

    // 특별 공급 접수 종료일
    @Column(name = "spsply_rcept_endde")
    private LocalDate spsplyRceptEndde;

    // 일반 공급 접수 시작일
    @Column(name = "gnrl_rcept_rcptde")
    private LocalDate gnrlRceptRcptde;

    // 일반 공급 접수 종료일
    @Column(name = "gnrl_rcept_endde")
    private LocalDate gnrlRceptEndde;

    // 1순위 접수일 - 해당 지역
    @Column(name = "gnrl_rnk1_crsparea_rcept_pd", nullable = false)
    private LocalDate gnrlRnk1CrspareaRceptPd;

    // 1순위 접수일 - 경기 지역
    @Column(name = "gnrl_rnk1_etc_gg_rcept_pd")
    private LocalDate gnrlRnk1EtcGgAreaRceptPd;

    // 1순위 접수일 - 기타 지역
    @Column(name = "gnrl_rnk1_etc_area_rcept_pd", nullable = false)
    private LocalDate gnrlRnk1EtcAreaRceptPd;

    // 2순위 접수일 - 해당 지역
    @Column(name = "gnrl_rnk2_crsparea_rcept_pd", nullable = false)
    private LocalDate gnrlRnk2CrspareaRceptPd;

    // 2순위 접수일 - 경기 지역
    @Column(name = "gnrl_rnk2_etc_gg_rcept_pd")
    private LocalDate gnrlRnk2EtcGgAreaRceptPd;

    // 2순위 접수일 - 기타 지역
    @Column(name = "gnrl_rnk2_etc_area_rcept_pd", nullable = false)
    private LocalDate gnrlRnk2EtcAreaRceptPd;

    // 홈페이지 주소
    @Column(name = "hmpg_adres", nullable = false)
    private String hmpgAdres;

}
