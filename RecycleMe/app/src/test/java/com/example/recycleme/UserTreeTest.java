package com.example.recycleme;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.example.recycleme.cart.NodeData;
import com.example.recycleme.cart.UserTree;
import com.example.recycleme.model.RecycledItem;

import net.bytebuddy.asm.Advice;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Unit test to see how UserTree works
 * This unit test tests edge cases that can happen to the UserTree class (e.g. non existent time, etc.)
 * @author Julius Liem
 */
public class UserTreeTest {
    private UserTree userTree;
    private List<RecycledItem> recycledItems;

    @Before
    public void setUp() {
        userTree = UserTree.getInstance();
        userTree.clear();
        this.recycledItems = Arrays.asList(new RecycledItem(1, "bread", "sbux", "dough", 1.0),
                new RecycledItem(2, "coffee", "beans", "tears", 2.0),
                new RecycledItem(3, "floss", "colgate", "plastic", 3.0),
                new RecycledItem(4, "tissue", "wetones", "paper", 1.0));
    }

    @After
    public void tearDown() {
        userTree.clear();
    }

    @Test
    public void testAddItemNormal() {
        LocalDateTime time = LocalDateTime.now();

        addItem(time);
        List<RecycledItem> recycledItems = userTree.getAllRecycledItems();

        assertEquals(4, recycledItems.size());
    }

    @Test
    public void testAddItemSameDateTime() {
        // should not work
        LocalDateTime time = LocalDateTime.now();
        addItem(time);
        userTree.addItems(time, new ArrayList<>());

        List<RecycledItem> recycledItems = userTree.getAllRecycledItems();

        assertEquals(4, recycledItems.size());
    }

    @Test
    public void testDeleteItemEmptyTree() {
        userTree.deleteItem(LocalDateTime.now());

        List<RecycledItem> recycledItems = userTree.getAllRecycledItems();

        assertTrue(recycledItems.isEmpty());
    }

    @Test
    public void testDeleteNonExistentTime() {
        LocalDateTime time = LocalDateTime.now();
        addItem(time);

        userTree.deleteItem(LocalDateTime.now());

        List<RecycledItem> recycledItems = userTree.getAllRecycledItems();

        assertEquals(4, recycledItems.size());
    }

    @Test
    public void testTraverseReturnItemAndDate() {
        List<RecycledItem> items = Arrays.asList(new RecycledItem(1, "bread", "sbux", "dough", 1.0),
                new RecycledItem(2, "coffee", "beans", "tears", 2.0),
                new RecycledItem(3, "floss", "colgate", "plastic", 3.0),
                new RecycledItem(4, "tissue", "wetones", "paper", 1.0));

        LocalDateTime time = LocalDateTime.now();
        this.addItem(time);
        LocalDateTime time2 = LocalDateTime.now();
        this.addItem(time2);

        List<NodeData<List<RecycledItem>>> nodeData = this.userTree.traverseReturnItemAndDate();

        for (NodeData<List<RecycledItem>> node: nodeData) {
            LocalDateTime currentTime = node.getDateTime();
            List<RecycledItem> value = node.getValue();

            assertTrue(currentTime.equals(time) || currentTime.equals(time2));
            assertEquals(value, items);
        }

    }

    @Test
    public void testGetAllRecycledItems() {
        LocalDateTime time = LocalDateTime.now();
        addItem(time);

        for (RecycledItem item: this.recycledItems) {
            assertTrue(userTree.getAllRecycledItems().contains(item));
        }
    }

    private void addItem(LocalDateTime time) {
        this.userTree.addItems(time, this.recycledItems);
    }

}
