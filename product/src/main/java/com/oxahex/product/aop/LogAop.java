package com.oxahex.product.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Component
public class LogAop {

    // 컨트롤러 이해의 모든 패키지, 클래스 이하 모든 메서드에 적용
    @Pointcut("execution(* com.oxahex.user.controller..*.*(..))")
    private void cut() {}

    // 포인트컷으로 필터링된 경로롤 들어오는 경우 메서드 호출 전에 적용
    @Before("cut()")
    public void beforeUserControllerCall(JoinPoint joinPoint) {

        // 메서드 정보
        Method method = getMethod(joinPoint);
        log.info("method name = {}", method.getName());

        // 파라미터
        Object[] args = joinPoint.getArgs();
        if (args.length <= 0) log.info("no parameter");
        for (Object arg : args) {
            log.info("parameter type = {}", arg.getClass().getSimpleName());
            log.info("parameter value = {}", arg);
        }
    }

    // 포인트컷으로 필터링된 경로로 들어오는 경우 메서드 리턴 후 적용 (response)
    @AfterReturning(value = "cut()", returning = "returnObject")
    public void afterUserControllerCall(JoinPoint joinPoint, Object returnObject) {

        // 메서드 정보
        Method method = getMethod(joinPoint);
        log.info("method name = {}", method.getName());
        log.info("return type = {}", returnObject.getClass().getSimpleName());
        log.info("return value = {}", returnObject);
    }


    // JointPoint로 메서드 정보 가져옴
    private Method getMethod(JoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        return signature.getMethod();
    }
}

