package com.adobe.bookstore.Services;

import com.adobe.bookstore.Models.BookStock;
import com.adobe.bookstore.Models.Order;
import com.adobe.bookstore.Repositories.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;



@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderServiceTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderService orderService;

    @Test
    @Sql(statements = "INSERT INTO book_stock (id, name, quantity) VALUES ('abcd2020', 'Dune', 3)")
    @Sql(statements = "INSERT INTO book_stock (id, name, quantity) VALUES ('2020abcd', 'Harry Potter', 5)")
    void serviceCanSaveAnOrderList() {

        BookStock bookStock = new BookStock("abcd2020","Dune",3);
        BookStock bookStock1 = new BookStock("2020abcd","Harry Potter",5);

        Order order0 = new Order(bookStock,"1234abc","Dune",2,3);
        Order order1 = new Order(bookStock1,"01234abc","Harry Potter",1,5);

        var validatedOrderList= order0.getOrderList();

        order0.setOrderList(validatedOrderList);

        order1.setOrderList(validatedOrderList);

        orderService.saveOrder(validatedOrderList);

        List<Order> savedOrders = orderRepository.saveAll(validatedOrderList);

        assertThat(validatedOrderList).isNotNull();
        assertThat(savedOrders).isNotNull();
        assertThat(savedOrders.size()).isEqualTo(validatedOrderList.size());
        assertThat("Dune").isEqualTo(savedOrders.get(0).getBookName());
        assertThat("Dune").isEqualTo(validatedOrderList.get(0).getBookName());


    }

    @Test
    @Sql(statements = "INSERT INTO book_stock (id, name, quantity) VALUES ('1234asd', 'Game of Thrones', 3)")
    void saveSingleOrderAndRetrieveItWhenCallingGetAll(){

        BookStock bookStock = new BookStock("1234asd","Game of Thrones",3);

        Order order = new Order(bookStock,"12345abc","Game of Thrones",2,3);

        var singleBookOrderList = order.getOrderList();

        order.setOrderList(singleBookOrderList);

        orderService.saveOrder(singleBookOrderList);

        List<Order> savedOrder = orderRepository.saveAll(singleBookOrderList);

       var allOrders = orderService.getAllOrders();

        assertThat(savedOrder.size()).isEqualTo(singleBookOrderList.size());
        assertThat("Game of Thrones").isEqualTo(allOrders.get(0).getBookName());

    }


}