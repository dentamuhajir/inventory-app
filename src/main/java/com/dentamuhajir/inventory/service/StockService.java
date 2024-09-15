package com.dentamuhajir.inventory.service;

import com.dentamuhajir.inventory.dto.StockCreateRequestDTO;
import com.dentamuhajir.inventory.dto.StockDetailResponseDTO;
import com.dentamuhajir.inventory.dto.StockListResponseDTO;
import com.dentamuhajir.inventory.dto.StockUpdateRequestDTO;
import com.dentamuhajir.inventory.model.Stock;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StockService {
    Stock createStock(StockCreateRequestDTO dto, MultipartFile image) throws IOException;
    List<StockListResponseDTO> listStocks();
    StockDetailResponseDTO detailStock(Long id);
    Stock updateStock(Long id, StockUpdateRequestDTO dto);
    void deleteStock(Long id);
}
