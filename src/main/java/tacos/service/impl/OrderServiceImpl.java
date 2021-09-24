package tacos.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;
import tacos.dao.OrderIngredientMapper;
import tacos.dao.OrderMapper;
import tacos.domain.Ingredient;
import tacos.domain.Malatang;
import tacos.domain.Order;
import tacos.service.OrderService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author yuanfu
 * @data 2021/5/13 15:22
 */
@Service
public class OrderServiceImpl implements OrderService {

    private OrderMapper orderMapper;

    private OrderIngredientMapper orderIngredientMapper;

    @Autowired
    public OrderServiceImpl (OrderMapper orderMapper,
                             OrderIngredientMapper orderIngredientMapper) {
        this.orderMapper = orderMapper;
        this.orderIngredientMapper = orderIngredientMapper;
    }

    @Override
    public List<Order> findAllOrders() {
        return orderMapper.findAll();
    }

    @Override
    public Order findOrderById(long id) {
        return orderMapper.findById(id);
    }

    @Override
    public List<Order> findOrdersByName(String name) {
        return orderMapper.findByName(name);
    }

    @Override
    public List<Order> findOrdersByPhone(String phone) {
        return orderMapper.findByPhone(phone);
    }

    @Override
    public List<Order> findOrdersByAddress(String address) {
        return orderMapper.findByAddress(address);
    }

    @Override
    public List<Order> findOrdersByDatetimeBetween(Date startTime, Date endTime) {
        return orderMapper.findByDatetimeBetween(startTime, endTime);
    }

    @Override
    public int addOrder(Order order, Malatang malatang) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String datetime = simpleDateFormat.format(new Date());
        order.setCreate_at(datetime);
        int add = orderMapper.add(order);

        long orderId = orderMapper.lastInsertId();
        List<Ingredient> ingredients = malatang.getIngredients();
        for(Ingredient ingredient : ingredients) {
            long ingredientId = ingredient.getId();
            orderIngredientMapper.add(orderId, ingredientId);
        }

        return add;
    }

    @Override
    public int deleteOrderById(long id) {
        return orderMapper.deleteById(id);
    }

}
