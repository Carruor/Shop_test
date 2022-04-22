package com.adobe.bookstore.Repositories;

import com.adobe.bookstore.Models.BookStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookStockRepository extends JpaRepository<BookStock, String> {
}
