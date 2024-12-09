package com.example.circularinventory.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "product_types")
public class ProductTypes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_type_id")
    Integer id;

    @OneToMany(mappedBy = "product_type_id")
    Set<ProductTypeMaterials> productTypeMaterialsSet;

    @Column
    String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<ProductTypeMaterials> getProductTypeMaterialsSet() {
        return productTypeMaterialsSet;
    }

    public void setProductTypeMaterialsSet(Set<ProductTypeMaterials> productTypeMaterialsSet) {
        this.productTypeMaterialsSet = productTypeMaterialsSet;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
