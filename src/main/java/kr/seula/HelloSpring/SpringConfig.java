package kr.seula.HelloSpring;

import jakarta.persistence.EntityManager;
import kr.seula.HelloSpring.domain.Member;
import kr.seula.HelloSpring.repository.JpaMemberRepository;
import kr.seula.HelloSpring.repository.MemberRepository;
import kr.seula.HelloSpring.repository.SpringDataJpaMemberRepository;
import kr.seula.HelloSpring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


// 자바 코드로 빈 등록
/*
    @Configuration 도
    스프링 빈으로 관리가 됨
*/
@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

    /*
        @Bean
        public MemberRepository memberRepository() {
            return new JpaMemberRepository(em);
        }
    */
}
