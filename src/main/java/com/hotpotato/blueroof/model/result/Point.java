package com.hotpotato.blueroof.model.result;

import com.hotpotato.blueroof.model.user.User;
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
// 회원 가점 결과
public class Point {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "point_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 청약 총 가점
    @Column(name = "sub_point", nullable = false)
    private int subPoint;

    // 무주택 가점
    @Column(name = "house_point", nullable = false)
    private int housePoint;

    // 부양 가족 가점
    @Column(name = "member_point", nullable = false)
    private int memberPoint;

    // 청약 통장 가점
    @Column(name = "account_point", nullable = false)
    private int accountPoint;

    // 기타 가점
    @Column(name = "other_point", nullable = false)
    private int otherPoint;

    // 기타 가점 내역
    @Column(name = "other_content", length=50)
    private String otherContent;

}
