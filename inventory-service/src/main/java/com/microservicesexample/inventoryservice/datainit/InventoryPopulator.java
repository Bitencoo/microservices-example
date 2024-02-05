package com.microservicesexample.inventoryservice.datainit;

import com.microservicesexample.inventoryservice.entity.Inventory;
import com.microservicesexample.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InventoryPopulator implements CommandLineRunner {
    private final InventoryRepository inventoryRepository;

    @Override
    public void run(String... args) throws Exception {
        Inventory inventory_1 = Inventory
                .builder()
                .skuCode("00001")
                .quantity(10)
                .build();

        Inventory inventory_2 = Inventory
                .builder()
                .skuCode("00002")
                .quantity(1)
                .build();
        inventoryRepository.save(inventory_1);
        inventoryRepository.save(inventory_2);
    }
}
