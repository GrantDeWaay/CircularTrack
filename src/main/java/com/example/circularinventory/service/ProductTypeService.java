package com.example.circularinventory.service;

import com.example.circularinventory.dto.*;
import com.example.circularinventory.model.*;
import com.example.circularinventory.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductTypeService {

    private final ProductTypeRepository productTypeRepository;
    private final MaterialRepository materialRepository;
    private final ProductTypeMaterialRepository productTypeMaterialRepository;


    @Autowired
    public ProductTypeService(ProductTypeRepository productTypeRepository, ProductRepository productRepository, MaterialRepository materialRepository, ProductTypeMaterialRepository productTypeMaterialRepository) {
        this.productTypeRepository = productTypeRepository;
        this.materialRepository = materialRepository;
        this.productTypeMaterialRepository = productTypeMaterialRepository;

    }

    @Transactional
    public List<ProductTypeMaterial> createNewProductType(CreateProductTypeDto createProductTypeDto){
        List<ProductMaterial> productMaterialList = createProductTypeDto.productMaterials;
        List<ProductTypeMaterial> productTypeMaterialList = new ArrayList<>();
        ProductType productType = new ProductType();
        productType.setName(createProductTypeDto.name);
        ProductType newProduct = productTypeRepository.save(productType);
        for(ProductMaterial productMaterial : productMaterialList){
            Material material = materialRepository.getReferenceById(productMaterial.materialId);
            ProductTypeMaterial productTypeMaterial = new ProductTypeMaterial();
            productTypeMaterial.setProductTypeId(newProduct);
            productTypeMaterial.setMaterialId(material);
            productTypeMaterial.setAmount(productMaterial.amount);
            productTypeMaterial.setRetrievablePercent(productMaterial.retrievablePercent);
            productTypeMaterialList.add(productTypeMaterial);
            ProductTypeMaterialKey x = new ProductTypeMaterialKey();
            x.setMaterialId(productMaterial.materialId);
            x.setProductTypeId(newProduct.getId());
            productTypeMaterial.setId(x);

        }
        productTypeMaterialRepository.saveAll(productTypeMaterialList);
        return productTypeMaterialList;
    }

    @Transactional
    public List<ProductTypeMaterial> updateProductType(UpdateProductTypeDto updateProductTypeDto){
        List<ProductMaterial> productMaterialList = updateProductTypeDto.materials;
        List<ProductTypeMaterial> productTypeMaterialList = new ArrayList<>();
        ProductType productType = productTypeRepository.getReferenceById(updateProductTypeDto.id);
        productTypeMaterialRepository.deleteAllByProductTypeIdEquals(productType);
        ProductType newProduct = productTypeRepository.save(productType);
        for(ProductMaterial productMaterial : productMaterialList){
            Material material = materialRepository.getReferenceById(productMaterial.materialId);
            ProductTypeMaterial productTypeMaterial = new ProductTypeMaterial();
            productTypeMaterial.setProductTypeId(newProduct);
            productTypeMaterial.setMaterialId(material);
            productTypeMaterial.setAmount(productMaterial.amount);
            productTypeMaterial.setRetrievablePercent(productMaterial.retrievablePercent);
            productTypeMaterialList.add(productTypeMaterial);
            ProductTypeMaterialKey x = new ProductTypeMaterialKey();
            x.setMaterialId(productMaterial.materialId);
            x.setProductTypeId(newProduct.getId());
            productTypeMaterial.setId(x);

        }
        productTypeMaterialRepository.saveAll(productTypeMaterialList);
        return productTypeMaterialList;
    }

    public List<ProductTypeResponseDto> getProductTypes() {
        List<ProductType> productTypes = productTypeRepository.findAll();
        return productTypes.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    public ProductTypeResponseDto getProductTypeById(GetProductTypeByIdDto getProductTypeByIdDto) {
        Optional<ProductType> productTypes = productTypeRepository.findById(getProductTypeByIdDto.id);
        if(productTypes.isPresent()){
            ProductType productType = productTypes.get();
            return mapToDto(productType);
        }
        return null;
    }

    private ProductTypeResponseDto mapToDto(ProductType productType) {
        ProductTypeResponseDto dto = new ProductTypeResponseDto();
        dto.setId(productType.getId());
        dto.setName(productType.getName());
        dto.setMaterials(productType.getProductTypeMaterialsSet().stream().map(this::mapToMaterialDto).collect(Collectors.toList()));
        return dto;
    }

    private ProductTypeResponseDto.MaterialDto mapToMaterialDto(ProductTypeMaterial productTypeMaterial) {
        ProductTypeResponseDto.MaterialDto materialDto = new ProductTypeResponseDto.MaterialDto();
        materialDto.setMaterialId(productTypeMaterial.getMaterialId().getId());
        materialDto.setMaterialName(productTypeMaterial.getMaterialId().getName());
        materialDto.setAmount(productTypeMaterial.getAmount());
        materialDto.setRetrievablePercent(productTypeMaterial.getRetrievablePercent());
        return materialDto;
    }

    public boolean isValidProductTypeId(int productTypeId) {
        return productTypeRepository.existsById(productTypeId);
    }
}
