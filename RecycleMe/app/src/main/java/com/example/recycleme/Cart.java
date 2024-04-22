package com.example.recycleme;

import java.util.ArrayList;

public class Cart {
    private static Cart instance = null;
    private ArrayList<RecycledItem> items;

    private Cart(ArrayList<RecycledItem> items) {
        this.items = items;
    }

    public static Cart getInstance() {
        if (instance == null) {
            instance = new Cart(new ArrayList<>());
        }
        return instance;
    }

    public ArrayList<RecycledItem> getItems() {
        return this.items;
    }

    //Adds an item to the cart
    public void addItem(RecycledItem item) {
        this.items.add(item);
}}
