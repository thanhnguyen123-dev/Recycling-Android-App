package com.example.recycleme;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.example.recycleme.model.RecycledItem;
import com.example.recycleme.util.tree.AVLTree;
import com.example.recycleme.cart.NodeData;

import org.checkerframework.checker.units.qual.N;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

/**
 * Unit test to see how AVLTree is working with NodeData
 * @author Julius Liem
 */
public class AVLTreeTest {
    private AVLTree<NodeData<Integer>> avlTree;

    private LocalDateTime time1;
    private LocalDateTime time2;
    private LocalDateTime time3;
    private NodeData<Integer> firstNode;
    private NodeData<Integer> secondNode;
    private NodeData<Integer> thirdNode;

   @Before
    public void setUp() {
       avlTree = new AVLTree<>();
       time1 = LocalDateTime.of(2024, 5, 1, 10, 0);
       time2 = LocalDateTime.of(2024, 5, 2, 12, 0);
       time3 = LocalDateTime.of(2024, 5, 3, 14, 0);

       firstNode = new NodeData<>(time1, 10);
       secondNode = new NodeData<>(time2, 12);
       thirdNode = new NodeData<>(time3, 14);

       avlTree.insert(firstNode);
       avlTree.insert(secondNode);
       avlTree.insert(thirdNode);
   }

    @Test
    public void testEmptyTreeSize() {
       AVLTree<Integer> integerAVLTree = new AVLTree<>();
       assertEquals(0, integerAVLTree.size());
    }

    @Test
    public void testAddDuplicateItem() {
       avlTree.insert(firstNode);

       // should not increase the size
        assertEquals(3, avlTree.size());
    }

    @Test
    public void testFindBetween() {
       List<NodeData<Integer>> findBetween2024 = avlTree.findBetween(new NodeData<>(time1.plusHours(1), 0), new NodeData<>(time3.minusHours(1), 0) );

       // should return the second one only
        assertEquals(findBetween2024.get(0), secondNode);
    }

    @Test
    public void testDeleteRootNode() {
       avlTree.delete(firstNode);
       assertEquals(avlTree.size(), 2);

       assertNull(avlTree.search(firstNode));
       assertNotNull(avlTree.search(secondNode));
       assertNotNull(avlTree.search(thirdNode));
    }

    @Test
    public void testDeleteNonExistentNode() {
        NodeData<Integer> currentTime = new NodeData<>(LocalDateTime.now(), 0);
        avlTree.delete(currentTime);

        assertEquals(3, avlTree.size());
        assertNotNull(avlTree.search(firstNode));
        assertNotNull(avlTree.search(secondNode));
        assertNotNull(avlTree.search(thirdNode));
    }

    @Test
    public void testFloor() {
        LocalDateTime time1minus1 = time1.minusHours(1);
        NodeData<Integer> time1Node = new NodeData<>(time1minus1, 0);

        assertEquals(avlTree.floor(time1Node), firstNode);
    }

    @Test
    public void testFloorEmptyTree() {
        AVLTree<Integer> newAVLTree = new AVLTree<>();

        assertNull(newAVLTree.floor(2));
    }

    @Test
    public void testCeilingEmptyTree() {
        AVLTree<Integer> newAVLTree = new AVLTree<>();

        assertNull(newAVLTree.ceiling(2));
    }

    @Test
    public void testFindBetweenEmptyTree() {
        AVLTree<Integer> newAVLTree = new AVLTree<>();

        assertTrue(newAVLTree.findBetween(2, 4).isEmpty());
    }

    @Test
    public void testFloorSingleItem() {
        this.avlTree.delete(firstNode);
        this.avlTree.delete(secondNode);

        assertEquals(this.avlTree.floor(secondNode), thirdNode);
    }

    @Test
    public void testCeilingSingleItem() {
        this.avlTree.delete(firstNode);
        this.avlTree.delete(secondNode);

        assertEquals(this.avlTree.ceiling(secondNode), thirdNode);
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
    public void testTraverse() {
       AVLTree<Integer> integerAVLTree = new AVLTree<>();

        for (int i = 0; i < 5; i++) {
            integerAVLTree.insert(i);
        }

        assertEquals(integerAVLTree.traverse(), Arrays.asList(0, 1, 2, 3, 4));
    }

}
