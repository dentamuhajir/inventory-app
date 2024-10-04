package com.dentamuhajir.inventory.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="stocks")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stocks_seq")
    private long id;

    @Column(name = "item_name", nullable = false)
    private String itemName;

    @Column(name = "stock_quantity")
    private Integer stockQuantity;

    @Column(name = "serial_number")
    private String serialNumber;

    @Column(name = "additional_info", columnDefinition = "JSON")
    private String additionalInfo;

    @Column(name = "image")
    private String image;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "updated_by")
    private String updatedBy;


    @OneToMany(mappedBy = "stock")
    private List<StockTransaction> transactions;

//    @OneToMany(mappedBy = "stock", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<StockTransaction> transactions;
//
//    @Override
//    public String toString() {
//        return "Stock{" +
//                "id=" + id +
//                ", itemName='" + itemName + '\'' +
//                ", stockQuantity=" + stockQuantity +
//                ", serialNumber='" + serialNumber + '\'' +
//                ", additionalInfo='" + additionalInfo + '\'' +
//                ", image='" + image + '\'' +
//                ", createdAt=" + createdAt +
//                ", createdBy='" + createdBy + '\'' +
//                ", updatedAt=" + updatedAt +
//                ", updatedBy='" + updatedBy + '\'' +
//                ", transactions=" + transactions + // You might want to customize this too
//                '}';
//    }

}
