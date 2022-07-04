package com.hotpotato.blueroof.controller;

import com.hotpotato.blueroof.service.ResultService;
import com.hotpotato.blueroof.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/api/v1/result")
// @Api(tags = {"User API"})
// 결과
public class ResultController {

    private final UserService userService;
    private final ResultService resultService;

    // 적격 & 부적격 판단
    // @ApiOperation(value = "적격 & 부적격 판단")
    //@PostMapping("/audit")
    //public ResponseEntity<? extends ResponseDto> createAudit() {

    //}
}
