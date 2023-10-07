package com.oxahex.user.service;

import com.oxahex.domain.entity.UserInfo;
import com.oxahex.domain.repository.UserInfoRepository;
import com.oxahex.user.dto.UserInfoDto;
import com.oxahex.user.exception.UserException;
import com.oxahex.user.type.ErrorCode;
import com.oxahex.user.util.encrypt.EncryptService;
import com.oxahex.user.util.keygen.GenerateKey;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.GeneralSecurityException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserInfoRepository userInfoRepository;
    private final EncryptService encryptService;

    /**
     * 유저 등록
     * <P> 중복 유저 확인
     * <p> 유저 고유 키 생성 및 반환
     * @param request 등록할 유저 정보
     * @return 유저 고유 키 반환
     */
    @Transactional
    public UserInfoDto.Response saveUser(UserInfoDto.Request request) throws GeneralSecurityException{

        // TODO: pointcut
        // 주민번호 암호화
//        String encryptedRegNum = encryptService.encryptText(request.getUserRegistrationNumber());

        // validation
        boolean isExistUser = userInfoRepository.existsByUserRegistrationNumber(request.getUserRegistrationNumber());
        if (isExistUser) throw new UserException(ErrorCode.USER_ALREADY_EXIST);


        // 고유 식별 키 생성
        String userKey = GenerateKey.getKey();

        // 유저 정보 DB 저장
        userInfoRepository.save(UserInfo.builder()
                        .userName(request.getUserName())
                        .userKey(userKey)
                        .userIncomeAmount(request.getUserIncomeAmount())
                        .userRegistrationNumber(request.getUserRegistrationNumber()).build());

        return UserInfoDto.Response.builder()
                .userKey(userKey).build();
    }


    @Transactional(readOnly = true)
    public UserInfoDto.Response findUser(String userKey) throws GeneralSecurityException {

        // 유저 키로 유저 찾고
        UserInfo userInfo = userInfoRepository.findUserInfoByUserKey(userKey)
                .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND));

        // 복호화
        String regNum = encryptService.decryptString(userInfo.getUserRegistrationNumber());

        return UserInfoDto.Response.builder()
                .userKey(userKey)
                .userRegistrationNumber(regNum).build();
    }
}
