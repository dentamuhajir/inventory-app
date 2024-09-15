package com.dentamuhajir.inventory.controller;

import com.dentamuhajir.inventory.dto.StockCreateRequestDTO;
import com.dentamuhajir.inventory.dto.StockDetailResponseDTO;
import com.dentamuhajir.inventory.dto.StockListResponseDTO;
import com.dentamuhajir.inventory.dto.StockUpdateRequestDTO;
import com.dentamuhajir.inventory.model.Stock;
import com.dentamuhajir.inventory.service.StockService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/stocks")
public class StockController {
    private StockService stockService;

    private static final Logger logger = LogManager.getLogger(StockController.class);

    @PostMapping
    public ResponseEntity<Stock> createStock(
            @RequestParam(value = "itemName", required = false) String itemName,
            @RequestParam(value = "stockQuantity", required = false) String stockQuantity,
            @RequestParam(value= "serialNumber", required = false) String serialNumber,
            @RequestParam(value ="additionalInfo", required = false) String additionalInfo,
            @RequestParam("image") MultipartFile image
    ) {
        StockCreateRequestDTO dto = new StockCreateRequestDTO();
        dto.setItemName(itemName);
        dto.setStockQuantity(Integer.parseInt(stockQuantity));
        dto.setSerialNumber(serialNumber);
        dto.setAdditionalInfo(additionalInfo);
        logger.info("Creating stock with request: {}", dto);

        if (!isValidImage(image)) {
            logger.error("Invalid file type. Only JPG and PNG are allowed.");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        try {
            Stock createdStock = stockService.createStock(dto, image);
            logger.info("Stock created successfully: {}", createdStock);
            return new ResponseEntity<>(createdStock, HttpStatus.CREATED);
        } catch (Exception e) {
            logger.error("Failed to created stock :", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    private boolean isValidImage(MultipartFile file) {
        String contentType = file.getContentType();
        return Arrays.asList("image/jpeg", "image/png").contains(contentType);
    }

    @GetMapping
    public ResponseEntity<List<StockListResponseDTO>> findAllStock() {
        logger.info("Get listing stock request ");
        try {
            List<StockListResponseDTO> stockList = stockService.listStocks();
            logger.info("Get listing stock response: {} ", stockList);
            return new ResponseEntity<>(stockList, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Failed to get list stock :", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Stock> updateStock(@PathVariable Long id, @RequestBody StockUpdateRequestDTO dto) {
        logger.info("Updating stock with ID: {} and request: {}", id, dto);
        try {
            Stock updatedStock = stockService.updateStock(id, dto);
            if (updatedStock != null) {
                logger.info("Stock updated successfully: {}", updatedStock);
                return ResponseEntity.ok(updatedStock);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            logger.error("Failed to update stock with ID: {}", id, e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity<StockDetailResponseDTO> detailStock(@PathVariable Long id) {
        logger.info("Get detail stock with ID: {} ", id);
        try {
            StockDetailResponseDTO detailStock = stockService.detailStock(id);
            logger.info("Get detail stock response: {} ", detailStock);
            return new ResponseEntity<>(detailStock, HttpStatus.OK);
        } catch (Exception e){
            logger.error("Failed to get detail stock with ID: {}", id, e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStock(@PathVariable Long id) {
        logger.info("Delete stock with ID: {} ", id);
        try {
            stockService.deleteStock(id);
            logger.info("deleted stock successfully ", id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            logger.error("Failed to delete stock with ID: {}", id, e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
