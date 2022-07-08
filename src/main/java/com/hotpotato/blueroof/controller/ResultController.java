package com.hotpotato.blueroof.controller;

import com.hotpotato.blueroof.dto.CommonResponseDto;
import com.hotpotato.blueroof.dto.ResponseDto;
import com.hotpotato.blueroof.dto.ResultRequestDto;
import com.hotpotato.blueroof.dto.ResultResponseDto;
import com.hotpotato.blueroof.model.result.Audit;
import com.hotpotato.blueroof.model.result.Point;
import com.hotpotato.blueroof.model.user.User;
import com.hotpotato.blueroof.service.ResultService;
import com.hotpotato.blueroof.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/api/v1/result")
@Api(tags = {"Result API"})
// 결과
public class ResultController {

    private final UserService userService;
    private final ResultService resultService;

    // 자격 진단
    @ApiOperation(value = "자격 진단")
    @PostMapping("/result")
    public ResponseEntity<? extends ResponseDto> createResult(@Valid @RequestBody ResultRequestDto resultDto) {
        User user = userService.getMyInfo();

        // 가점 계산
        Point point = resultService.point(user, resultDto);

        // 적격 & 부적격 판단
        Audit audit = resultService.audit(user, resultDto, point.getId());

        return ResponseEntity.ok().body(new CommonResponseDto<>(new ResultResponseDto(audit.getIneligible(), audit.getIneligibleReason(), point.getSubPoint())));
    }

}
