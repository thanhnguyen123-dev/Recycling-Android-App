package com.example.recycleme.cart;

import com.example.recycleme.RecycledItem;

import java.util.ArrayList;

public class Cart {
    private static Cart instance = null;
    private ArrayList<RecycledItem> items;

    private Cart() {
        this.items = new ArrayList<>();
    }

    public static Cart getInstance() {
        if (instance == null) {
            instance = new Cart();
        }
        return instance;
    }

    public ArrayList<RecycledItem> getItems() {
        return this.items;
    }

    //Adds an item to the cart
    public void addItem(RecycledItem item) {
        this.items.add(item);
    }

    public void clear() {
        items.clear();
    }

    public void removeItem(RecycledItem item) {
        items.remove(item);
    }
}
