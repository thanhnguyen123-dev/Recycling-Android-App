package com.example.recycleme;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import com.example.recycleme.cart.RedBlackTree;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

public class RedBlackTreeTest {
    private RedBlackTree<Integer> rbTree;

    @Before
    public void setUp() {
        rbTree = new RedBlackTree<>();
        rbTree.insert(LocalDateTime.of(2024, 10, 2, 11, 0), 11);
        rbTree.insert(LocalDateTime.of(2024, 10, 2, 10, 0), 10);
        rbTree.insert(LocalDateTime.of(2024, 10, 2, 13, 0), 13);
        rbTree.insert(LocalDateTime.of(2024, 10, 2, 12, 0), 12);
        rbTree.insert(LocalDateTime.of(2024, 10, 2, 14, 0), 14);
        rbTree.insert(LocalDateTime.of(2024, 10, 2, 16, 0), 16);
    }

    @Test
    public void testRBTreeStructure() {
        RedBlackTree<Integer>.Node root = rbTree.root;

        assertNotNull("Root should not be null", root);
        assertEquals("Root value should be 11", Integer.valueOf(11), root.getValue());
        assertEquals("Root color should be BLACK", RedBlackTree.Color.BLACK, root.getColor());
        assertEquals("Root left value should be 10", Integer.valueOf(10), root.getLeft().getValue());
        assertEquals("Root right value should be 13", Integer.valueOf(13), root.getRight().getValue());

        RedBlackTree<Integer>.Node left = root.getLeft();
        assertNotNull("Left node should not be null", left);
        assertEquals("Left node color should be BLACK", RedBlackTree.Color.BLACK, left.getColor());
        assertNull("Left node left child should be null", left.getLeft());
        assertNull("Left node right child should be null", left.getRight());

        RedBlackTree<Integer>.Node right = root.getRight();
        assertNotNull("Right node should not be null", right);
        assertEquals("Right node color should be RED", RedBlackTree.Color.RED, right.getColor());
        assertEquals("Right node left value should be 12", Integer.valueOf(12), right.getLeft().getValue());
        assertEquals("Right node right value should be 14", Integer.valueOf(14), right.getRight().getValue());

        RedBlackTree<Integer>.Node rightLeft = right.getLeft();
        assertNotNull("Right left node should not be null", rightLeft);
        assertEquals("Right left node color should be BLACK", RedBlackTree.Color.BLACK, rightLeft.getColor());
        assertNull("Right left node left child should be null", rightLeft.getLeft());
        assertNull("Right left node right child should be null", rightLeft.getRight());

        RedBlackTree<Integer>.Node rightRight = right.getRight();
        assertNotNull("Right right node should not be null", rightRight);
        assertEquals("Right right node color should be BLACK", RedBlackTree.Color.BLACK, rightRight.getColor());
        assertEquals("Right right node right value should be 16", Integer.valueOf(16), rightRight.getRight().getValue());
        assertNull("Right right node left child should be null", rightRight.getLeft());

        RedBlackTree<Integer>.Node rightRightRight = rightRight.getRight();
        assertNotNull("Right right right node should not be null", rightRightRight);
        assertEquals("Right right right node color should be RED", RedBlackTree.Color.RED, rightRightRight.getColor());
        assertNull("Right right right node left child should be null", rightRightRight.getLeft());
        assertNull("Right right right node right child should be null", rightRightRight.getRight());
    }

    @Test
    public void testSearch() {
        assertEquals( rbTree.search(LocalDateTime.of(2024, 10, 2, 16, 0)), Integer.valueOf(16));
    }
}
