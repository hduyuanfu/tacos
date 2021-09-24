package tacos.nacos.impl2;

/**
 * @author yuanfu
 * @date 2021/9/17 16:23
 */
public interface LockLongPollingService {
    String pull();

    String push(String data);
}
