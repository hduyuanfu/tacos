package tacos.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import tacos.domain.Ingredient;
import tacos.domain.Malatang;
import tacos.domain.User;
import tacos.service.OrderService;
import tacos.domain.Order;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * @author yuanfu
 * @data 2021/5/12 19:03
 */
@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes(value={"malatang", "order"})
// "malatang"如果不出现在上面，说明这个controller不共享"malatang这个对象"
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController (OrderService orderService) {
        this.orderService = orderService;
    }

    @ModelAttribute(name="order")
    public Order order() {
        return new Order();
    }

    @GetMapping("/current")
    public String orderForm() {
        // model.addAttribute("order", new Order()); 这句话和上面那个order()方法等效
        return "orderForm";
    }

    @PostMapping("/processOrder")
    public String processOrder(@Valid /*@ModelAttribute("order")*/ Order order,
                               Errors errors,
                               SessionStatus sessionStatus,
                               // @AuthenticationPrincipal User user, //并没有什么用
                               @ModelAttribute("malatang") Malatang malatang) {
        if(errors.hasErrors()) {
            return "orderForm";
        }
        // order.setName(user.getUsername());
        int add = orderService.addOrder(order, malatang);

        if(add == 1) {
            log.info("订单保存成功");
        }

        sessionStatus.setComplete();
        return "redirect:/design";
    }

}
