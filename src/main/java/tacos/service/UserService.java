package tacos.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import tacos.domain.User;

/**
 * @author yuanfu
 * @data 2021/5/20 18:45
 */
public interface UserService extends UserDetailsService {

    User findUserByName(String name);

    int saveUser(User user);

    int lastInsertId();
}
