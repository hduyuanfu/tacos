package tacos.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import tacos.service.UserService;

/**
 * @author yuanfu
 * @data 2021/5/20 15:48
 */
@Configuration
// @EnableWebSecurity
@Order(101)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserService userService;

    @Autowired
    public SecurityConfig(UserService userService) {
        this.userService = userService;
    }

    /**
     * 这里在内存中配置了用户后，application.properties中的配置就会失效
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // auth.inMemoryAuthentication()
        //         .passwordEncoder(passwordEncoder())
        //         .withUser("renziyan").password("123456").roles("admin")
        //         .and()
        //         .withUser("yuanfu").password("234567").roles("USER");

        auth.userDetailsService(userService)
                .passwordEncoder(passwordEncoder());
    }

    /**
     * 在下面的.hasRole()方法中 设置路路径访问对应的权限为ADMIN，那么在数据库给该用户名设置的权限应该为:ROLE_ADMIN；
     * 权限设置为USER，数据库中应该为ROLE_USER。
     * 而且要注意在.hasRole()中我们不能写ROLE_,因为会自动帮我们插入，自己写反而画蛇添足，直接报bug
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/design", "/orders", "/mail").hasRole("USER")
                // .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/", "/**").permitAll()
            .and()
                .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/design", true)
            .and()
                .logout()
                .logoutSuccessUrl("/login");

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
