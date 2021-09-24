package tacos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tacos.dao.OrderIngredientMapper;
import tacos.service.OrderIngredientService;

import java.util.List;

/**
 * @author yuanfu
 * @data 2021/5/13 21:15
 */
@Service
public class OrderIngredientServiceImpl implements OrderIngredientService {

    private OrderIngredientMapper orderIngredientMapper;

    @Autowired
    public OrderIngredientServiceImpl(OrderIngredientMapper orderIngredientMapper) {
        this.orderIngredientMapper = orderIngredientMapper;
    }

    @Override
    public List<Long> findIngredientsIdByOrderId(long orderId) {
        return orderIngredientMapper.findIngredientIdByOrderId(orderId);
    }

    @Override
    public int deleteIngredientsIdByOrderId(long orderId) {
        return orderIngredientMapper.delete(orderId);
    }

    @Override
    public int addOrderIdAndIngredientId(long orderId, long ingredientId) {
        return orderIngredientMapper.add(orderId, ingredientId);
    }
}
