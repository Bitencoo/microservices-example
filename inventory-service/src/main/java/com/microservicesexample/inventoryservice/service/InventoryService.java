package com.microservicesexample.inventoryservice.service;

import com.microservicesexample.inventoryservice.entity.Inventory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface InventoryService {
    boolean findBySkuCode(String skuCode, int quantity);
}
