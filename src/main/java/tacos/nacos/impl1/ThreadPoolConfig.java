package tacos.nacos.impl1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yuanfu
 * @date 2021/9/17 14:58
 */
@Configuration
public class ThreadPoolConfig {
    @Bean
    public ScheduledExecutorService getScheduledExecutorService() {
        AtomicInteger poolNum = new AtomicInteger(0);
        ScheduledExecutorService scheduler = new ScheduledThreadPoolExecutor(2, r -> {
            Thread t = new Thread(r);
            t.setName("LoopLongPollingThread-" + poolNum.incrementAndGet());
            return t;
        });
        return scheduler;
    }
}
