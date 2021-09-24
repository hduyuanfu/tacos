package tacos.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
/**
 * 切入点表达式定义格式：execution([可见性] 返回类型 [声明类型].方法名(参数) [异常])
 * 其中[]中的为可选，其他的还支持通配符的使用。
 * *: 匹配所有字符
 * ..: 一般用于匹配多个包，多个参数
 * 例如：@Pointcut("execution(public * tacos.controller..*.*(..))")表示公开类型，返回值类型任意，tacos.controller包下的类以及每个子包
 * 下的所有类，任意方法，方法参数任意
 */

/**
 * aop: 对谁做，何时做，做什么。
 * 定义切入点表达式就是对谁做；
 * execution最常用，代表运行时，但是还有其他关键字，代表何时做；
 * 下面代码中的@Before("webLog()")，@AfterReturning(pointcut = "webLog()", returning = "ret")就是做什么
 */

/**
 * @author yuanfu
 * @data 2021/5/19 15:54
 */
@Slf4j
@Component
@Aspect
@Order(5)
public class WebLogAspect {

    ThreadLocal<Long> startTime =  new ThreadLocal<>();

    @Pointcut("execution(* tacos.controller.HelloController.*(..))")
    public void webLog() {};

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 记录处理开始处理时间
        startTime.set(System.currentTimeMillis());

        // 接受到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        log.info("URL : " + request.getRequestURL().toString());
        log.info("HTTP_METHOD : " + request.getMethod());
        log.info("IP : " + request.getRemoteAddr());
        log.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        log.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        log.info("======");
    }

    @AfterReturning(pointcut = "webLog()", returning = "ret")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        log.info("======");
        log.info("RESPONSE : " + ret);

        // 记录处理用了多长时间
        log.info("TIME : " + (System.currentTimeMillis() - startTime.get()));
    }
}
