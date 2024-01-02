package kr.seula.HelloSpring.controller;

import kr.seula.HelloSpring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/*
    @Controller
    @Service
    @Repository

    이러한 애노테이션은 스프링이 클래스를
    객체로 만들어 빈으로 등록을 한다. (컴포넌트 스캔)

    이러한 애노테이션은 @Component에
    특수한 기능이 첨가된 애노테이션이다.
    ( 컴포넌트 스캔인 이유 )

    따라서 @Component를 붙이면 자동으로
    스프링 빈으로 등록된다.
*/
@Controller
public class MemberController {
    // MemberController가 MemberService를 의존
    /*
        MemberController가 MemberService를 통해
        회원 가입, 멤버 조회를 할 수 있어야 한다.
    */

    private final MemberService memberService;

    // 의존 관계 주입 (생성자 주입)
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
