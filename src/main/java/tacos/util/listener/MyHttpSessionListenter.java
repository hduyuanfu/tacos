package tacos.util.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 通过HttpSessionListener来统计当前在线人数、ip等信息；未了避免并发问题我们使用原子int来计数
 * @author yuanfu
 * @data 2021/5/19 20:41
 */
@Slf4j
// @Component
// @WebListener
public class MyHttpSessionListenter implements HttpSessionListener {

    public static AtomicInteger userCount = new AtomicInteger(0);

    @Override
    public synchronized void sessionCreated(HttpSessionEvent se) {
        userCount.getAndIncrement();
        se.getSession().getServletContext().setAttribute("sessionCount", userCount.get());
        log.info("在线人数增加为：{}", userCount.get());
    }

    @Override
    public synchronized void sessionDestroyed(HttpSessionEvent se) {
        userCount.getAndDecrement();
        se.getSession().getServletContext().setAttribute("sessionCount", userCount.get());
        log.info("在线人数减少为：{}", userCount.get());
    }
}
