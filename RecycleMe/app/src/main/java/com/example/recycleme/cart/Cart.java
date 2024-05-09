package com.example.recycleme.cart;

import com.example.recycleme.model.RecycledItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cart{
    private static Cart instance = null;
    private Map<String, List<RecycledItem>> itemMaterialMap;

    private Cart() {
        this.itemMaterialMap = new HashMap<>();
    }

    public Map<String, List<RecycledItem>> getItemMaterialMap() {
        return itemMaterialMap;
    }

    public static Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    public ArrayList<RecycledItem> getItems() {
        return flattenMap(this.itemMaterialMap);
    }

    //Adds an item to the cart
    public void addItem(RecycledItem item) {
        String material = item.getMaterial();

        List<RecycledItem> itemsWithSameMaterial = this.itemMaterialMap.getOrDefault(material, new ArrayList<>());
        itemsWithSameMaterial.add(item);

        this.itemMaterialMap.put(material, itemsWithSameMaterial);
    }

    public void clear() {
        this.itemMaterialMap.clear();
    }

    public void removeItem(RecycledItem item) {
        String material = item.getMaterial();

        List<RecycledItem> itemsWithSameMaterial = this.itemMaterialMap.getOrDefault(material, new ArrayList<>());
        if (itemsWithSameMaterial.contains(item)) {
            itemsWithSameMaterial.remove(item);

            if (itemsWithSameMaterial.isEmpty()) {
                this.itemMaterialMap.remove(material);
            }
        }
    }

    private static ArrayList<RecycledItem> flattenMap(Map<String, List<RecycledItem>> map) {
        ArrayList<RecycledItem> flattenedMap = new ArrayList<>();

        for (Map.Entry<String, List<RecycledItem>> entry: map.entrySet()) {
            flattenedMap.addAll(entry.getValue());
        }

        return flattenedMap;
    }

}
