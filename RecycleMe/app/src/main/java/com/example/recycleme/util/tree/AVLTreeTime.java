package com.example.recycleme.util.tree;

import com.example.recycleme.model.RecycledItem;
import com.example.recycleme.cart.NodeData;

import java.time.LocalDateTime;
import java.util.List;


/**
 * Extension of the AVLTree class that specifically handles NodeData
 * Can be considered as a facade for AVLTree and NodeData
 * Provides methods to insert, search, and traverse based on time
 *
 * @author Julius Liem
 */
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
