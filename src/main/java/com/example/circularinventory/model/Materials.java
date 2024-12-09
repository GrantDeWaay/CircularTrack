package com.example.circularinventory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "materials")
public class Materials {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer material_id;

    @Column
    String name;

    @Column
    Integer amount;

    @OneToMany(mappedBy = "material_id")
    Set<ProductTypeMaterials> productTypeMaterialsSet;

    public Integer getId() {
        return material_id;
    }

    public void setId(Integer id) {
        this.material_id = id;
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

    public Set<ProductTypeMaterials> getProductTypeMaterialsSet() {
        return productTypeMaterialsSet;
    }

    public void setProductTypeMaterialsSet(Set<ProductTypeMaterials> productTypeMaterialsSet) {
        this.productTypeMaterialsSet = productTypeMaterialsSet;
    }
}
