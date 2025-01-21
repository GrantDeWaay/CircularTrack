package com.example.circularinventory.controller;

import com.example.circularinventory.dto.CreateProductTypeDto;
import com.example.circularinventory.dto.GetProductTypeByIdDto;
import com.example.circularinventory.dto.ProductTypeResponseDto;
import com.example.circularinventory.dto.UpdateProductTypeDto;
import com.example.circularinventory.model.ProductTypeMaterial;
import com.example.circularinventory.model.ProductType;
import com.example.circularinventory.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/productType")
public class ProductTypeController {

    private final ProductTypeService productTypeService;

    @Autowired
    public ProductTypeController(ProductTypeService productTypeService) {
        this.productTypeService = productTypeService;
    }

    @PostMapping("/create-product-type")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<ProductTypeMaterial>> createProductType(@RequestBody CreateProductTypeDto createProductTypeDto) {
        List<ProductTypeMaterial> productTypeMaterialList = productTypeService.createNewProductType(createProductTypeDto);
        return ResponseEntity.ok(productTypeMaterialList);
    }

    @GetMapping("/get-product-types")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<ProductTypeResponseDto>> getProductTypes() {
        List<ProductTypeResponseDto> productTypes = productTypeService.getProductTypes();
        return ResponseEntity.ok(productTypes);
    }

    @PutMapping("/update-product-types")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<ProductTypeMaterial>> updateProductType(@RequestBody UpdateProductTypeDto updateProductTypeDto) {
        List<ProductTypeMaterial> productTypeMaterialList = productTypeService.updateProductType(updateProductTypeDto);
        return ResponseEntity.ok(productTypeMaterialList);
    }

    @PostMapping("/get-product-type-by-id")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<ProductTypeResponseDto> getProductTypeById(@RequestBody GetProductTypeByIdDto getProductTypeByIdDto) {
        ProductTypeResponseDto productType = productTypeService.getProductTypeById(getProductTypeByIdDto);
        return ResponseEntity.ok(productType);
    }
}
