package tacos.util.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author yuanfu
 * @data 2021/5/19 21:00
 */
@Slf4j
@Component
public class MyInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("MyInterceptor preHandle 调用了：{}", request.getRequestURI());
        request.setAttribute("requestTime", System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        /**
         * 下面语句一开始是这样写的 if(!request.getRequestURI().contains("/online"))
         * 会报错 Cannot create a session after the response has been committed
         * 根据提示就是下面这行出的错。然后经过分析之后，访问/removeSession后会把session清除，先response已经返回客户端了，再调用
         * request.getSession()就会报错。所以修改了下，对于/removeSession就不进入条件判断就好了
         */
        if(request.getRequestURI().contains("/addSession")) {
            HttpSession session = request.getSession();
            String sessionName = (String) session.getAttribute("name");
            if("yuanfu".equals(sessionName)) {
                log.info("MyInterceptor postHandle调用了，当前浏览器存在 session: {}", sessionName);
            } else {
                log.info("MyInterceptor postHandle调用了，当前浏览器不存在 session: yuanfu");
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        long duration = (System.currentTimeMillis() - (long) request.getAttribute("requestTime"));
        log.info("MyInterceptor afterCompletion调用了，{} 调用耗时: {}ms", request.getRequestURI(), duration);
    }
}
