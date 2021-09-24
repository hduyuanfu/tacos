package tacos.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tacos.util.listener.MyHttpSessionListenter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 用于测试过滤器，拦截器和监听器的一个控制器
 * @author yuanfu
 * @data 2021/5/19 21:24
 */
@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/addSession")
    public String addSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("name", "yuanfu");
        return "当前在线人数" + session.getServletContext().getAttribute("sessionCount") + "人";
    }

    @GetMapping("/removeSession")
    public String removeSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.invalidate();
        return "当前在线人数" + session.getServletContext().getAttribute("sessionCount") + "人";
    }

    @GetMapping("/online")
    public String online() {
        return "当前在线人数" + MyHttpSessionListenter.userCount.get() + "人";
    }
}
