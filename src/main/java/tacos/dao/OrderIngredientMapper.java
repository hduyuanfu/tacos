package tacos.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yuanfu
 * @data 2021/5/13 11:57
 */
@Repository
public interface OrderIngredientMapper {

    @Select("select ingredientId from Morder_Ingredients where orderId = #{orderId}")
    List<Long> findIngredientIdByOrderId(long orderId);

    @Delete("delete from Morder_Ingredients where orderId = #{orderId}")
    int delete(long orderId);

    @Insert("insert into Morder_Ingredients values (#{orderId}, #{ingredientId})")
    int add(long orderId, long ingredientId);
}
