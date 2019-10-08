package kr.co.m_n09;

import kr.co.m_n09.push.PushService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("")
    private String main() throws Exception {
        return "index";
    }
}
