package com.microservicesexample.inventoryservice.service;

import com.microservicesexample.inventoryservice.entity.Inventory;
import com.microservicesexample.inventoryservice.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class InventoryServiceImpl implements InventoryService{
    private final InventoryRepository inventoryRepository;
    @Override
    public boolean findBySkuCode(String skuCode, int quantity) {
        return inventoryRepository.findBySkuCodeAndQuantityMoreOrEqualThanQuantity(skuCode, quantity).isPresent();
    }
}
