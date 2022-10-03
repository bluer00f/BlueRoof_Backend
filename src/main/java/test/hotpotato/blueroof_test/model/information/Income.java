package test.hotpotato.blueroof_test.model.information;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import test.hotpotato.blueroof_test.model.Timestamped;
import test.hotpotato.blueroof_test.model.user.User;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
// 소득
public class Income extends Timestamped {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "income_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 월 평균 소득
    @Column(name = "income_month_price")
    private int incomeMonthPrice;

}