package kr.seula.HelloSpring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    // 라우트를 GET 방식으로 매핑
    @GetMapping("hello")
    // Model은 인터페이스이지만 IoC여서 스프링이 객체를 넣어줌
    public String hello(Model model) {
        model.addAttribute("data", "Hello !!");

        // HelloController -> viewResolver
        return "hello";
    }

    @GetMapping("hello-mvc")
    // 파라미터로 name을 받아서 model 객체에 name 이름으로 추가
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    // API 방식
    @GetMapping("hello-string")
    // HTTP에서 Body에 리턴값을 넣겠다는 의미
    @ResponseBody
    // name: spring
    public String helloString(@RequestParam("name") String name) {
        // 기본 문자는 StringConverter로 Body에 문자 내용을 변환
        // HttpMessageConverter.StringConverter
        return "hello " + name; // "hello spring"
    }

    // API 방식
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);

        // 객체를 반환하면 자동으로 JSON 변환 (Jackson2)
        // HttpMessageConverter.JsonConverter
        return hello;
    }
    /*
        Http 요청의 헤더의 Accept에 따라
        HttpMessageConverter가 결정됨
        (String, JSON, XML)
    */

    /*
        자바 빈 표준 방식 - Getter & Setter
        Getter와 Setter로만 접근
    */
    static class Hello {
        // 변수에 직접 접근 X
        private String name;

        // Getter
        public String getName() {
            return name;
        }

        // Setter
        public void setName(String name) {
            this.name = name;
        }
    }
}
