package tacos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tacos.dao.RoleMapper;
import tacos.dao.UserMapper;
import tacos.dao.UserRoleMapper;
import tacos.domain.Role;
import tacos.domain.User;
import tacos.service.UserService;

import java.util.List;

/**
 * @author yuanfu
 * @data 2021/5/20 18:46
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    private final RoleMapper roleMapper;

    private final UserRoleMapper userRoleMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper,
                           RoleMapper roleMapper,
                           UserRoleMapper userRoleMapper) {
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
        this.userRoleMapper = userRoleMapper;
    }

    @Override
    public User findUserByName(String name) {
        return userMapper.findByName(name);
    }

    @Override
    public int saveUser(User user) {
        int add = userMapper.save(user);
        return add;
    }

    @Override
    public int lastInsertId() {
        return userMapper.lastInsertId();
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.findByName(s);
        if(user == null) {
            // 避免返回null,这里返回一个不含有任何值的User对象，在后期的密码比对过程中一样会验证失败
            // return new User();
            // 或者下面这样也行
            throw new UsernameNotFoundException("user" + s + "not found");
        }
        int rid = userRoleMapper.findRidByUid(user.getId());
        Role role = roleMapper.findById(rid);
        user.setRole(role);
        return user;
    }
}
