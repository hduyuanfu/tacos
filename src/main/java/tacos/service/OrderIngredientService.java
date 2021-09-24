package tacos.service;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author yuanfu
 * @data 2021/5/13 21:07
 */
public interface OrderIngredientService {

    List<Long> findIngredientsIdByOrderId(long orderId);

    int deleteIngredientsIdByOrderId(long orderId);

    int addOrderIdAndIngredientId(long orderId, long ingredientId);
}
