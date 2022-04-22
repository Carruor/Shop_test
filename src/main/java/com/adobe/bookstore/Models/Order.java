package com.adobe.bookstore.Models;

import com.adobe.bookstore.Exceptions.OrderException;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Orders")
@JsonSerialize
@Component
public class Order {

    @ManyToOne
    @JoinColumn(name = "book_stock")
    private BookStock bookStock = new BookStock();

    @Id
    @Column(name = "OrderId", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @Column(name = "bookName", nullable = false)
    private String bookName;

    @Column(name = "orderQuantity", nullable = false)
    private Integer orderQuantity;

    @Column(name = "availableStock", nullable = false)
    private Integer availableStock;

    @Transient
    private List<Order> orderList = new ArrayList<Order>();


    public Order() {
    }

    public Order(BookStock bookStock, String id, String bookName, Integer orderQuantity, Integer availableStock) {
        this.bookStock = bookStock;
        this.id = id;
        this.bookName = bookStock.getName();
        this.orderQuantity = orderQuantity;
        this.availableStock = bookStock.getQuantity();
        orderList.add(this);

        if (orderQuantity > availableStock) throw new OrderException("Order cannot  be created, stock not available","404");

    }

    public BookStock getBookStock() {
        return bookStock;
    }

    public void setBookStock(BookStock bookStock) {
        this.bookStock = bookStock;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(Integer orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public Integer getAvailableStock() {
        return availableStock;
    }

    public void setAvailableStock(Integer availableStock) {
        this.availableStock = availableStock;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

}
