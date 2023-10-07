package com.oxahex.domain.repository;

import com.oxahex.domain.entity.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {
    boolean existsByUserRegistrationNumber(String userRegistrationNumber);
    Optional<UserInfo> findUserInfoByUserKey(String userKey);
}
