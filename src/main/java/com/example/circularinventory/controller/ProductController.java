package com.example.circularinventory.controller;

import com.example.circularinventory.dto.CreateProductDto;
import com.example.circularinventory.dto.ReturnProductDto;
import com.example.circularinventory.model.Product;
import com.example.circularinventory.service.MaterialsService;
import com.example.circularinventory.service.ProductService;
import com.example.circularinventory.service.ProductTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {


    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/get-products")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<List<Product>> getProducts() {
        List<Product> product = productService.getAllProducts();
        return ResponseEntity.ok(product);
    }

    @PostMapping("/create-product")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Product> createProduct(@RequestBody CreateProductDto createProductDto) {
        Product product = productService.createProduct(createProductDto);
        if(product == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(product);
    }

    @PostMapping("/return-product")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Product> returnProduct(@RequestBody ReturnProductDto returnProductDto) {
        Product product = productService.returnProduct(returnProductDto);
        return ResponseEntity.ok(product);
    }
}
