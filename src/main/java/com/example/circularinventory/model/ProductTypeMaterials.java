package com.example.circularinventory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "product_type_materials")
public class ProductTypeMaterials {

    @EmbeddedId
    ProductTypeMaterialsKey id;

    @ManyToOne
    @MapsId("material_id")
    @JoinColumn(name = "material_id")
    @JsonIgnore
    Materials material_id;

    @ManyToOne
    @MapsId("product_type_id")
    @JoinColumn(name = "product_type_id")
    @JsonIgnore
    ProductTypes product_type_id;

    @Column
    Integer amount;

    @Column
    Integer retrievable_percent;



    public ProductTypeMaterialsKey getId() {
        return id;
    }

    public void setId(ProductTypeMaterialsKey id) {
        this.id = id;
    }

    public Materials getMaterial_id() {
        return material_id;
    }

    public void setMaterial_id(Materials material_id) {
        this.material_id = material_id;
    }

    public ProductTypes getProduct_type_id() {
        return product_type_id;
    }

    public void setProduct_type_id(ProductTypes product_type_id) {
        this.product_type_id = product_type_id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getRetrievable_percent() {
        return retrievable_percent;
    }

    public void setRetreivable_percent(Integer retrievable_percent) {
        this.retrievable_percent = retrievable_percent;
    }
}
