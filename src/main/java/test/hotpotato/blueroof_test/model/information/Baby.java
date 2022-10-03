package test.hotpotato.blueroof_test.model.information;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import test.hotpotato.blueroof_test.model.Timestamped;
import test.hotpotato.blueroof_test.model.type.Flag;
import test.hotpotato.blueroof_test.model.user.User;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
// 태아 정보
public class Baby extends Timestamped {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "baby_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // 임신 여부
    @Column(name = "pregnant_fl", nullable = false)
    @Enumerated(EnumType.STRING)
    private Flag pregnant;

    // 태아 수
    @Column(name = "baby_count")
    private int babyCount;

}
