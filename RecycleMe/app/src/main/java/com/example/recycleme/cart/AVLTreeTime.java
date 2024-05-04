package com.example.recycleme.cart;

import com.example.recycleme.RecycledItem;

import java.time.LocalDateTime;
import java.util.List;

public class AVLTreeTime extends AVLTree<NodeData<List<RecycledItem>>> {

    public AVLTreeTime() {
        super();
    }

    public void insert(LocalDateTime time, List<RecycledItem> itemsWantToBeAdded) {
        NodeData<List<RecycledItem>> toAdd =new NodeData<List<RecycledItem>>(time, itemsWantToBeAdded);
        insert(toAdd);
    }

    public List<NodeData<List<RecycledItem>>> traverseAndReturnDataWithTime() {
        return this.traverse();
    }

    public List<RecycledItem> searchItem(LocalDateTime time) {
        try {
            return this.search(new NodeData<>(time, null)).getValue();
        } catch (NullPointerException e) {
            return null;
        }

    }

}
