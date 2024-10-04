package com.dentamuhajir.inventory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name="stock_transactions")
public class StockTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stock_transactions_seq")
    private long id;

    @ManyToOne
    @JoinColumn(name = "stock_id", nullable = false)
    @JsonIgnore // Prevent serialization of the stock reference
    private Stock stock;

    @Column(name = "transaction_type", nullable = false)
    private String transactionType; // e.g., "IN" or "OUT"

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "transaction_date", nullable = false)
    private Date transactionDate;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "updated_by")
    private String updatedBy;
}
