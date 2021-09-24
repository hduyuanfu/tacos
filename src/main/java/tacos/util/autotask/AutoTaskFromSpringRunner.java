package tacos.util.autotask;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 实现ApplicationRunner接口，那这个run方法会在项目启动后执行，但只会执行一次
 *
 * @author yuanfu
 * @data 2021/5/14 20:31
 */
@Slf4j
@Component
public class AutoTaskFromSpringRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        process();
    }

    private void process() {
        log.info("run ApplicationArguments");
    }
}
