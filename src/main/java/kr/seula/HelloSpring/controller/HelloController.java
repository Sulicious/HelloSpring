package kr.seula.HelloSpring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
}
