package com.microservicesexample.inventoryservice.repository;

import com.microservicesexample.inventoryservice.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    @Query("SELECT e FROM Inventory e WHERE e.skuCode = :skuCode AND e.quantity >= :quantity")
    Optional<Inventory> findBySkuCodeAndQuantityMoreOrEqualThanQuantity(
            @Param("skuCode") String skuCode,
            @Param("quantity") int quantity
    );
}
