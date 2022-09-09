package com.hotpotato.blueroof.controller;

import com.hotpotato.blueroof.dto.*;
import com.hotpotato.blueroof.model.user.User;
import com.hotpotato.blueroof.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/api/v1/user")
@Api(tags = {"User API"})
public class UserController {

    private final UserService userService;

    // 로그인 Controller
    @ApiOperation(value = "로그인")
    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@Valid @RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(userService.login(loginDto));
    }

    @ApiOperation(value = "토큰 재발행")
    @PostMapping("/reissue")
    public ResponseEntity<TokenDto> reissue(@RequestBody TokenRequestDto tokenRequestDto) {
        return ResponseEntity.ok(userService.reissue(tokenRequestDto));
    }

    // 회원가입 Controller
    @ApiOperation(value = "회원가입")
    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@Valid @RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.signup(userDto));
    }

    // 회원 정보 수정
    @ApiOperation(value = "회원 정보 수정")
    @PutMapping("/update")
    public ResponseEntity<Object> update(@Valid @RequestBody UserDto userDto) {
        User user = userService.getMyInfo();
        return ResponseEntity.ok(userService.update(user, userDto));
    }

    // 회원 탈퇴 Controller
    @ApiOperation(value = "회원 탈퇴")
    @DeleteMapping("/withdraw")
    public ResponseEntity withdraw() {
        User user = userService.getMyInfo();
        userService.withdraw(user);
        return new ResponseEntity(HttpStatus.OK);
    }

    // 아이디 중복 확인 controller (존재하면 true)
    @ApiOperation(value = "아이디 중복 확인")
    @GetMapping("/check/{loginId}")
    public ResponseEntity<Boolean> checkUserIdDuplicate(@PathVariable String loginId) {
        return ResponseEntity.ok(userService.checkIdDuplication(loginId));
    }

    // SpringContext 에서 유저 정보 조회
    @ApiOperation(value = "유저 정보 조회 - SpringContext 내")
    @GetMapping("/")
    public ResponseEntity<User> getMyUserInfo() {
        return ResponseEntity.ok(userService.getMyInfo());
    }

    // 로그인 아이디로 유저 정보 조회
    @ApiOperation(value = "유저 정보 조회 - 로그인 아이디")
    @GetMapping("/{loginId}")
    public ResponseEntity<User> getUserInfo(@PathVariable String loginId) {
        return ResponseEntity.ok(userService.getUserInfo(loginId));
    }

    // 혼인 여부 조회
    @ApiOperation(value = "혼인 여부 조회")
    @GetMapping("/marriage")
    public ResponseEntity<? extends ResponseDto> getMarriage() {
        User user = userService.getMyInfo();
        return ResponseEntity.ok().body(new CommonResponseDto<>(userService.getMarriage(user)));
    }
}
