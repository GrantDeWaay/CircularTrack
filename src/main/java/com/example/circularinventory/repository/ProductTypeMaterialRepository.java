package com.example.circularinventory.repository;

import com.example.circularinventory.model.ProductTypeMaterial;
import com.example.circularinventory.model.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTypeMaterialRepository extends JpaRepository<ProductTypeMaterial, Integer> {

    void deleteAllByProductTypeIdEquals(ProductType productTypeId);
}