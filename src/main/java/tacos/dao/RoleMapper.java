package tacos.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tacos.domain.Role;

import java.util.List;

/**
 * @author yuanfu
 * @data 2021/5/20 16:58
 */
@Repository
public interface RoleMapper {

    @Select("select * from roles where id = #{id}")
    Role findById(int id);
}
