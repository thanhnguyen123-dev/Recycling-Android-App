package com.example.recycleme.search;

import com.example.recycleme.model.RecycledItem;
import com.example.recycleme.util.tree.AVLTreeItem;

import java.util.ArrayList;
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

    public List<RecycledItem> evaluateSearchExp(AVLTreeItem avlTreeItem) {
        String searchItem = item != null ? item: "";
        List<RecycledItem> result = new ArrayList<>();
        List<RecycledItem> resultMaterial = new ArrayList<>();

        for (String b : brand) {
            result.addAll(avlTreeItem.findItems(searchItem, b, ""));
        }

        for (String m : material) {
            resultMaterial.addAll(avlTreeItem.findItems(searchItem, "", m));
        }

        if (brand.isEmpty() && material.isEmpty()) {
            result.addAll(avlTreeItem.findItems(searchItem, "", ""));
        } else if (brand.isEmpty() || material.isEmpty()) {
            result.addAll(resultMaterial);
        } else {
            result.retainAll(resultMaterial);
        }
        return result;
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
