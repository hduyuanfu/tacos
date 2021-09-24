package tacos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author yuanfu
 * @data 2021/5/12 18:59
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {

        return "home"; // 返回视图名
    }
}
