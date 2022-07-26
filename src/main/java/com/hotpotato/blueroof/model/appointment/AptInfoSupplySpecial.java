package com.hotpotato.blueroof.model.appointment;

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
// 특별 공급 대상
public class AptInfoSupplySpecial {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "apt_info_supply_special_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "apt_info_id", nullable = false)
    private AptInfo aptInfo;

    // 공급 세대수 - 다자녀 가구
    @Column(name = "supply_many_child", nullable = false)
    private int supplyManyChild;

    // 공급 세대수 - 신혼 부부
    @Column(name = "supply_new_marriage", nullable = false)
    private int supplyNewMarriage;

    // 공급 세대수 - 생애 최초
    @Column(name = "supply_first", nullable = false)
    private int supplyFirst;

    // 공급 세대수 - 노부모 부양
    @Column(name = "supply_old_parent", nullable = false)
    private int supplyOldParent;

    // 공급 세대수 - 기관 추천
    @Column(name = "supply_recommendation", nullable = false)
    private int supplyRecommendation;

    // 공급 세대수 - 이전 기관
    @Column(name = "supply_transfer", nullable = false)
    private int supplyTransfer;

    // 공급 세대수 - 기타
    @Column(name = "supply_other", nullable = false)
    private int supplyOther;

}
