package com.example.circularinventory.service;

import com.example.circularinventory.dto.CreateProductTypeDto;
import com.example.circularinventory.dto.ProductMaterial;
import com.example.circularinventory.model.*;
import com.example.circularinventory.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductTypeService {

    private final ProductRepository productRepository;
    private final ProductTypesRepository productTypesRepository;
    private final MaterialsRepository materialsRepository;
    private final ProductTypeMaterialsRepository productTypeMaterialsRepository;


    @Autowired
    public ProductTypeService(ProductTypesRepository productTypesRepository, ProductRepository productRepository, MaterialsRepository materialsRepository, ProductTypeMaterialsRepository productTypeMaterialsRepository) {
        this.productTypesRepository = productTypesRepository;
        this.productRepository = productRepository;
        this.materialsRepository = materialsRepository;
        this.productTypeMaterialsRepository = productTypeMaterialsRepository;

    }

    @Transactional
    public List<ProductTypeMaterials> createNewProductType(CreateProductTypeDto createProductTypeDto){
        List<ProductMaterial> productMaterialList = createProductTypeDto.productMaterials;
        List<ProductTypeMaterials> productTypeMaterialsList = new ArrayList<>();
        ProductTypes productType = new ProductTypes();
        productType.setName(createProductTypeDto.name);
        ProductTypes newProduct = productTypesRepository.save(productType);
        for(ProductMaterial productMaterial : productMaterialList){
            Materials material = materialsRepository.getReferenceById(productMaterial.materialId);
            ProductTypeMaterials productTypeMaterial = new ProductTypeMaterials();
            productTypeMaterial.setProduct_type_id(newProduct);
            productTypeMaterial.setMaterial_id(material);
            productTypeMaterial.setAmount(productMaterial.amount);
            productTypeMaterial.setRetreivable_percent(productMaterial.retrievablePercent);
            productTypeMaterialsList.add(productTypeMaterial);
            ProductTypeMaterialsKey x = new ProductTypeMaterialsKey();
            x.setMaterial_id(productMaterial.materialId);
            x.setProduct_type_id(newProduct.getId());
            productTypeMaterial.setId(x);

        }
        productTypeMaterialsRepository.saveAll(productTypeMaterialsList);
        return productTypeMaterialsList;
    }

    public boolean isValidProductTypeId(int productTypeId) {
        return productTypesRepository.existsById(productTypeId);
    }
}
