package tacos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tacos.dao.UserRoleMapper;
import tacos.service.UserRoleService;

/**
 * @author yuanfu
 * @data 2021/5/20 20:43
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    private UserRoleMapper userRoleMapper;

    @Autowired
    public UserRoleServiceImpl(UserRoleMapper userRoleMapper) {
        this.userRoleMapper = userRoleMapper;
    }

    @Override
    public int findRidByUid(int uid) {
        return userRoleMapper.findRidByUid(uid);
    }

    @Override
    public void add(int uid, int rid) {
        userRoleMapper.add(uid, rid);
    }
}
