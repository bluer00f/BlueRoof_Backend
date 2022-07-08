package com.hotpotato.blueroof.repository;

import com.hotpotato.blueroof.model.information.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    // 회원의 세대원 조회
    List<Member> findAllByUserId(Long userId);
}
