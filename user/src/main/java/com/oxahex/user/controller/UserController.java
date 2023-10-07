package com.oxahex.user.controller;

import com.oxahex.domain.entity.UserInfo;
import com.oxahex.user.dto.ResponseDto;
import com.oxahex.user.dto.UserInfoDto;
import com.oxahex.user.service.UserService;
import com.oxahex.user.type.SuccessCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.GeneralSecurityException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fintech/v1/user")
public class UserController {

    private final UserService userService;

    /**
     * 유저 정보를 받아 저장하고, 고유 식별 키를 반
     * @param request 등록할 유저 정보
     * @return 해당 유저의 고유 식별 키
     */
    @PostMapping("/information")
    public ResponseEntity<ResponseDto.Success> addUserInfo(
            @RequestBody UserInfoDto.Request request
    ) throws GeneralSecurityException {

        return ResponseEntity.ok().body(
                ResponseDto.Success.builder()
                .data(userService.addUser(request))
                .responseCode(SuccessCode.SUCCESS.getStatus().value())
                .responseMessage(SuccessCode.SUCCESS.getDescription()).build()
        );
    }

    @GetMapping("/private-info/{userKey}")
    public ResponseEntity<ResponseDto.Success> getPrivateUserInfo(
            @PathVariable String userKey
    ) throws GeneralSecurityException {

        return ResponseEntity.ok(
                ResponseDto.Success.builder()
                        .data(userService.getUserInfo(userKey))
                        .responseCode(SuccessCode.SUCCESS.getStatus().value())
                        .responseMessage(SuccessCode.SUCCESS.getDescription()).build()
        );
    }
}
