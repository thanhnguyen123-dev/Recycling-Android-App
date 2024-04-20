package com.example.recycleme.cart;

import com.example.recycleme.RecycledItem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ItemsCart {
    private ArrayList<RecycledItem> items;
    private static ItemsCart instance;

    public static ItemsCart getInstance() {
        if (instance == null) {
            instance = new ItemsCart();
        }

        return instance;
    }

    private ItemsCart() {
        items = new ArrayList<>();
    }

    public void addItem(RecycledItem item) {
        items.add(item);
    }

    public void removeItem(RecycledItem item) {
        items.remove(item);
    }

    public List<RecycledItem> getItems() {
        return items;
    }

    public void clear() {
        items.clear();
    }

    public void saveItemsInTree(LocalDateTime time) {
        UserTree tree = UserTree.getInstance();
        tree.addItems(time, this.items);
    }
}
