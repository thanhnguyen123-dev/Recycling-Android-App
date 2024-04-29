package com.example.recycleme.cart;

import com.example.recycleme.cart.NodeData;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class AVLTree<T> {

    private class Node {
        private LocalDateTime key;
        private T value;
        private Node left, right;
        private int height;

        Node(LocalDateTime key, T value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.height = 0;
        }

        public LocalDateTime getKey() {
            return key;
        }

        public T getValue() {
            return value;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }

        public int getHeight() {
            return height;
        }
    }

    private Node root;
    private int size;

    public AVLTree() {
        root = null;
        size = 0;
    }

    public void insert(LocalDateTime key, T value) {
        // TODO: Implement the insert method
    }

    private Node insertInternal(Node tree, Node node) {
        if (tree == null) {
            return node;
        }
        if (node.getKey().compareTo(tree.getKey()) < 0) {
            tree = insertInternal(tree.left, node);
        }
        else if (node.getKey().compareTo(tree.getKey()) > 0) {
            tree = insertInternal(tree.right, node);
        }
        else return tree;

        return applyRotation(tree);
    }

    private Node applyRotation(Node tree) {
        return null;
    }






    public T search(LocalDateTime key) {
        // TODO: Implement the search method
        return null;
    }

    public List<NodeData<T>> traverseAndReturnDataWithTime() {
        // TODO: Implement the traverseAndReturnDataWithTime method
        return null;
    }

    public void delete(LocalDateTime key) {
        // TODO: Implement the delete method
    }

    public int size() {
        return size;
    }

    /*
    The findBetween method takes two parameters: startTime and endTime, representing
    the start and end time range (inclusive) within which we want to find the nodes.
     */
    public List<NodeData<T>> findBetween(LocalDateTime startTime, LocalDateTime endTime) {
        // TODO: Implement the findBetween method

        return null;
    }

    public static void main(String[] args) {

    }
}