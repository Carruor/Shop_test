package com.adobe.bookstore.Services;

import com.adobe.bookstore.Models.Order;
import com.adobe.bookstore.Repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService  {

    private OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void saveOrder(List <Order> orders) {

           for (Order order: orders) {

                orderRepository.save(order);}

    }
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}