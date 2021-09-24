package tacos.nacos.impl3;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author yuanfu
 * @date 2021/9/17 16:31
 */
public interface NacosLongPollingService {
    void doGet(String dataId, HttpServletRequest req, HttpServletResponse resp);

    void push(String dataId, String data);
}
