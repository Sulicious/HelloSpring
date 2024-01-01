package kr.seula.HelloSpring.service;

import kr.seula.HelloSpring.domain.Member;
import kr.seula.HelloSpring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
    /*
        테스트 클래스 메소드는
        메서드의 이름이 한글이여도 됨
        ( 관례 상 )
    */

    /*
        Given | 주어진 조건
        When | ~일 때
        Then | 결과
    */

    MemberService memberService;
    MemoryMemberRepository memoryMemberRepository;

    @BeforeEach
    void beforeEach() {
        memoryMemberRepository = new MemoryMemberRepository();

        /*
            DI : Dependency Injection ( 의존성 주입 )

            MemberService 클래스에서 직접 new 연산자를 통해
            객체를 생성하는 것이 아닌 외부에서 객체를
            넣어주는 방식을 DI라고 한다.
        */
        memberService = new MemberService(memoryMemberRepository);
    }

    @AfterEach
    void afterEach() {
    }

    @Test
    void 회원가입() {
        // Given
        Member member = new Member();
        member.setName("Hello");

        // When
        Long saveId = memberService.join(member);

        // Then
        Member findMember = memberService.findOne(saveId).get();
        assertEquals(member.getName(), findMember.getName());
    }

    @Test
    void 중복_회원_예외() {
        // Given
        Member member1 = new Member();
        member1.setName("Spring");

        Member member2 = new Member();
        member2.setName("Spring");

        // When
        memberService.join(member1);

        // try {
        //     memberService.join(member2);
        //     fail();
        // } catch (IllegalStateException e) {
        //     assertEquals("이미 존재하는 회원입니다.", e.getMessage());
        // }

        // IllegalStateException 타입의 예외가 터져야 됨 ( 반환 됨 )
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> { memberService.join(member2); });

        // Then
        assertEquals("이미 존재하는 회원입니다.", e.getMessage());
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}