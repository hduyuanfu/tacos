package tacos.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * @author yuanfu
 * @data 2021/5/19 16:25
 */
@Slf4j
@Component
@Aspect
@Order(10)
public class CheckNameAspect {

    @Pointcut("execution(* tacos.controller.HelloController.*(..))")
    public void nameLog() {};

    @Before("nameLog()")
    public void doBefore(JoinPoint joinPoint) {
        log.info("校验name是否为ranziyan");
    }
}
