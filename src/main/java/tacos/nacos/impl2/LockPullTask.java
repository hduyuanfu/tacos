package tacos.nacos.impl2;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.concurrent.Callable;

/**
 * @author yuanfu
 * @date 2021/9/17 16:24
 */
@Slf4j
@Data
public class LockPullTask implements Callable<String> {
    @Getter
    @Setter
    public volatile String data;
    private Object lock;

    public LockPullTask(Object lock) {
        this.lock = lock;
    }

    @Override
    public String call() throws Exception {
        log.info("长轮询任务开启:" + new Date());
        while (StringUtils.isEmpty(data)) {
            synchronized (lock) {
                lock.wait();
            }
        }
        log.info("长轮询任务结束:" + new Date());
        return data;
    }

}
