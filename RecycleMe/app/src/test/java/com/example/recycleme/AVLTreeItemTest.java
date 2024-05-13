package com.example.recycleme;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.example.recycleme.model.RecycledItem;
import com.example.recycleme.util.tree.AVLTreeItem;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Unit test to see how AVLTreeItem works with edge cases
 * @author Julius Liem
 */
public class AVLTreeItemTest {

    private AVLTreeItem avlTreeItem;
    private List<RecycledItem> recycledItemList;

    @Before
    public void setUp() {
        this.avlTreeItem = new AVLTreeItem();
        this.recycledItemList = Arrays.asList(
                new RecycledItem(1, "Glass Jar", "Beans", "A", 0.4),
                new RecycledItem(2, "Glass Jar", "Beans", "B", 0.4),
                new RecycledItem(3, "Glass Jar", "Beans", "C", 0.4),
                new RecycledItem(4, "Glass Jar", "Beans", "D", 0.4),
                new RecycledItem(5, "Glass Jar", "Beans", "E", 0.4)
        );
    }

    @Test
    public void testFindItemsEmptyTree() {
        List<RecycledItem> recycledItems = this.avlTreeItem.findItems("","","");
        assertEquals(recycledItems.size(), 0);
    }

    @Test
    public void testFindItemsSingleItem() {
        this.avlTreeItem.insert(recycledItemList.get(0));

        List<RecycledItem> result = avlTreeItem.findItems("", "", "");
        assertEquals(1, result.size());
        assertEquals(recycledItemList.get(0), result.get(0));
    }

    @Test
    public void testFindItemsMultipleItems() {
        for (RecycledItem item: this.recycledItemList) {
            this.avlTreeItem.insert(item);
        }

        List<RecycledItem> result = avlTreeItem.findItems("Glass Jar", "", "");
        assertEquals(5, result.size());

        for (RecycledItem item: recycledItemList) {
            assertTrue(result.contains(item));
        }
    }

    @Test
    public void testFindItemsPartialMatch() {
        for (RecycledItem item: this.recycledItemList) {
            this.avlTreeItem.insert(item);
        }

        List<RecycledItem> result = avlTreeItem.findItems("glass", "", "");
        assertEquals(5, result.size());
    }

    @Test
    public void testFindItemsCaseInsensitive() {
        for (RecycledItem item: this.recycledItemList) {
            this.avlTreeItem.insert(item);
        }

        List<RecycledItem> result = avlTreeItem.findItems("", "", "a");

        assertEquals(1,result.size());
        assertEquals(this.recycledItemList.get(0), result.get(0));
    }

}
