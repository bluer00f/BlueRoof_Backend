package com.hotpotato.blueroof.controller;

import com.hotpotato.blueroof.dto.AssetDto;
import com.hotpotato.blueroof.model.user.User;
import com.hotpotato.blueroof.service.AssetService;
import com.hotpotato.blueroof.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@CrossOrigin
@RequestMapping("/api/v1/asset")
@Api(tags = {"Asset API"})
public class AssetController {
    private final UserService userService;
    private final AssetService assetService;

    // 자산 정보 저장/수정/삭제 Controller
    @ApiOperation(value = "자산 정보 저장/수정/삭제")
    @PostMapping("")
    public ResponseEntity<AssetDto> createAsset(@RequestBody AssetDto assetDto) {
        User user = userService.getMyInfo();
        return ResponseEntity.ok(assetService.createAsset(user, assetDto));
    }

    // 자산 정보 조회 Controller
    @ApiOperation(value = "자산 정보 조회")
    @GetMapping("")
    public ResponseEntity<AssetDto> getAsset() {
        User user = userService.getMyInfo();
        return ResponseEntity.ok(assetService.getAsset(user));
    }
}
