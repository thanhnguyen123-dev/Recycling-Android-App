package com.example.recycleme.cart;

import com.example.recycleme.RecycledItem;
import com.example.recycleme.interfaces.IterableCollection;
import com.example.recycleme.interfaces.Iterator;

import java.util.ArrayList;

public class Cart implements IterableCollection {
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

    @Override
    public Iterator createIterator() {
        return new CartIterator();
    }

    private class CartIterator implements Iterator {

        int index;

        @Override
        public boolean hasNext() {
            if (!items.isEmpty() && index < items.size()) {
                return true;
            }

            return false;
        }

        @Override
        public Object next() {
            if (this.hasNext()) {
                return items.get(index++);
            }

            return null;
        }
    }
}
