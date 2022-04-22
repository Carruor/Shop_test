package com.adobe.bookstore.Controllers;

import com.adobe.bookstore.Models.Order;
import com.adobe.bookstore.Services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders/")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping
    public String saveOrder(@RequestBody Order order) {
        var orderID = order.getId();
        this.orderService.saveOrder(order.getOrderList());
        return orderID;
    }

    @GetMapping
    public List<Order> getAllOrders() {return orderService.getAllOrders();}
}
