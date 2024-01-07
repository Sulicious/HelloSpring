package kr.seula.HelloSpring.controller;

import kr.seula.HelloSpring.domain.Member;
import kr.seula.HelloSpring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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

    실무에서는 컴포넌트 스캔 방법을 사용함.

    상황에 따라 구현 클래스가 바뀌어야 할 땐
    자바 코드로 빈을 등록하는 방법을 사용함
    ( 기본 MemberService 클래스의 코드를
    일절 손대지 않고 구현체 바꿀 수 있음 )
*/

// 컴포넌트 스캔으로 빈 등록
@Controller
public class MemberController {
    // MemberController가 MemberService를 의존
    /*
        MemberController가 MemberService를 통해
        회원 가입, 멤버 조회를 할 수 있어야 한다.
    */

    private final MemberService memberService;

    // 의존 관계 주입 (Setter 주입)
    // @Autowired
    // public void setMemberService(MemberService memberService) {
    //     this.memberService = memberService;
    // }
    // 단점 : 메서드를 퍼블릭으로 노출해야됨
    // ( 호출 되지 않을 메소드가 호출 될 수 있음 )


    // 의존 관계 주입 (필드 주입)
    // @Autowired final MemberService memberService;
    // 단점 : 딱히 바꿀 방법이 없음

    // 의존 관계 주입 (생성자 주입)
    @Autowired
    public MemberController(MemberService memberService) {
         this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return"members/createMemberForm";
    }

    @PostMapping("/members/new")
    // MemberForm을 페이로드의 타입으로 설정
    public String create(MemberForm form) {
        // Member 도메인을 사용
        // Domain -> 레포에 저장하기 위한 타입
        Member member = new Member();
        member.setName(form.getName());

        // System.out.println("member = " + member.getName());

        // 멤버 레포지토리에 저장
        memberService.join(member);

        /*
            redirect 프로토콜을 사용하는데
            스프링에서 HTTP 리디렉션 응답을 함
         */
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
