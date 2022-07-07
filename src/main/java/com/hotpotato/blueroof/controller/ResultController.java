package com.hotpotato.blueroof.controller;

import com.hotpotato.blueroof.dto.CommonResponseDto;
import com.hotpotato.blueroof.dto.ResponseDto;
import com.hotpotato.blueroof.model.user.User;
import com.hotpotato.blueroof.service.ResultService;
import com.hotpotato.blueroof.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/api/v1/result")
@Api(tags = {"Result API"})
// 결과
public class ResultController {

    private final UserService userService;
    private final ResultService resultService;

    // 적격 & 부적격 판단
    @ApiOperation(value = "적격 & 부적격 판단")
    @PostMapping("/audit")
    public ResponseEntity<? extends ResponseDto> createAudit() {
        User user = userService.getMyInfo();

        return ResponseEntity.ok().body(new CommonResponseDto<>(resultService.audit(user)));
    }
}
