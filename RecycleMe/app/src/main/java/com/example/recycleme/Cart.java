package com.example.recycleme;

import java.util.ArrayList;

public class Cart{

    public ArrayList<RecycledItem> cartItems;

    public Cart(ArrayList<RecycledItem> itemsToAdd){
        this.cartItems = itemsToAdd;
    }

    //Adds an item to the cart
    public void addItem(RecycledItem item) {
        if (cartItems == null) {
            cartItems = new ArrayList<>();
        }
        cartItems.add(item);
    }


    public ArrayList<RecycledItem> getItems() {
        return this.cartItems;
    }
}
