package tacos.nacos.impl2;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.concurrent.Callable;

/**
 * @author yuanfu
 * @date 2021/9/17 16:24
 */
@Slf4j
public class LockPushTask implements Callable<String> {
    private LockPullTask lockPullTask;
    private String data;
    private Object lock;

    public LockPushTask(LockPullTask lockPullTask, String data, Object lock) {
        this.lockPullTask = lockPullTask;
        this.data = data;
        this.lock = lock;
    }

    @Override
    public String call() throws Exception {
        log.info("数据发生变更:" + new Date());
        synchronized (lock) {
            lockPullTask.setData(data);
            lock.notifyAll();
            log.info("数据变更为:" + data);
        }
        return "changed";
    }
}
