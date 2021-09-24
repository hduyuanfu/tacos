package tacos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tacos.util.interceptor.MyInterceptor;

import java.util.ArrayList;

/**
 * @author yuanfu
 * @data 2021/5/20 15:06
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    private final MyInterceptor myInterceptor;

    @Autowired
    public InterceptorConfig(MyInterceptor myInterceptor) {
        this.myInterceptor = myInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        ArrayList<String> includePathLists = new ArrayList<>();
        ArrayList<String> excludePathLists = new ArrayList<>();
        includePathLists.add("/text/*");
        registry.addInterceptor(myInterceptor).addPathPatterns(includePathLists);
    }
}
