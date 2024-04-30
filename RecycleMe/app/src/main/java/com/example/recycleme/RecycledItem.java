package com.example.recycleme;

import java.util.Objects;

public class RecycledItem {
    private String item;

    private String id;
    private String brand;
    private String material;
    private Double value;

    public RecycledItem(String item, String brandName, String material, Double value) {
        this.item = item;
        this.brand = brandName;
        this.material = material;
        this.value = value;
    }

    public String getItem() {
        return item;
    }

    public String getBrandName() {
        return brand;
    }

    public String getMaterial() {
        return material;
    }

    public Double getValue() {
        return value;
    }

    public String getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecycledItem that = (RecycledItem) o;
        return id == that.id && Objects.equals(item, that.item) && Objects.equals(brand, that.brand) && Objects.equals(material, that.material) && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item, id, brand, material, value);
    }


    public void setId(String id) {
        this.id = id;
    }
}
