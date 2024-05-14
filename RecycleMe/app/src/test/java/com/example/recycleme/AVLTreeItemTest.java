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
                new RecycledItem(5, "Glass Jar", "Beans", "E", 0.4),
                new RecycledItem(1, "Glass Jar", "Beans", "E", 0.4),
                new RecycledItem(5, "Kendrick", "Lamar", "Fire", 0.4),
                new RecycledItem(1, "Aqua", "Apple", "Water", 0.4)
        );
    }

    @Test
    public void testEmpty() {
        List<RecycledItem> items = this.avlTreeItem.findItems("", "", ""); //get everything
        assertTrue(items.isEmpty());
    }

    @Test
    public void testSameID() {
        // adding same ID to the avl tree
        // should be fine because the key is item, brand name, and material

        this.avlTreeItem.insert(this.recycledItemList.get(0));
        this.avlTreeItem.insert(this.recycledItemList.get(5));

        assertEquals(2, this.avlTreeItem.size());
    }

    @Test
    public void testSearchSameNameDifferentMaterial() {
        for (RecycledItem item: this.recycledItemList) {
            this.avlTreeItem.insert(item);
        }

        List<RecycledItem> items = this.avlTreeItem.findItems("Glass Jar", "Beans", "A");

        assertEquals(this.recycledItemList.get(0), items.get(0));
    }

    @Test
    public void testPartialMatch() {
        for (RecycledItem item: this.recycledItemList) {
            this.avlTreeItem.insert(item);
        }

        List<RecycledItem> items = this.avlTreeItem.findItems("Glass", "Beans", "A");
        assertEquals(this.recycledItemList.get(0), items.get(0));

    }

    @Test
    public void testPartialCaseInsensitive() {
        for (RecycledItem item: this.recycledItemList) {
            this.avlTreeItem.insert(item);
        }

        List<RecycledItem> items = this.avlTreeItem.findItems("aq", "", "");
        assertEquals(this.recycledItemList.get(this.recycledItemList.size() - 1), items.get(0));
    }

    @Test
    public void testMatchManyItems() {
        for (RecycledItem item: this.recycledItemList) {
            this.avlTreeItem.insert(item);
        }

        List<RecycledItem> items = this.avlTreeItem.findItems("Glass", "", "");

        assertEquals(5, items.size()); // 5, excluding 1 double
    }

    @Test
    public void testSearchCaseSensitive() {
        // should be case insensitive
        for (RecycledItem item: this.recycledItemList) {
            this.avlTreeItem.insert(item);
        }

        List<RecycledItem> items = this.avlTreeItem.findItems("glass jar", "beans", "a");
        assertEquals(this.recycledItemList.get(0), items.get(0));
    }

    @Test
    public void testForeignChar() {
        for (RecycledItem item: this.recycledItemList) {
            this.avlTreeItem.insert(item);
        }

        List<RecycledItem> items = this.avlTreeItem.findItems("汉字", "", "");
        assertTrue(items.isEmpty());
    }
}
