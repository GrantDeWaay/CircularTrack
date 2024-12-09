package com.example.circularinventory.service;

import com.example.circularinventory.dto.CreateProductDto;
import com.example.circularinventory.dto.ReturnProductDto;
import com.example.circularinventory.model.Materials;
import com.example.circularinventory.model.Product;
import com.example.circularinventory.model.ProductTypeMaterials;
import com.example.circularinventory.model.ProductTypes;
import com.example.circularinventory.repository.MaterialsRepository;
import com.example.circularinventory.repository.ProductRepository;
import com.example.circularinventory.repository.ProductTypesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class ProductService {

    private final ProductTypeService productTypeService;
    private final ProductTypesRepository productTypesRepository;
    private final ProductRepository productRepository;
    private final MaterialsRepository materialsRepository;

    @Autowired
    public ProductService(ProductTypeService productTypeService, ProductTypesRepository productTypesRepository,
                          ProductRepository productRepository, MaterialsRepository materialsRepository) {
        this.productTypeService = productTypeService;
        this.productTypesRepository = productTypesRepository;
        this.productRepository = productRepository;
        this.materialsRepository = materialsRepository;
    }


    public Product createProduct(CreateProductDto createProductDto) {
        if(!productTypeService.isValidProductTypeId(createProductDto.productTypeId)) {
            return null;
        }
        Product product = new Product();
        product.setPickupTimestamp(new Date().toInstant());
        ProductTypes productTypes = productTypesRepository.getReferenceById(createProductDto.productTypeId);
        Set<ProductTypeMaterials> productTypeMaterials = productTypes.getProductTypeMaterialsSet();
        for(ProductTypeMaterials productTypeMaterial: productTypeMaterials) {
            Integer amountToSubtract = productTypeMaterial.getAmount();
            Materials material = productTypeMaterial.getMaterial_id();
            material.setAmount(material.getAmount()- amountToSubtract);
            materialsRepository.save(material);
        }
        product.setProductType(productTypes);

        return productRepository.save(product);
    }

    public Product returnProduct(ReturnProductDto returnProductDto) {
        Product product = productRepository.getReferenceById(returnProductDto.productId);
        product.setReturnTimestamp(new Date().toInstant());
        ProductTypes productTypes = productTypesRepository.getReferenceById(product.getProductType().getId());
        Set<ProductTypeMaterials> productTypeMaterials = productTypes.getProductTypeMaterialsSet();
        for(ProductTypeMaterials productTypeMaterial: productTypeMaterials) {
            double amountToAdd = productTypeMaterial.getAmount();
            amountToAdd = amountToAdd/productTypeMaterial.getRetrievable_percent();
            Materials material = productTypeMaterial.getMaterial_id();
            material.setAmount(material.getAmount()+ (Integer)((int) amountToAdd));
            materialsRepository.save(material);
        }

        return productRepository.save(product);
    }

    public List<Product> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products;
    }
}
