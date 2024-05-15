package com.example.recycleme.cart;
import com.example.recycleme.model.RecycledItem;
import com.example.recycleme.util.tree.AVLTreeTime;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Singleton class that manages an AVLTree of user history (stored in NodeData)
 * Provides method to add, search, clear, and retrieve items from tree.
 * When it is started, it simulates the addition of user items (so the History of the users looks populated)
 * The tree is sorted based on LocalDateTime.
 *
 * @author Julius Liem - u7724204
 */
public class UserTree {
    private static UserTree instance;
    private AVLTreeTime avlTree;

    public static UserTree getInstance() {
        if (instance == null) {
            instance = new UserTree();
            instance.simulatePreviousAddition();
        }

        return instance;
    }

    public void clear() {
        this.avlTree = new AVLTreeTime();
    }

    private UserTree() {
        this.avlTree = new AVLTreeTime();
    }

    public void addItems(LocalDateTime time, List<RecycledItem> itemsWantToBeAdded) {
        this.avlTree.insert(time, itemsWantToBeAdded);
    }

    public List<RecycledItem> searchItem(LocalDateTime time) {
        return this.avlTree.searchItem(time);
    }

    public void deleteItem(LocalDateTime time) {
        this.avlTree.delete(new NodeData<>(time, new ArrayList<>()));
        return;
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

    public void simulatePreviousAddition() {
        List<RecycledItem> recycledItems = Arrays.asList(
                new RecycledItem(3001, "Plastic Bottle", "Coca-Cola", "PET", 0.2),
                new RecycledItem(4001, "Aluminum Can", "Pepsi", "Aluminum", 0.1),
                new RecycledItem(5001, "Glass Jar", "Heinz", "Glass", 0.4),
                new RecycledItem(6001, "Cardboard Box", "Amazon", "Cardboard", 0.8),
                new RecycledItem(7001, "Paper Bag", "Whole Foods", "Paper", 0.3)
        );

        this.avlTree.insert(LocalDateTime.of(2024, 4, 25,10,0), recycledItems);
        this.avlTree.insert(LocalDateTime.of(2024, 4, 24,10,0), recycledItems);
        this.avlTree.insert(LocalDateTime.of(2024, 4, 24,10,1), recycledItems);
    }
}
