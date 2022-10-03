package test.hotpotato.blueroof_test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.hotpotato.blueroof_test.model.information.Member;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    // 회원의 세대원 조회
    List<Member> findAllByUserId(Long userId);

    void deleteAllByUserId(Long id);
}