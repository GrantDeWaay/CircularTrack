package com.example.circularinventory.controller;

import com.example.circularinventory.dto.AddMaterialDto;
import com.example.circularinventory.dto.CreateProductDto;
import com.example.circularinventory.dto.CreateProductTypeDto;
import com.example.circularinventory.dto.ReturnProductDto;
import com.example.circularinventory.model.Materials;
import com.example.circularinventory.model.Product;
import com.example.circularinventory.model.ProductTypeMaterials;
import com.example.circularinventory.service.MaterialsService;
import com.example.circularinventory.service.ProductService;
import com.example.circularinventory.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/supplychain")
public class SupplyChainController {

    private final ProductService productService;
    private final MaterialsService materialService;
    private final ProductTypeService productTypeService;

    @Autowired
    public SupplyChainController(ProductService productService, MaterialsService materialsService, ProductTypeService productTypeService) {
        this.productService = productService;
        this.materialService = materialsService;
        this.productTypeService = productTypeService;
    }

    // Existing endpoint to create a product and update materials
    @PostMapping("/create-product")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductDto createProductDto) {
        Product product = productService.createProduct(createProductDto);
        if(product == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(product);
    }

    @GetMapping("/get-products")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> product = productService.getAllProducts();
        return ResponseEntity.ok(product);
    }

        @PostMapping("/return-product")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Product> returnProduct(@RequestBody ReturnProductDto returnProductDto) {
        Product product = productService.returnProduct(returnProductDto);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/add-material")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Materials> addMaterial(@RequestBody AddMaterialDto addMaterialDto) {
        Materials material = materialService.createNewMaterial(addMaterialDto.name, addMaterialDto.quantity);
        return ResponseEntity.ok(material);
    }

    @PostMapping("/create-product-type")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<ProductTypeMaterials>> createProductType(@RequestBody CreateProductTypeDto createProductTypeDto) {
        List<ProductTypeMaterials> productTypeMaterialsList = productTypeService.createNewProductType(createProductTypeDto);
        return ResponseEntity.ok(productTypeMaterialsList);
    }

    @GetMapping("/get-materials")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<Materials>> getMaterials() {
        List<Materials> productTypeMaterialsList = materialService.getAllMaterials();
        return ResponseEntity.ok(productTypeMaterialsList);
    }
}
