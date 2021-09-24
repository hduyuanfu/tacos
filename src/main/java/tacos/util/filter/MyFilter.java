package tacos.util.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

/**
 * @author yuanfu
 * @data 2021/5/19 20:53
 */
@Slf4j
// @Component
@WebFilter(filterName = "myFilter", urlPatterns = "/test/*")
public class MyFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("过滤器初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) servletResponse);
        String requestURI = request.getRequestURI();
        log.info("请求地址是：{}", requestURI);
        if(requestURI.contains("/addSession")
            || requestURI.contains("/removeSession")
            || requestURI.contains("/online")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            wrapper.sendRedirect("/online");
        }
    }

    @Override
    public void destroy() {
        log.info("销毁过滤器");
    }
}
