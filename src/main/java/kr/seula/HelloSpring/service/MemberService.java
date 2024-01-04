package kr.seula.HelloSpring.service;

import kr.seula.HelloSpring.domain.Member;
import kr.seula.HelloSpring.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class MemberService {
    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memoryMemberRepository) {
        /*
            DI : Dependency Injection ( 의존성 주입 )

            MemberService 클래스에서 직접 new 연산자를 통해
            객체를 생성하는 것이 아닌 외부에서 객체를
            넣어주는 방식을 DI라고 한다.
        */
        this.memberRepository = memoryMemberRepository;
    }

    /*
        회원가입
    */
    public Long join(Member member) {
        validateDuplicateMember(member);

        // 그냥 꺼내고 싶을 때
        // result.get();

        // Optional 안에 객체가 있을 때 꺼내기
        /*
            result.ifPresent(m -> {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            })
        */

        memberRepository.save(member);

        return member.getId();
    }

    /*
        중복 회원 확인
    */
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                 .ifPresent(m -> {
                     throw new IllegalStateException("이미 존재하는 회원입니다.");
                 });
    }

    /*
        전체 회원 조회
    */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /*
        id로 회원 찾기
    */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
