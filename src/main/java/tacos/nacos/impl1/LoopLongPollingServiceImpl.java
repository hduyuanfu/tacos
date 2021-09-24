package tacos.nacos.impl1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

/**
 * @author yuanfu
 * @date 2021/9/17 14:44
 */
@Service
public class LoopLongPollingServiceImpl implements LoopLongPollingService {
    @Autowired
    ScheduledExecutorService scheduler;
    private LoopPullTask loopPullTask;

    @Override
    public String pull() {
        loopPullTask = new LoopPullTask();
        Future<String> result = scheduler.schedule(loopPullTask, 0L, TimeUnit.MILLISECONDS);
        try {
            return result.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return "ex";
    }

    @Override
    public String push(String data) {
        Future<String> future = scheduler.schedule(new LoopPushTask(loopPullTask, data), 0L, TimeUnit.MILLISECONDS);
        try {
            return future.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return "ex";
    }
}
