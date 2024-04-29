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

        Node(LocalDateTime key, T value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }

        public LocalDateTime getKey() {
            return key;
        }

        public void setKey(LocalDateTime key) {
            this.key = key;
        }

        public T getValue() {
            return value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
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