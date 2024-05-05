package com.example.recycleme;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.example.recycleme.cart.AVLTree;
import com.example.recycleme.cart.NodeData;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AVLTreeTest {
    private AVLTree<NodeData<Integer>> avlTree;

    private LocalDateTime time1;
    private LocalDateTime time2;
    private LocalDateTime time3;

   @Before
    public void setUp() {
       avlTree = new AVLTree<>();
       time1 = LocalDateTime.of(2023, 5, 1, 10, 0);
       time2 = LocalDateTime.of(2023, 5, 2, 12, 0);
       time3 = LocalDateTime.of(2023, 5, 3, 14, 0);

       NodeData<Integer> first = new NodeData<>(time1, 10);
       NodeData<Integer> second = new NodeData<>(time2, 12);
       NodeData<Integer> third = new NodeData<>(time3, 14);

       avlTree.insert(first);
       avlTree.insert(second);
       avlTree.insert(third);
   }

    @Test
    public void testSize() {
        assertEquals(3, avlTree.size());
    }

    @Test
    public void testSearch() {
        int value1 = avlTree.search(new NodeData<>(time1, 0)).getValue();
        int value2 = avlTree.search(new NodeData<>(time2, 0)).getValue();
        int value3 = avlTree.search(new NodeData<>(time3, 0)).getValue();

        assertEquals(10, value1);
        assertEquals(12, value2);
        assertEquals(14, value3);
    }

    @Test
    public void testTraverseAndReturnDataWithTime() {
        List<NodeData<Integer>> nodeDataList = avlTree.traverse();

        assertEquals(3, nodeDataList.size());
        assertEquals(time1, nodeDataList.get(0).getDateTime());
        assertEquals(Integer.valueOf(10), nodeDataList.get(0).getValue());
        assertEquals(time2, nodeDataList.get(1).getDateTime());
        assertEquals(Integer.valueOf(12), nodeDataList.get(1).getValue());
        assertEquals(time3, nodeDataList.get(2).getDateTime());
        assertEquals(Integer.valueOf(14), nodeDataList.get(2).getValue());
    }

    @Test
    public void testInsertWithSameDateTime() {
        AVLTree<NodeData<Integer>> avlTree = new AVLTree<NodeData<Integer>>();
        LocalDateTime time1 = LocalDateTime.of(2023, 5, 1, 10, 0);
        LocalDateTime time2 = LocalDateTime.of(2023, 5, 1, 10, 0);

        NodeData<Integer> first = new NodeData<>(time1, 10);
        NodeData<Integer> second = new NodeData<>(time2, 12);
        avlTree.insert(first);
        avlTree.insert(second);

        assertEquals(1, avlTree.size());
        assertEquals(Integer.valueOf(10), avlTree.search(new NodeData<>(time1, 0)).getValue());
    }

    @Test
    public void testSearchNonExistentNode() {
        LocalDateTime time2 = LocalDateTime.of(2023, 5, 2, 15, 0);

        assertNull(avlTree.search(new NodeData<>(time2, 0)));
    }

    @Test
    public void testTraverseEmptyTree() {
       AVLTree<NodeData<Integer>> avlTree = new AVLTree<>();
       List<NodeData<Integer>> nodeDataList = avlTree.traverse();

       assertTrue(nodeDataList.isEmpty());
    }

    @Test
    public void testDelete() {
        avlTree.delete(new NodeData<>(time2, 0));

        assertEquals(2, avlTree.size());
        assertNull(avlTree.search(new NodeData<>(time2, 0)));
    }

    @Test
    public void testDeleteNonExistentNode() {
        LocalDateTime time2 = LocalDateTime.of(2023, 5, 2, 15, 0);
        NodeData<Integer> second = new NodeData<>(time2, 12);

        avlTree.delete(second);

        assertEquals(3, avlTree.size());
        assertEquals(Integer.valueOf(10), avlTree.search(new NodeData<>(time1, 10)).getValue());
    }

    @Test
    public void testDeleteRootNode() {
       AVLTree<NodeData<Integer>> avlTree = new AVLTree<>();
       LocalDateTime time1 = LocalDateTime.of(2023, 5, 1, 10, 0);

       NodeData<Integer> first = new NodeData<>(time1, 10);
       avlTree.insert(first);

       avlTree.delete(first);

       assertEquals(0, avlTree.size());
       assertNull(avlTree.search(first));
    }

    @Test
    public void testCeilingRecycledItem() {
       AVLTree<RecycledItem> recycledItemAVLTree = new AVLTree<>();

       List<RecycledItem> recycledItems = Arrays.asList(
               new RecycledItem(3001, "Plastic Bottle", "Coca-Cola", "PET", 0.2),
               new RecycledItem(4001, "Aluminum Can", "Pepsi", "Aluminum", 0.1),
               new RecycledItem(5001, "Glass Jar", "Heinz", "Glass", 0.4),
               new RecycledItem(8001, "Glass Jar", "Beans", "Glass", 0.4),
               new RecycledItem(6001, "Cardboard Box", "Amazon", "Cardboard", 0.8),
               new RecycledItem(7001, "Paper Bag", "Whole Foods", "Paper", 0.3)
       );

        for (RecycledItem recycledItem: recycledItems) {
            recycledItemAVLTree.insert(recycledItem);
        }

        RecycledItem ceiling = recycledItemAVLTree.ceiling(new RecycledItem(0, "glass jar", "heinz", "glass", 0.0));
        assertEquals(ceiling, recycledItems.get(2));
    }

    @Test
    public void testRotation() {
       AVLTree<Integer> integerAVLTree = new AVLTree<>();

        for (int i = 0; i < 5; i++) {
            integerAVLTree.insert(i);
        }

        assertEquals(integerAVLTree.traverse(), Arrays.asList(0, 1, 2, 3, 4));
    }

}
