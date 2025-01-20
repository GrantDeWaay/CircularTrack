package com.example.circularinventory.model;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "materials")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_id") // Ensure this matches the actual column name in your database
    Integer materialId;

    @Column
    String name;

    @Column
    Integer amount;

    @OneToMany(mappedBy = "materialId")
    Set<ProductTypeMaterial> productTypeMaterialSet;

    public Integer getId() {
        return materialId;
    }

    public void setId(Integer id) {
        this.materialId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Set<ProductTypeMaterial> getProductTypeMaterialsSet() {
        return productTypeMaterialSet;
    }

    public void setProductTypeMaterialsSet(Set<ProductTypeMaterial> productTypeMaterialSet) {
        this.productTypeMaterialSet = productTypeMaterialSet;
    }
}
