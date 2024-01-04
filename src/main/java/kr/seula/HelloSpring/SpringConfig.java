package kr.seula.HelloSpring;

import kr.seula.HelloSpring.repository.JdbcMemberRepository;
import kr.seula.HelloSpring.repository.MemberRepository;
import kr.seula.HelloSpring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

// 자바 코드로 빈 등록
/*
    @Configuration 도
    스프링 빈으로 관리가 됨
*/
@Configuration
public class SpringConfig {

    private final DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new JdbcMemberRepository(dataSource);
    }
}
