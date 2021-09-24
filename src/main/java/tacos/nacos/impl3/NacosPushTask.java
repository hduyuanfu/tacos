package tacos.nacos.impl3;

import java.util.Iterator;
import java.util.Queue;

/**
 * @author yuanfu
 * @date 2021/9/17 18:03
 */
public class NacosPushTask implements Runnable{
    private String dataId;
    private String data;
    private Queue<NacosPullTask> nacosPullTasks;

    public NacosPushTask(String dataId, String data,
                         Queue<NacosPullTask> nacosPullTasks) {
        this.dataId = dataId;
        this.data = data;
        this.nacosPullTasks = nacosPullTasks;
    }

    @Override
    public void run() {
        Iterator<NacosPullTask> iterator = nacosPullTasks.iterator();
        while(iterator.hasNext()){
            NacosPullTask nacosPullTask = iterator.next();
            if(dataId.equals(nacosPullTask.dataId)) {
              // 可根据内容的md5判断数据是否发生变化，此处为了演示就不写了
              // 移除队列中的任务，确保下次请求时响应的task不是上次请求留在队列中的task
              iterator.remove();
              nacosPullTask.sendResponse(data);
              break;
            }
        }
    }
}
