package com.hotpotato.blueroof.controller;

import com.hotpotato.blueroof.dto.SubscriptionDto;
import com.hotpotato.blueroof.model.user.User;
import com.hotpotato.blueroof.service.SubscriptionService;
import com.hotpotato.blueroof.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/api/v1/subscription")
@Api(tags = {"Subscription API"})
public class SubscriptionController {

    private final UserService userService;
    private final SubscriptionService subscriptionService;

    // 청약 정보 저장/수정/삭제 Controller
    @ApiOperation(value = "청약 정보 저장/수정/삭제")
    @PostMapping("")
    public ResponseEntity<SubscriptionDto> createSubscription(@RequestBody SubscriptionDto subscriptionDto) {
        User user = userService.getMyInfo();
        return ResponseEntity.ok(subscriptionService.createSubscription(user, subscriptionDto));
    }

    // 청약 정보 조회 Controller
    @ApiOperation(value = "청약 정보 조회")
    @GetMapping("")
    public ResponseEntity<SubscriptionDto> getSubscription() {
        User user = userService.getMyInfo();
        return ResponseEntity.ok(subscriptionService.getSubscription(user));
    }

}
