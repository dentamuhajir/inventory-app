package com.dentamuhajir.inventory.repository;

import com.dentamuhajir.inventory.model.StockTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockTransactionRepository extends JpaRepository<StockTransaction,Long> {
}
