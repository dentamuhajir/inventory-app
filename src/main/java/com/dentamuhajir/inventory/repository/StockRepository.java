package com.dentamuhajir.inventory.repository;

import com.dentamuhajir.inventory.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, Long> {

    // Using JPQL
    @Query("SELECT s FROM Stock s ORDER BY s.id DESC")
    List<Stock> findStockOrderByDesc();

    @Query(nativeQuery = true, value = "SELECT * FROM stocks ORDER BY :order ASC")
    List<Stock> findStockOrderBy(@Param("order") String order);

}
