package com.oxahex.user.aop;

import com.oxahex.user.annotation.Encrypt;
import com.oxahex.user.util.encrypt.EncryptService;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.security.GeneralSecurityException;
import java.util.Arrays;

@Aspect
@Component
@RequiredArgsConstructor
public class EncryptAop {

    private final EncryptService encryptService;


    @Pointcut("execution(* com.oxahex.user.service.*.find*(..))")
    public void findMethods() {};

    @Pointcut("execution(* com.oxahex.user.service.*.save*(..))")
    public void saveMethods() {}

    // saveUser 할 때, 암호화 해서 저장
    @Before("saveMethods()")
    public void beforeSave(JoinPoint joinPoint) throws IllegalAccessException, GeneralSecurityException {
        System.out.println("암호화 args: " + Arrays.toString(joinPoint.getArgs()));
        for (Object arg: joinPoint.getArgs()) {
            for (Field f : arg.getClass().getDeclaredFields()) {
                if (f.isAnnotationPresent(Encrypt.class)) {
                    f.setAccessible(true);
                    String encrypted = encryptService.encryptText(f.get(arg).toString());
                    f.set(arg, encrypted);
                }
            }
        }
    }

    // findUser 시 복호화 해서 return
    @AfterReturning("findMethods()")
    public void afterFind(JoinPoint joinPoint) {
        System.out.println("복호: args" + Arrays.toString(joinPoint.getArgs()));
    }

}
