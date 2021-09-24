package tacos.service;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import tacos.domain.Malatang;
import tacos.domain.Order;

import java.util.Date;
import java.util.List;

/**
 * @author yuanfu
 * @data 2021/5/13 15:21
 */
public interface OrderService {

    List<Order> findAllOrders();

    Order findOrderById(long id);

    List<Order> findOrdersByName(String name);

    List<Order> findOrdersByPhone(String phone);

    List<Order> findOrdersByAddress(String address);

    List<Order> findOrdersByDatetimeBetween(Date startTime, Date endTime);

    int addOrder(Order order, Malatang malatang);

    int deleteOrderById(long id);

}
