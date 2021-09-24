package tacos.nacos.impl1;

import lombok.Data;

import java.util.concurrent.Callable;

/**
 * @author yuanfu
 * @date 2021/9/17 14:54
 */
@Data
public class LoopPushTask implements Callable<String> {
    private LoopPullTask loopPullTask;
    private String data;

    public LoopPushTask (LoopPullTask loopPullTask, String data) {
        this.loopPullTask = loopPullTask;
        this.data = data;
    }

    @Override
    public String call() throws Exception {
        loopPullTask.setData(data);
        return "changed";
    }
}
