package kr.seula.HelloSpring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    /*
        스프링 컨테이너에 관련 컨트롤러가
        먼저 있는지 찾기 때문에
        Welcome 페이지 반환 X
    */
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
