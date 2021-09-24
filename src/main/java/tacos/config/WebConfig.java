package tacos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import tacos.util.filter.MyFilter;
import tacos.util.interceptor.MyInterceptor;
import tacos.util.listener.MyHttpRequestListener;

/**
 * @author yuanfu
 * @data 2021/5/15 21:20
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/login");
    }

    @Bean
    @ConditionalOnMissingBean
    public HttpTraceRepository traceRepository() {
        return new InMemoryHttpTraceRepository();
    }

}
