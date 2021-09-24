package tacos.service;

/**
 * @author yuanfu
 * @data 2021/5/20 20:41
 */
public interface UserRoleService {

    int findRidByUid(int uid);

    void add(int uid, int rid);
}
