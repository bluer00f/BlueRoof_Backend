package com.hotpotato.blueroof.model.information;

import com.hotpotato.blueroof.model.Timestamped;
import com.hotpotato.blueroof.model.type.Flag;
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
// 태아 정보
public class Baby extends Timestamped {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "baby_id")
    private Long id;

    @ManyToOne
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
