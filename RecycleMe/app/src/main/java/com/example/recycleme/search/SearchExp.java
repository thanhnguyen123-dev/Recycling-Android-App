package com.example.recycleme.search;

import com.example.recycleme.RecycledItem;

import java.util.HashSet;
import java.util.List;

public class SearchExp {
    private String item;
    private HashSet<String> brand;
    private HashSet<String> material;

    public SearchExp(String item, HashSet<String> brand, HashSet<String> material) {
        this.item = item;
        this.brand = brand;
        this.material = material;
    }

    public String show() {
        return "Item: " + item + "; Brand: " + brand.toString() + "; Material: " + material.toString();
    }

    public List<RecycledItem> evaluateSearchExp() {
        return null;
    }

    public String getItem() {
        return item;
    }

    public HashSet<String> getBrand() {
        return brand;
    }

    public HashSet<String> getMaterial() {
        return material;
    }
}
