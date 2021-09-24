package tacos.nacos.impl3;

import org.springframework.stereotype.Service;

import javax.servlet.AsyncContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yuanfu
 * @date 2021/9/17 16:34
 */
@Service
public class NacosLongPollingServiceImpl implements NacosLongPollingService{
    final ScheduledExecutorService scheduler;
    final Queue<NacosPullTask> nacosPullTasks;

    public NacosLongPollingServiceImpl() {
        scheduler = new ScheduledThreadPoolExecutor(1, r -> {
            Thread t = new Thread(r);
            t.setName("NacosLongPollingTask");
            t.setDaemon(true);
            return t;
        });
        nacosPullTasks = new ConcurrentLinkedDeque<>();
        scheduler.scheduleAtFixedRate(() -> System.out.println("线程存活状态：" + new Date()), 0L, 60, TimeUnit.SECONDS);
    }
    @Override
    public void doGet(String dataId, HttpServletRequest req, HttpServletResponse resp) {
        // 一定要由当前HTTP线程调用，如果放在task线程容器会立即发送响应
        final AsyncContext asyncContext = req.startAsync();
        scheduler.execute(new NacosPullTask(nacosPullTasks, scheduler, asyncContext, dataId, req, resp));
    }

    @Override
    public void push(String dataId, String data) {
        scheduler.schedule(new NacosPushTask(dataId, data, nacosPullTasks), 0L, TimeUnit.MILLISECONDS);
    }
}
