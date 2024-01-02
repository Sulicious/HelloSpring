package kr.seula.HelloSpring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 이 애노테이션 하나면 여러 애노테이션 생략 가능
// @ComponentScan 애노테이션을 포함한다.
@SpringBootApplication
public class HelloSpringApplication {
	public static void main(String[] args) {
		// 제어의 역전 (IoC)
		SpringApplication.run(HelloSpringApplication.class, args);
	}

}
