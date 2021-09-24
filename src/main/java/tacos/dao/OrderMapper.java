package tacos.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tacos.domain.Ingredient;
import tacos.domain.Order;

import java.util.Date;
import java.util.List;

/**
 * @author yuanfu
 * @data 2021/5/12 19:31
 */
@Repository
public interface OrderMapper {

    @Select("select * from Morder")
    List<Order> findAll();

    @Select("select * from Morder where id = #{id}")
    Order findById(long id);

    @Select("select * from Morder where name = #{name}")
    List<Order> findByName(String name);

    @Select("select * from Morder where phone = #{phone}")
    List<Order> findByPhone(String phone);

    @Select("select * from Morder where address = #{address}")
    List<Order> findByAddress(String address);

    @Select("select * from Morder where datetime between #{startTime} and #{endTime}")
    List<Order> findByDatetimeBetween(Date startTime, Date endTime);

    @Insert("insert into Morder values (null, #{name}, #{phone}, #{address}, #{create_at})")
    int add(Order order);

    @Delete("delete from Morder where id = #{id}")
    int deleteById(long id);

    @Select("select last_insert_id()")
    long lastInsertId();
}
