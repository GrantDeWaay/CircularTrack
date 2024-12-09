package com.example.circularinventory.repository;

import com.example.circularinventory.model.ProductTypeMaterials;
import com.example.circularinventory.model.ProductTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductTypeMaterialsRepository extends JpaRepository<ProductTypeMaterials, Integer> {

}