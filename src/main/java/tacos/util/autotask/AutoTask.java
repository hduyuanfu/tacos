package tacos.util.autotask;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 基于注解的定时任务，springboot自带功能，无需额外包
 *
 * @author yuanfu
 * @data 2021/5/14 19:38
 */
@Slf4j
@Component
// @EnableScheduling
public class AutoTask {

    /**
     * 对于一些复杂的定期任务，可以用cron表达式
     */
    // @Scheduled(cron="*/6 * * * * ?")
    private void process1() {
        log.info("autoTask 1");
    }

    /**
     * 以固定延迟8s调用一次执行，这个周期以上一个调用任务的完成时间为基准，
     * 上个任务完成之后，8s后再次执行
     */
    // @Scheduled(fixedDelay = 8000)
    private void process2() {
        log.info("autoTask 2");
    }

    /**
     * 以固定速率执行，以下方法将以固定速率10s来调用一次执行，这个周期是以上一个任务开始时间
     * 为基准，从上一个任务开始执行后10s再次调用
     */
    // @Scheduled(fixedRate = 10000)
    private void process3() {
        log.info("autoTask 3");
    }
}
