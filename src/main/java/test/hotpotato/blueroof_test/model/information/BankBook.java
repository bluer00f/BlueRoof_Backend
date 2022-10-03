package test.hotpotato.blueroof_test.model.information;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import test.hotpotato.blueroof_test.model.type.BankBookType;
import test.hotpotato.blueroof_test.model.user.User;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
// 청약 통장 정보
public class BankBook {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "bank_book_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 통장 종류
    @Column(name = "bank_book_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private BankBookType bankBookType;

    // 가입 일자
    @Column(name = "bank_book_date")
    private LocalDate bankBookDate;

    // 미성년자 납입 횟수
    @Column(name = "teen_count")
    private int teenCount;

    // 미성년자 전체 납입 금액
    @Column(name = "teen_price")
    private int teenPrice;

    // 미성년 납입 금액 합 (24개)
    @Column(name = "teen_sum_price")
    private int teenSumPrice;

    // 총 납입 금액
    @Column(name = "total_price", nullable = false)
    private int totalPrice;

    // 납입 횟수
    @Column(name = "total_count", nullable = false)
    private int totalCount;

}