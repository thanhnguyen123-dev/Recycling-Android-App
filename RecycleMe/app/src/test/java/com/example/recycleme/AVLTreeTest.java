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
    private AVLTree<Integer> avlTree;

    @Before
    public void setUp() {
        avlTree = new AVLTree<>();
    }

    @Test
    public void testInsert() {
        LocalDateTime time1 = LocalDateTime.of(2023, 5, 1, 10, 0);
        LocalDateTime time2 = LocalDateTime.of(2023, 5, 2, 12, 0);
        LocalDateTime time3 = LocalDateTime.of(2023, 5, 3, 14, 0);

        avlTree.insert(time1, 10);
        avlTree.insert(time2, 12);
        avlTree.insert(time3, 14);

        assertEquals(3, avlTree.size());
    }

    @Test
    public void testSearch() {
        LocalDateTime time1 = LocalDateTime.of(2023, 5, 1, 10, 0);
        LocalDateTime time2 = LocalDateTime.of(2023, 5, 2, 12, 0);
        LocalDateTime time3 = LocalDateTime.of(2023, 5, 3, 14, 0);

        avlTree.insert(time1, 10);
        avlTree.insert(time2, 12);
        avlTree.insert(time3, 14);

        int value1 = avlTree.search(time1);
        int value2 = avlTree.search(time2);
        int value3 = avlTree.search(time3);

        assertEquals(10, value1);
        assertEquals(12, value2);
        assertEquals(14, value3);
    }

    @Test
    public void testTraverseAndReturnDataWithTime() {
        LocalDateTime time1 = LocalDateTime.of(2023, 5, 1, 10, 0);
        LocalDateTime time2 = LocalDateTime.of(2023, 5, 2, 12, 0);
        LocalDateTime time3 = LocalDateTime.of(2023, 5, 3, 14, 0);

        avlTree.insert(time2, 12);
        avlTree.insert(time1, 10);
        avlTree.insert(time3, 14);

        List<NodeData<Integer>> nodeDataList = avlTree.traverseAndReturnDataWithTime();

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
        LocalDateTime time1 = LocalDateTime.of(2023, 5, 1, 10, 0);
        LocalDateTime time2 = LocalDateTime.of(2023, 5, 1, 10, 0);

        avlTree.insert(time1, 10);
        avlTree.insert(time2, 20);

        assertEquals(1, avlTree.size());
        assertEquals(Integer.valueOf(10), avlTree.search(time1));
    }

    @Test
    public void testSearchNonExistentNode() {
        LocalDateTime time1 = LocalDateTime.of(2023, 5, 1, 10, 0);
        LocalDateTime time2 = LocalDateTime.of(2023, 5, 2, 12, 0);

        avlTree.insert(time1, 10);

        Integer value = avlTree.search(time2);

        assertNull(value);
    }

    @Test
    public void testTraverseEmptyTree() {
        List<NodeData<Integer>> nodeDataList = avlTree.traverseAndReturnDataWithTime();

        assertTrue(nodeDataList.isEmpty());
    }

    @Test
    public void testDelete() {
        LocalDateTime time1 = LocalDateTime.of(2023, 5, 1, 10, 0);
        LocalDateTime time2 = LocalDateTime.of(2023, 5, 2, 12, 0);
        LocalDateTime time3 = LocalDateTime.of(2023, 5, 3, 14, 0);

        avlTree.insert(time1, 10);
        avlTree.insert(time2, 12);
        avlTree.insert(time3, 14);

        avlTree.delete(time2);

        assertEquals(2, avlTree.size());
        assertNull(avlTree.search(time2));
    }

    @Test
    public void testDeleteNonExistentNode() {
        LocalDateTime time1 = LocalDateTime.of(2023, 5, 1, 10, 0);
        LocalDateTime time2 = LocalDateTime.of(2023, 5, 2, 12, 0);

        avlTree.insert(time1, 10);

        avlTree.delete(time2);

        assertEquals(1, avlTree.size());
        assertEquals(Integer.valueOf(10), avlTree.search(time1));
    }

    @Test
    public void testDeleteRootNode() {
        LocalDateTime time1 = LocalDateTime.of(2023, 5, 1, 10, 0);

        avlTree.insert(time1, 10);

        avlTree.delete(time1);

        assertEquals(0, avlTree.size());
        assertNull(avlTree.search(time1));
    }

    @Test
    public void findBetween() {
        LocalDateTime time1 = LocalDateTime.of(2023, 5, 1, 10, 0);
        LocalDateTime time2 = LocalDateTime.of(2023, 5, 2, 12, 0);
        LocalDateTime time3 = LocalDateTime.of(2023, 5, 2, 14, 0);
        LocalDateTime time4 = LocalDateTime.of(2023, 5, 3, 15, 0);

        avlTree.insert(time1, 10);
        avlTree.insert(time2, 12);
        avlTree.insert(time3, 14);
        avlTree.insert(time4, 15);


        List<NodeData<Integer>> betweenList = avlTree.findBetween(LocalDateTime.of(2023, 5, 2, 1, 1),
                LocalDateTime.of(2023, 5, 3, 1, 1));

        assertEquals(Integer.valueOf(12), betweenList.get(0).getValue());
        assertEquals(Integer.valueOf(14), betweenList.get(0).getValue());
    }

}
