package com.example.circularinventory.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "product_type_materials")
public class ProductTypeMaterial {

    @EmbeddedId
    ProductTypeMaterialKey id;

    @ManyToOne
    @MapsId("materialId")
    @JoinColumn(name = "material_id")
    @JsonIgnore
    Material materialId;

    @ManyToOne
    @MapsId("productTypeId")
    @JoinColumn(name = "product_type_id")
    @JsonIgnore
    ProductType productTypeId;

    @Column
    Integer amount;

    @Column(name = "retrievable_percent")
    Integer retrievablePercent;



    public ProductTypeMaterialKey getId() {
        return id;
    }

    public void setId(ProductTypeMaterialKey id) {
        this.id = id;
    }

    public Material getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Material materialId) {
        this.materialId = materialId;
    }

    public ProductType getProductTypeId() {
        return productTypeId;
    }

    public void setProductTypeId(ProductType productTypeId) {
        this.productTypeId = productTypeId;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getRetrievablePercent() {
        return retrievablePercent;
    }

    public void setRetrievablePercent(Integer retrievablePercent) {
        this.retrievablePercent = retrievablePercent;
    }
}
