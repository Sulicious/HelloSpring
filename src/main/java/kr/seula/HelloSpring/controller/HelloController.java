package kr.seula.HelloSpring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
    @GetMapping("hello")
    // Model은 인터페이스이지만 IoC여서 스프링이 객체를 넣어줌
    public String hello(Model model) {
        model.addAttribute("data", "Hello !!");

        // HelloController -> viewResolver
        return "hello";
    }
}
