package kr.seula.HelloSpring.repository;

import kr.seula.HelloSpring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/*
    JpaRepository 인터페이스를 상속 받으면
    스프링(Spring Data JPA)에서 자동으로 구현체를 만들어서
    빈에 등록을 해줌
*/
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
    /*
        findBy 뒤에 오는 Name, Id 나 And, Or 조합으로
        만들어지는 JPQL이 달라짐
        (인터페이스 이름만으로 개발 끝)
    */
    @Override
    Optional<Member> findByName(String name);
}
