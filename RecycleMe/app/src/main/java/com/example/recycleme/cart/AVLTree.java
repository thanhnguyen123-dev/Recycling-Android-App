package com.example.recycleme.cart;

import com.example.recycleme.cart.NodeData;

import java.time.LocalDateTime;
import java.util.List;

public class AVLTree<T> {

    private class Node {
        // TODO: Add necessary fields (e.g., key, value, left, right, height)

        // TODO: Implement the constructor

        // TODO: Implement the necessary methods (e.g., getters, setters)
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
}