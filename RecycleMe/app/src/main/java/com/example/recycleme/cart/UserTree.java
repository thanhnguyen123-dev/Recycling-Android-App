package com.example.recycleme.cart;
import com.example.recycleme.RecycledItem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserTree {
    private static UserTree instance;
    private AVLTree<List<RecycledItem>> avlTree;

    public static UserTree getInstance() {
        if (instance == null) {
            instance = new UserTree();
            instance.simulatePreviousAddition();
        }

        return instance;
    }

    private UserTree() {
        this.avlTree = new AVLTree<>();
    }

    public void addItems(LocalDateTime time, List<RecycledItem> itemsWantToBeAdded) {
        this.avlTree.insert(time, itemsWantToBeAdded);
    }

    public List<RecycledItem> searchItem(LocalDateTime time) {
        return this.avlTree.search(time);
    }


    public List<NodeData<List<RecycledItem>>> traverseReturnItemAndDate() {
        return this.avlTree.traverseAndReturnDataWithTime();
    }

    public List<RecycledItem> getAllRecycledItems() {
        List<NodeData<List<RecycledItem>>> nodeDataList = this.avlTree.traverseAndReturnDataWithTime();
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

        this.avlTree.insert(LocalDateTime.of(2024, 4, 25,10,0), recycledItems);
        this.avlTree.insert(LocalDateTime.of(2024, 4, 24,10,0), recycledItems);
        this.avlTree.insert(LocalDateTime.of(2024, 4, 24,10,1), recycledItems);
    }
}
