package tacos.nacos.impl1;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.concurrent.Callable;

/**
 * @author yuanfu
 * @date 2021/9/17 14:45
 */
@Slf4j
@Data
public class LoopPullTask implements Callable<String> {

    public volatile String data;
    private Long TIME_OUT_MILLIS = 10000L;


    @Override
    public String call() throws Exception {
        long startTime = System.currentTimeMillis();
        while(true) {
            if(!StringUtils.isEmpty(data)) {
                return data;
            }
            if(isTimeOut(startTime)) {
                log.info("获取数据请求超时" + new Date());
                data = "请求超时";
                return data;
            }
            // 减轻cpu压力
            Thread.sleep(200);
        }
    }

    private boolean isTimeOut(long startTime) {
        long nowTime = System.currentTimeMillis();
        return nowTime - startTime > TIME_OUT_MILLIS;
    }
}
