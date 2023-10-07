package com.oxahex.domain.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "USER_INFO")
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "usr_key")
    String userKey;

    @Column(name = "usr_reg_num")
    String userRegistrationNumber;

    @Column(name = "usr_nm")
    String userName;

    @Column(name = "usr_icm_amt")
    Long userIncomeAmount;
}
