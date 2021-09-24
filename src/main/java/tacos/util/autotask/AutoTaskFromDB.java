package tacos.util.autotask;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;
import tacos.dao.CronMapper;

/**
 * @author yuanfu
 * @data 2021/5/14 20:19
 */
@Slf4j
// @Component
// @EnableScheduling
public class AutoTaskFromDB implements SchedulingConfigurer {

    private CronMapper cronMapper;

    @Autowired
    public AutoTaskFromDB(CronMapper cronMapper) {
        this.cronMapper = cronMapper;
    }

    /**
     * 根据cron_id从数据库中读cron字符串，然后执行定时任务，但是此处我们先把id硬编码写死了
     * @param scheduledTaskRegistrar
     */
    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addTriggerTask(() -> process(),
                triggerContext -> {
                    String cron = cronMapper.getCron(1);
                    if(cron.isEmpty()) {
                        log.info("cron 为空");
                    }
                    return new CronTrigger(cron).nextExecutionTime(triggerContext);
                });
    }

    private void process() {
        log.info("autoTask fromDB");
    }
}
