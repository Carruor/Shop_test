package com.adobe.bookstore.Models;

import com.adobe.bookstore.Exceptions.OrderException;
import org.junit.jupiter.api.Test;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void orderCanCreateAValidOrder() throws Exception {
        BookStock bookStock = new BookStock("abcd2020","Dune",3);
        Order order = new Order(bookStock,"1234abc","Dune",2,3);


        assertTrue(order instanceof Order);
        assertEquals(3,bookStock.getQuantity());
        assertEquals(3, order.getAvailableStock());
        assertEquals("Dune", order.getBookName());

    }

    @Test
    void invalidOrderThrowsException() throws Exception {

        BookStock bookStock = new BookStock();

        OrderException thrown = assertThrows(OrderException.class, () -> new Order(
                bookStock,
                "1234abc",
                "Dune",
                4,
                3));


        assertEquals("Order cannot  be created, stock not available", thrown.getMessage());
        assertEquals("404", thrown.getCode());
    }

    @Test
    void validOrdersAreAddedToList(){
        BookStock bookStock = new BookStock("abcd2020","Dune",3);

        Order order = new Order(bookStock,"1234abc","Dune",2,3);

        var validOrderList = order.getOrderList();

        order.setOrderList(validOrderList);

        assertThat(order.getOrderList().size()).isEqualTo(1);
        assertThat(order.getOrderList().get(0).getOrderQuantity()).isEqualTo(2);
        assertThat(order.getOrderList().get(0).getAvailableStock()).isEqualTo(3);

    }




}