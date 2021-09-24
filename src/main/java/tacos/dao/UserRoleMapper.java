package tacos.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @author yuanfu
 * @data 2021/5/20 18:41
 */
@Repository
public interface UserRoleMapper {

    @Select("select rid from user_roles where uid = #{uid}")
    int findRidByUid(int uid);

    @Select("insert into user_roles values (#{uid}, #{rid})")
    void add(int uid, int rid);
}
