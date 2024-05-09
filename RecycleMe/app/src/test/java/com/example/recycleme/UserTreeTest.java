package com.example.recycleme;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.example.recycleme.cart.NodeData;
import com.example.recycleme.cart.UserTree;
import com.example.recycleme.model.RecycledItem;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class UserTreeTest {
    private UserTree userTree;

    @Before
    public void setUp() {
        userTree = UserTree.getInstance();
    }

    @After
    public void tearDown() {
        userTree.clear();
    }

    @Test
    public void testAddItems() {
        LocalDateTime time = LocalDateTime.now();
        List<RecycledItem> items = new ArrayList<>();
        items.add(new RecycledItem(1001, "Plastic Bottle", "Brand1", "PET", 0.2));
        items.add(new RecycledItem(1002, "Aluminum Can", "Brand2", "Aluminum", 0.1));

        userTree.addItems(time, items);

        List<RecycledItem> retrievedItems = userTree.searchItem(time);
        assertEquals(items, retrievedItems);
    }

    @Test
    public void testSearchItemNonExistentTime() {
        LocalDateTime nonExistentTime = LocalDateTime.now().plusDays(1);
        List<RecycledItem> retrievedItems = userTree.searchItem(nonExistentTime);
        assertNull(retrievedItems);
    }


    @Test
    public void testTraverseReturnItemAndDate() {
        LocalDateTime time1 = LocalDateTime.of(2024, 4, 25, 10, 0);
        LocalDateTime time2 = LocalDateTime.of(2024, 4, 24, 10, 0);
        LocalDateTime time3 = LocalDateTime.of(2024, 4, 24, 10, 1);

        userTree.addItems(time1, new ArrayList<>());
        userTree.addItems(time2, new ArrayList<>());
        userTree.addItems(time3, new ArrayList<>());

        List<NodeData<List<RecycledItem>>> nodeDataList = userTree.traverseReturnItemAndDate();
        assertEquals(3, nodeDataList.size());

        //
        assertEquals(time2, nodeDataList.get(0).getDateTime());
        assertEquals(time3, nodeDataList.get(1).getDateTime());
        assertEquals(time1, nodeDataList.get(2).getDateTime());
    }

    @Test
    public void testGetAllRecycledItems() {
        List<RecycledItem> allItems = userTree.getAllRecycledItems();
        assertEquals(0, allItems.size());
    }

    @Test
    public void testAddItemsWithSameTime() {
        LocalDateTime time = LocalDateTime.now();
        List<RecycledItem> items1 = new ArrayList<>();
        items1.add(new RecycledItem(2001, "Plastic Bottle", "Brand3", "PET", 0.2));
        items1.add(new RecycledItem(2002, "Aluminum Can", "Brand4", "Aluminum", 0.1));

        List<RecycledItem> items2 = new ArrayList<>();
        items2.add(new RecycledItem(2003, "Glass Jar", "Brand5", "Glass", 0.4));
        items2.add(new RecycledItem(2004, "Cardboard Box", "Brand6", "Cardboard", 0.8));

        userTree.addItems(time, items1);
        userTree.addItems(time, items2);

        List<RecycledItem> retrievedItems = userTree.searchItem(time);
        assertEquals(items1, retrievedItems);
    }

    @Test
    public void testAddItemsWithEmptyList() {
        LocalDateTime time = LocalDateTime.now();
        List<RecycledItem> emptyList = new ArrayList<>();

        userTree.addItems(time, emptyList);

        List<RecycledItem> retrievedItems = userTree.searchItem(time);
        assertEquals(new ArrayList<>(), retrievedItems);
    }

}
