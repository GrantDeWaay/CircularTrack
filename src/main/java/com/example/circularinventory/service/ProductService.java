package com.example.circularinventory.service;

import com.example.circularinventory.dto.CreateProductDto;
import com.example.circularinventory.dto.ReturnProductDto;
import com.example.circularinventory.model.Material;
import com.example.circularinventory.model.Product;
import com.example.circularinventory.model.ProductTypeMaterial;
import com.example.circularinventory.model.ProductType;
import com.example.circularinventory.repository.MaterialRepository;
import com.example.circularinventory.repository.ProductRepository;
import com.example.circularinventory.repository.ProductTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class ProductService {

    private final ProductTypeService productTypeService;
    private final ProductTypeRepository productTypeRepository;
    private final ProductRepository productRepository;
    private final MaterialRepository materialRepository;

    @Autowired
    public ProductService(ProductTypeService productTypeService, ProductTypeRepository productTypeRepository,
                          ProductRepository productRepository, MaterialRepository materialRepository) {
        this.productTypeService = productTypeService;
        this.productTypeRepository = productTypeRepository;
        this.productRepository = productRepository;
        this.materialRepository = materialRepository;
    }


    public Product createProduct(CreateProductDto createProductDto) {
        if(!productTypeService.isValidProductTypeId(createProductDto.productTypeId)) {
            return null;
        }
        Product product = new Product();
        product.setPickupTimestamp(new Date().toInstant());
        ProductType productType = productTypeRepository.getReferenceById(createProductDto.productTypeId);
        Set<ProductTypeMaterial> productTypeMaterials = productType.getProductTypeMaterialsSet();
        for(ProductTypeMaterial productTypeMaterial: productTypeMaterials) {
            Integer amountToSubtract = productTypeMaterial.getAmount();
            Material material = productTypeMaterial.getMaterialId();
            material.setAmount(material.getAmount()- amountToSubtract);
            materialRepository.save(material);
        }
        product.setProductType(productType);

        return productRepository.save(product);
    }

    public Product returnProduct(ReturnProductDto returnProductDto) {
        Product product = productRepository.getReferenceById(returnProductDto.productId);
        product.setReturnTimestamp(new Date().toInstant());
        ProductType productType = productTypeRepository.getReferenceById(product.getProductType().getId());
        Set<ProductTypeMaterial> productTypeMaterials = productType.getProductTypeMaterialsSet();
        for(ProductTypeMaterial productTypeMaterial: productTypeMaterials) {
            double amountToAdd = productTypeMaterial.getAmount();
            amountToAdd = amountToAdd/productTypeMaterial.getRetrievablePercent();
            Material material = productTypeMaterial.getMaterialId();
            material.setAmount(material.getAmount()+ (Integer)((int) amountToAdd));
            materialRepository.save(material);
        }

        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
