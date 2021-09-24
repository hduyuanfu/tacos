package tacos.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tacos.domain.User;

/**
 * @author yuanfu
 * @data 2021/5/20 18:39
 */
@Repository
public interface UserMapper {

    @Select("select * from user where username = #{username}")
    User findByName(String username);

    @Insert("insert into user values (null, #{username}, #{password}, #{enabled}, #{email}, #{regtime})")
    int save(User user);

    @Select("select last_insert_id()")
    int lastInsertId();
}
