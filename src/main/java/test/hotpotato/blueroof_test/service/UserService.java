package test.hotpotato.blueroof_test.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import test.hotpotato.blueroof_test.SecurityUtil;
import test.hotpotato.blueroof_test.model.user.User;
import test.hotpotato.blueroof_test.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    // 현재 SecurityContext 에 있는 유저 정보 조회
    @Transactional(readOnly = true)
    public User getMyInfo() {
        return userRepository.findByLoginId(SecurityUtil.getCurrentUsername())
                .orElseThrow(() -> new RuntimeException("로그인 유저 정보가 없습니다."));
    }

    // 로그인 아이디로 유저 정보 조회
    @Transactional(readOnly = true)
    public User getUserInfo(String loginId) {
        return userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new RuntimeException("유저 정보가 없습니다."));
    }
}