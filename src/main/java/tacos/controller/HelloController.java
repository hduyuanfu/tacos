package tacos.controller;

import org.springframework.web.bind.annotation.*;

/**
 * @author yuanfu
 * @data 2021/5/19 15:47
 */
@RestController
@RequestMapping("/hello")
public class HelloController {

    @ResponseBody
    @GetMapping//(value = "/hello")
    public String hello(@RequestParam("name") String name) {
        return "hello " + name;
    }
}
