package com.hotpotato.blueroof.controller;

import com.hotpotato.blueroof.dto.SpouseDto;
import com.hotpotato.blueroof.model.user.User;
import com.hotpotato.blueroof.service.SpouseService;
import com.hotpotato.blueroof.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/api/v1/spouse")
@Api(tags = {"Spouse API"})
public class SpouseController {

    private final UserService userService;
    private final SpouseService spouseService;

    // 배우자 정보 저장/수정/삭제 Controller
    @ApiOperation(value = "배우자 정보 저장/수정/삭제")
    @PostMapping("")
    public ResponseEntity<SpouseDto> createSpouse(@RequestBody SpouseDto spouseDto) {
        User user = userService.getMyInfo();
        return ResponseEntity.ok(spouseService.createSpouse(user, spouseDto));
    }

    // 배우자 정보 조회 Controller
    @ApiOperation(value = "배우자 정보 조회")
    @GetMapping("")
    public ResponseEntity<SpouseDto> getSpouse() {
        User user = userService.getMyInfo();
        return ResponseEntity.ok(spouseService.getSpouse(user));
    }
}
