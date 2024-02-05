package com.microservicesexample.inventoryservice.controller;

import com.microservicesexample.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;
    @GetMapping("/{skuCode}")
    public ResponseEntity<Boolean> isInStock(@PathVariable("skuCode") String skuCode, @RequestParam(required = true) int quantity) {
        return new ResponseEntity<Boolean>(inventoryService.findBySkuCode(skuCode, quantity), HttpStatus.OK);
    }
}
