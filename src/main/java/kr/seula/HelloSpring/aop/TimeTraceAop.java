package kr.seula.HelloSpring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/*
    AOP (시간 재기)

    어느 메서드에서 병목 현상이
    발생하는지 쉽게 알 수 있다

    중간에 어떤 조건일 때는
    "하지마!" 등을 할 수 있다.
*/

// 실제 MemberService를 복제하여 가짜(프록시)를 만들어 시간을 재는 기술
// ( 이후 joinPoint.proceed() 를 통해서 진짜를 호출 )
@Aspect
@Component
public class TimeTraceAop {

    // 공통 관심사 어디에 적용할지 타겟팅
    // (여기서는 패키지 전체)

    // 이렇게 서비스 패키지 하위만 적용할 수도 있음
    // @Around("execution(* kr.seula.HelloSpring.service..*(..))")
    @Around("execution(* kr.seula.HelloSpring..*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("Start : " + joinPoint.toString());

        try {
            // 다음 메서드로 진행
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long result = finish - start;

            System.out.println("End : " + joinPoint.toString() + " " + result + "ms");
        }
    }
}
