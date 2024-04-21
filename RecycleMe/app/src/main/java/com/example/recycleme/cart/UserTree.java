package com.example.recycleme.cart;

import com.example.recycleme.RecycledItem;
import com.example.recycleme.treedb.RedBlackTree;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserTree {
    private static UserTree instance;
    private RedBlackTree<List<RecycledItem>> rbTree;

    public static UserTree getInstance() {
        if (instance == null) {
            instance = new UserTree();
        }

        return instance;
    }

    private UserTree() {
        this.rbTree = new RedBlackTree<>();
    }

    public void addItems(LocalDateTime time, List<RecycledItem> itemsWantToBeAdded) {
        this.rbTree.insert(time, itemsWantToBeAdded);
    }

    public List<RecycledItem> searchItem(LocalDateTime time) {
        return this.rbTree.search(time);
    }

    public void traverse() {
        this.rbTree.traverse();
    }
}
