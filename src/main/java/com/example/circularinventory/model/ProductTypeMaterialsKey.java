package com.example.circularinventory.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Table;

import java.io.Serializable;

@Embeddable
public class ProductTypeMaterialsKey implements Serializable {

    @Column(name = "material_id")
    Integer material_id;

    @Column(name = "product_type_id")
    Integer product_type_id;

    public Integer getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(Integer material_id) {
        this.material_id = material_id;
    }

    public Integer getProduct_type_id() {
        return product_type_id;
    }

    public void setProduct_type_id(Integer product_type_id) {
        this.product_type_id = product_type_id;
    }
}
