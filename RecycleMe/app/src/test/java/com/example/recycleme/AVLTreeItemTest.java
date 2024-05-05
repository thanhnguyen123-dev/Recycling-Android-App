package com.example.recycleme;

import static org.junit.Assert.assertEquals;

import com.example.recycleme.cart.AVLTree;
import com.example.recycleme.cart.AVLTreeItem;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class AVLTreeItemTest {
    @Test
    public void testCeilingRecycledItem() {
        AVLTreeItem recycledItemAVLTree = new AVLTreeItem();

        List<RecycledItem> recycledItems = Arrays.asList(
                new RecycledItem(5001, "Glass Jar", "Beans", "A", 0.4),
                new RecycledItem(8001, "Glass Jar", "Beans", "B", 0.4),
                new RecycledItem(8001, "Glass Jar", "Beans", "C", 0.4),
                new RecycledItem(5001, "Glass Jar", "Beans", "D", 0.4),
                new RecycledItem(8001, "Glass Jar", "Beans", "E", 0.4),
                new RecycledItem(8001, "Glass Jar", "Beans", "F", 0.4),
                new RecycledItem(5001, "Broksz", "Ceans", "Aa", 0.4),
                new RecycledItem(5001, "Broksz", "Ceans", "Ab", 0.4),
                new RecycledItem(8001, "Ausa", "Beans", "B", 0.4),
                new RecycledItem(8001, "Ausa", "Ceans", "C", 0.4),
                new RecycledItem(5001, "Thing", "Deans", "D", 0.4),
                new RecycledItem(8001, "What", "Feans", "E", 0.4),
                new RecycledItem(8001, "This", "Geans", "F", 0.4)
        );

        for (RecycledItem recycledItem: recycledItems) {
            recycledItemAVLTree.insert(recycledItem);
        }

        assertEquals(Arrays.asList(recycledItems.get(6), recycledItems.get(7)), recycledItemAVLTree.findItems("broksz", "ceans", "a"));
        assertEquals(Arrays.asList(recycledItems.get(8), recycledItems.get(9)), recycledItemAVLTree.findItems("Ausa", "", ""));
    }
}
