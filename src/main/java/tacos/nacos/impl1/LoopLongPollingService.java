package tacos.nacos.impl1;

/**
 * @author yuanfu
 * @date 2021/9/17 14:43
 */
public interface LoopLongPollingService {
    String pull();

    String push(String data);
}
