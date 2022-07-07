package com.hotpotato.blueroof.service;

import com.hotpotato.blueroof.model.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class ResultService {

    // 적격 & 부적격 판단
    @Transactional
    public boolean audit(User user) {
        return true;
    }

}
