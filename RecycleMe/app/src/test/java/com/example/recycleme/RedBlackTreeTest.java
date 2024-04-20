package com.example.recycleme;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.example.recycleme.treedb.RedBlackTree;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

public class RedBlackTreeTest {
    private RedBlackTree<Integer> rbTree;

    @Before
    public void setUp() {
        rbTree = new RedBlackTree<>();
        rbTree.add(LocalDateTime.of(2024, 10, 2, 11, 0), 11);
        rbTree.add(LocalDateTime.of(2024, 10, 2, 10, 0), 10);
        rbTree.add(LocalDateTime.of(2024, 10, 2, 13, 0), 13);
        rbTree.add(LocalDateTime.of(2024, 10, 2, 12, 0), 12);
        rbTree.add(LocalDateTime.of(2024, 10, 2, 14, 0), 14);
        rbTree.add(LocalDateTime.of(2024, 10, 2, 16, 0), 16);
    }

    @Test
    public void testRBTreeStructure() {
        RedBlackTree<Integer>.Node root = rbTree.root;

        assertNotNull("Root should not be null", root);
        assertEquals("Root value should be 11", Integer.valueOf(11), root.value);
        assertEquals("Root color should be BLACK", RBTree.Color.BLACK, root.color);
        assertEquals("Root left value should be 10", Integer.valueOf(10), root.left.value);
        assertEquals("Root right value should be 13", Integer.valueOf(13), root.right.value);

        RBTree<Integer>.Node left = root.left;
        assertNotNull("Left node should not be null", left);
        assertEquals("Left node color should be BLACK", RBTree.Color.BLACK, left.color);
        assertNull("Left node left child should be null", left.left);
        assertNull("Left node right child should be null", left.right);

        RBTree<Integer>.Node right = root.right;
        assertNotNull("Right node should not be null", right);
        assertEquals("Right node color should be RED", RBTree.Color.RED, right.color);
        assertEquals("Right node left value should be 12", Integer.valueOf(12), right.left.value);
        assertEquals("Right node right value should be 14", Integer.valueOf(14), right.right.value);

        RBTree<Integer>.Node rightLeft = right.left;
        assertNotNull("Right left node should not be null", rightLeft);
        assertEquals("Right left node color should be BLACK", RBTree.Color.BLACK, rightLeft.color);
        assertNull("Right left node left child should be null", rightLeft.left);
        assertNull("Right left node right child should be null", rightLeft.right);

        RBTree<Integer>.Node rightRight = right.right;
        assertNotNull("Right right node should not be null", rightRight);
        assertEquals("Right right node color should be BLACK", RBTree.Color.BLACK, rightRight.color);
        assertEquals("Right right node right value should be 16", Integer.valueOf(16), rightRight.right.value);
        assertNull("Right right node left child should be null", rightRight.left);

        RBTree<Integer>.Node rightRightRight = rightRight.right;
        assertNotNull("Right right right node should not be null", rightRightRight);
        assertEquals("Right right right node color should be RED", RBTree.Color.RED, rightRightRight.color);
        assertNull("Right right right node left child should be null", rightRightRight.left);
        assertNull("Right right right node right child should be null", rightRightRight.right);
    }
}
