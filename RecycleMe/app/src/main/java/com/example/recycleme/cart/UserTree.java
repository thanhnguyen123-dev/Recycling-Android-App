package com.example.recycleme.cart;
import com.example.recycleme.RecycledItem;
import com.example.recycleme.RecycledItemDb;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserTree {
    private static UserTree instance;
    private RedBlackTree<List<RecycledItem>> rbTree;

    public static UserTree getInstance() {
        if (instance == null) {
            instance = new UserTree();
            instance.simulatePreviousAddition();
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

    public List<NodeData<List<RecycledItem>>> traverseReturnItemAndDate() {
        return this.rbTree.traverseAndReturnDataWithTime();
    }

    public List<RecycledItem> getAllRecycledItems() {
        List<NodeData<List<RecycledItem>>> nodeDataList = this.rbTree.traverseAndReturnDataWithTime();
        List<RecycledItem> recycledItems = new ArrayList<>();

        for (NodeData<List<RecycledItem>> nodeData : nodeDataList) {
            List<RecycledItem> itemList = nodeData.getValue();
            recycledItems.addAll(itemList);
        }

        return recycledItems;
    }

    private void simulatePreviousAddition() {
        List<RecycledItem> recycledItems = Arrays.asList(
                new RecycledItem("Plastic Bottle", "Coca-Cola", "PET", 0.2),
                new RecycledItem("Aluminum Can", "Pepsi", "Aluminum", 0.1),
                new RecycledItem("Glass Jar", "Heinz", "Glass", 0.4),
                new RecycledItem("Cardboard Box", "Amazon", "Cardboard", 0.8),
                new RecycledItem("Paper Bag", "Whole Foods", "Paper", 0.3)
        );

        this.rbTree.insert(LocalDateTime.of(2024, 4, 25,10,0), recycledItems);
        this.rbTree.insert(LocalDateTime.of(2024, 4, 24,10,0), recycledItems);
        this.rbTree.insert(LocalDateTime.of(2024, 4, 24,10,1), recycledItems);
    }
}
