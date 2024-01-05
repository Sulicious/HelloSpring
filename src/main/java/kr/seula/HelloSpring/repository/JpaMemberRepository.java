package kr.seula.HelloSpring.repository;

import jakarta.persistence.EntityManager;
import kr.seula.HelloSpring.domain.Member;

import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository {

    /*
        스프링 부트의 JPA 라이브러리를
        의존성으로 사용하면
        스프링이 자동으로 EntityManager를
        빈으로 만들어준다.

        JPA는 EntityManager 객체로 모든게 동작이 된다.

        DB 통신 등 모든걸 처리해줌
    */
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        // JPA가 member.setId() 까지 다 해줌 ㅁㅊ
        // persist: 영속하다
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        /*
            보통 테이블 대상으로 쿼리를 날리지만,
            객체(엔티티)를 대상으로 쿼리를 날림
            그럼 자동으로 SQL로 번역이 됨
        */
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();
    }
}
