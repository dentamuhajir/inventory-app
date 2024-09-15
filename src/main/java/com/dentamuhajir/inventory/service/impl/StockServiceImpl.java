package com.dentamuhajir.inventory.service.impl;

import com.dentamuhajir.inventory.dto.StockCreateRequestDTO;
import com.dentamuhajir.inventory.dto.StockDetailResponseDTO;
import com.dentamuhajir.inventory.dto.StockListResponseDTO;
import com.dentamuhajir.inventory.dto.StockUpdateRequestDTO;
import com.dentamuhajir.inventory.model.Stock;
import com.dentamuhajir.inventory.repository.StockRepository;
import com.dentamuhajir.inventory.service.StockService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@AllArgsConstructor
@Log4j2
public class StockServiceImpl implements StockService {
    private StockRepository stockRepository;

    @Override
    public Stock createStock(StockCreateRequestDTO dto, MultipartFile image) throws IOException {
        Stock stock = new Stock();
        stock.setItemName(dto.getItemName());
        stock.setStockQuantity(dto.getStockQuantity());
        stock.setSerialNumber(dto.getSerialNumber());
        stock.setAdditionalInfo(dto.getAdditionalInfo());

        stock.setImage("uploads/" + image.getOriginalFilename());
        stock.setCreatedAt(new Date());
        stock.setCreatedBy("Admin");
        stock.setUpdatedAt(new Date());
        stock.setUpdatedBy("Admin");

        return stockRepository.save(stock);
    }

    @Override
    public List<StockListResponseDTO> listStocks() {
        List<Stock> stocks  = stockRepository.findAll();
        List<StockListResponseDTO> dtos = new ArrayList<>();

        for (Stock stock : stocks) {
            StockListResponseDTO dto = new StockListResponseDTO();
            dto.setId(stock.getId());
            dto.setItemName(stock.getItemName());
            dto.setImage(stock.getImage());
            dtos.add(dto);
        }

        return dtos;
    }

    @Override
    public StockDetailResponseDTO detailStock(Long id) {
        Stock stock = stockRepository.findById(id).get();
        StockDetailResponseDTO dto = new StockDetailResponseDTO();
        dto.setId(stock.getId());
        dto.setItemName(stock.getItemName());
        dto.setStockQuantity(stock.getStockQuantity());
        dto.setSerialNumber(stock.getSerialNumber());
        dto.setAdditionalInfo(stock.getAdditionalInfo());
        dto.setImage(stock.getImage());

        return dto;
    }

    @Override
    public Stock updateStock(Long id, StockUpdateRequestDTO dto) {
        Stock existingStock = stockRepository.findById(id).get();
        existingStock.setItemName(dto.getItemName());
        existingStock.setStockQuantity(dto.getStockQuantity());
        existingStock.setSerialNumber(dto.getSerialNumber());
        existingStock.setAdditionalInfo(dto.getAdditionalInfo());
        existingStock.setImage(dto.getImage());
        existingStock.setCreatedAt(new Date());
        existingStock.setCreatedBy("Admin");
        existingStock.setUpdatedAt(new Date());
        existingStock.setUpdatedBy("Admin");

        return stockRepository.save(existingStock);
    }

    @Override
    public void deleteStock(Long id) {
        stockRepository.deleteById(id);
    }
}
