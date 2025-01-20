package com.example.circularinventory.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "product_types")
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_type_id")
    Integer id;

    @OneToMany(mappedBy = "productTypeId")
    Set<ProductTypeMaterial> productTypeMaterialSet;

    @Column
    String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<ProductTypeMaterial> getProductTypeMaterialsSet() {
        return productTypeMaterialSet;
    }

    public void setProductTypeMaterialsSet(Set<ProductTypeMaterial> productTypeMaterialSet) {
        this.productTypeMaterialSet = productTypeMaterialSet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
