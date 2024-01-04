package kr.seula.HelloSpring.service;

import kr.seula.HelloSpring.domain.Member;
import kr.seula.HelloSpring.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/*
    통합 테스트 ( DB 연결 포함 )

    이거 보단 단위 테스트 잘 만드는게
    더 좋다 ( 김영한 St. )
*/
@SpringBootTest
/*
    Test용 코드의
    트랜잭션을 완료한 후
    커밋을 하지 않고 롤백을 함

    Transaction -> Commit (실제 반영) (X)
    Transaction (O)
*/
@Transactional
public class MemberServiceIntegrationService {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    /*
        @AfterEach
        public void afterEach() {
            memberRepository.deleteAll();
        }
    */

    @Test
    // @Commit -> 끝나고 반영
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
