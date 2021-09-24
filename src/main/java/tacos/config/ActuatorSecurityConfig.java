package tacos.config;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author yuanfu
 * @data 2021/5/21 13:41
 */
@Configuration
@Order(100)
public class ActuatorSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .requestMatcher(EndpointRequest.toAnyEndpoint().excluding("health", "info"))
            // .authorizeRequests().anyRequest().hasRole("ADMIN")
            .authorizeRequests().anyRequest().hasRole("USER")
            .and().httpBasic();
    }
}
