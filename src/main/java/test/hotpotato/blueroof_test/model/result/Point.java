package test.hotpotato.blueroof_test.model.result;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import test.hotpotato.blueroof_test.model.user.User;

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


    //가점표>가점항목 결과 model

    // 청약 총 가점
    @Column(name = "sub_point", nullable = false)
    private int subPoint;

    //1.가구소득 가점 (최대 1점)
    @Column(name = "house_income_point")
    private int houseIncomePoint;

    //2. 미성년 자녀 수 가점 (최대 3점)
    @Column(name = "children_num_point")
    private int childrenNumPoint;

    //3. 해당 지역구 연속거주기간 가점 (최대 3점)
    @Column(name = "continually_residence_point")
    private int continuallyResidencePoint;

    //4. 주택청약 납입횟수 가점 (최대 3점)
    @Column(name = "bankBook_total_count_point")
    private int bankBookTotalCountPoint;

    //5-1. 혼인기간 가점 (*신혼부부만) (최대 3점)
    @Column(name = "new_marriage_point")
    private int newMarriagePoint;

    //5-2. 자녀 나이 가점 (*한부모 가족만) (최대 3점)
    @Column(name = "single_parent_point")
    private int singleParentPoint;

/*
    // 무주택 가점
    @Column(name = "house_point")
    private int housePoint;

    // 부양 가족 가점
    @Column(name = "member_point")
    private int memberPoint;

    // 청약 통장 가점
    @Column(name = "account_point")
    private int accountPoint;

    // 기타 가점
    @Column(name = "other_point")
    private int otherPoint;

    // 기타 가점 내역
    @Column(name = "other_content", length = 50)
    private String otherContent;*/


}