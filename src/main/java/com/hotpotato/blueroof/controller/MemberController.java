package com.hotpotato.blueroof.controller;

import com.hotpotato.blueroof.dto.AllMemberDto;
import com.hotpotato.blueroof.model.user.User;
import com.hotpotato.blueroof.service.MemberService;
import com.hotpotato.blueroof.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/api/v1/member")
@Api(tags = {"All Member API"})
public class MemberController {
    private final UserService userService;
    private final MemberService memberService;

    // 세대 구성원(+태아) 정보 저장/수정/삭제 Controller
    @ApiOperation(value = "세대 구성원(+태아) 정보 저장/수정/삭제")
    @PostMapping("")
    public ResponseEntity<AllMemberDto> createAllMember(@RequestBody AllMemberDto allMemberDtoList) {
        User user = userService.getMyInfo();
        return ResponseEntity.ok(memberService.createAllMember(user, allMemberDtoList));
    }

    // 세대 구성원(+태아) 정보 조회 Controller
    @ApiOperation(value = "세대 구성원(+태아) 정보 조회")
    @GetMapping("")
    public ResponseEntity<AllMemberDto> getAllMember() {
        User user = userService.getMyInfo();
        return ResponseEntity.ok(memberService.getAllMember(user));
    }


}
