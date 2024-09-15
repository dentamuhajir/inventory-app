package com.dentamuhajir.inventory.repository;

import com.dentamuhajir.inventory.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {

}
