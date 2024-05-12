package com.example.recycleme.model;

import java.util.Objects;


/**
 * Model class for recycled item.
 * @author Julius Liem
 */
public class RecycledItem implements Comparable<RecycledItem>{
    private String item;
    private int id;
    private String brand;
    private String material;
    private Double value;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public RecycledItem(int id, String item, String brandName, String material, Double value) {
        if (value < 0) {
            throw new IllegalArgumentException("Value should not be less than 0!");
        }

        this.item = item;
        this.id = id;
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

    @Override
    public String toString() {
        return "RecycledItem{" +
                "item='" + item + '\'' +
                ", id=" + id +
                ", brand='" + brand + '\'' +
                ", material='" + material + '\'' +
                ", value=" + value +
                '}';
    }

    /*
    Note here that the equals method only comapres the item, brand, and material
    This is a deliberate design decision to ease searching in the AvlTreeItem
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecycledItem that = (RecycledItem) o;
        return Objects.equals(item.toLowerCase(), that.item.toLowerCase()) && Objects.equals(brand.toLowerCase(), that.brand.toLowerCase()) && Objects.equals(material.toLowerCase(), that.material.toLowerCase());
    }

    @Override
    public int hashCode() {
        return Objects.hash(item, brand, material);
    }


    /*
    compareTo method in order to put the item inside an AVLTree
    The recycled item will first be compared to the other item's item name
    If the item name is the same, it will be compared with brand, and then material

    This is also why searching on AVLTreeItem takes O(n) time when there's no item name.
    When there's no item name we're forced to traverse the whole tree.
    */
    @Override
    public int compareTo(RecycledItem o) {
        int itemComparison = this.getItem().toLowerCase().compareTo(o.getItem().toLowerCase());

        if (itemComparison != 0) {
            return itemComparison;
        }

        int brandComparison = this.getBrandName().toLowerCase().compareTo(o.getBrandName().toLowerCase());
        if (brandComparison != 0) {
            return brandComparison;
        }

        return this.getMaterial().toLowerCase().compareTo(o.getMaterial().toLowerCase());
    }
}
