package kr.seula.HelloSpring.repository;

import kr.seula.HelloSpring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/*
    김영한 St.

    테스트 관련은 꼭 깊이 있게 공부해라
*/

/*
    TDD

    테스트 케이스 클래스를 먼저 개발 후
    기능을 구현하는 개발 방법
*/

/*
    JUnit

    기능이 작동되는지 서비스를 실행시켜
    테스트 할 필요없이,
    테스트 케이스를 만들어 확인 가능
*/
public class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        /*
            모든 테스트 케이스의 메소드는
            서로 의존 관계가 없어야 한다.
        */
        repository.clearStore();
    }

    @Test
    public void save() {
        Member member = new Member();
        // name 속성 설정
        member.setName("spring");
        // 메모리 Map에 저장
        repository.save(member);
        // Map에서 Optional 객체를 찾은 후 객체 얻기 (Null 가능)
        Member result = repository.findById(member.getId()).get();

        // 얻은 객체가 member와 같은지 확인
        // System.out.println("result = " + (result == member));

        // 1: expected, 2: actual | actual과 다르면 빨간불
        Assertions.assertEquals(member, result);
    }

    @Test
    public void findByName() {
        // member1 객체
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        // member2 객체
        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        // findByName으로 member1 객체 찾기
        Member result = repository.findByName("spring1").get();

        // 검증하기
        Assertions.assertEquals(member1, result);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        Assertions.assertEquals(result.size(), 2);
    }
}
