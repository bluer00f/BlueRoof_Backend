package test.hotpotato.blueroof_test.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import test.hotpotato.blueroof_test.dto.CommonResponseDto;
import test.hotpotato.blueroof_test.dto.ResponseDto;
import test.hotpotato.blueroof_test.dto.ResultRequestDto;
import test.hotpotato.blueroof_test.dto.ResultResponseDto;
import test.hotpotato.blueroof_test.model.result.Audit;
import test.hotpotato.blueroof_test.model.result.Point;
import test.hotpotato.blueroof_test.model.user.User;
import test.hotpotato.blueroof_test.service.ResultService;
import test.hotpotato.blueroof_test.service.UserService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/api/v1/result")
@Api(tags = {"Result API"})

//결과
public class ResultController {

    private final UserService userService;
    private final ResultService resultService;

    // 자격 진단
    @ApiOperation(value = "자격 진단")
    @PostMapping("")
    public ResponseEntity<? extends ResponseDto> createResult(@Valid @RequestBody ResultRequestDto resultDto) {
        User user = userService.getMyInfo();

        // 적격 & 부적격 판단
        Audit audit = resultService.audit(user, resultDto);

        // 가점 계산
        Point point = resultService.point(user, resultDto, audit.getId());


        ResultResponseDto resultResponseDto = new ResultResponseDto(resultDto.getSupplyType1(), resultDto.getSupplyType2(),
                resultDto.getHouseType(), audit.getIneligible().toString(), audit.getIneligibleReason(), point.getSubPoint());

        return ResponseEntity.ok().body(new CommonResponseDto<>(resultResponseDto));
    }

    //가점 계산 (임시)
    @ApiOperation(value = "적격 후 가점 계산 진단")
    @PostMapping("/getpoint")
    public ResponseEntity<? extends ResponseDto> createResult(@Valid @RequestBody ResultRequestDto resultDto,@Valid @RequestBody User user,@Valid @RequestBody Audit audit) {

        Point point = resultService.point(user, resultDto, audit.getId());


        ResultResponseDto resultResponseDto = new ResultResponseDto(resultDto.getSupplyType1(), resultDto.getSupplyType2(),
                resultDto.getHouseType(), audit.getIneligible().toString(), audit.getIneligibleReason(), point.getSubPoint());

        return ResponseEntity.ok().body(new CommonResponseDto<>(resultResponseDto));
    }
}