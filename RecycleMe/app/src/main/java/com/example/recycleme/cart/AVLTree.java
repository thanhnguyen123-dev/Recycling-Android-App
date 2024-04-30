package com.example.recycleme.cart;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AVLTree<T> {
    private class Node {
        LocalDateTime time;
        T value;
        Node left, right;
        int height;

        Node(LocalDateTime time, T value) {
            this.time = time;
            this.value = value;
            this.left = null;
            this.right = null;
            this.height = 0;
        }

        public LocalDateTime getTime() {
            return time;
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

        public void setHeight(int height) {
            this.height = height;
        }

    }

    private Node root;
    private int size;

    public AVLTree() {
        root = null;
        size = 0;
    }

    public void insert(LocalDateTime time, T value) {
        Node newNode = new Node(time, value);
        if (containsTime(root, time)) return;
        root = insertInternal(root, newNode);
        this.size++;
    }

    private Node insertInternal(Node tree, Node node) {
        if (tree == null) {
            tree =  node;
            return tree;
        }
        if (node.getTime().compareTo(tree.getTime()) < 0) {
            tree.left = insertInternal(tree.left, node);
        }
        else if (node.getTime().compareTo(tree.getTime()) > 0) {
            tree.right = insertInternal(tree.right, node);
        }
        else return tree;
        updateHeight(node);
        return applyRotation(tree);
    }

    private boolean containsTime(Node tree, LocalDateTime time) {
        if (tree == null) return false;
        int compare = time.compareTo(tree.getTime());
        if (compare == 0) return true;
        else if (compare < 0) {
            return containsTime(tree.left, time);
        }
        else return containsTime(tree.right, time);
    }

    private void updateHeight(Node node) {
        int maxHeight = Math.max(calculateHeight(node.left), calculateHeight(node.right));
        node.setHeight(maxHeight + 1);
    }

    private int calculateHeight(Node node) {
        if (node == null) return -1;
        else return node.getHeight();
    }

    private Node applyRotation(Node node) {
        int balanceFactor = getBalanceFactor(node);

        // left heavy
        if (balanceFactor > 1) {
            // check for left-right situation
            if (getBalanceFactor(node.left) < 0) {
                Node newLeftNode = rotateLeft(node.left);
                node.left = newLeftNode;
            }
            return rotateRight(node);
        }

        // right heavy
        if (balanceFactor < -1) {
            // check for right-left situation
            if (getBalanceFactor(node.right) > 0) {
                Node newRightNode = rotateRight(node.right);
                node.right = newRightNode;
            }
            return rotateLeft(node);
        }

        return node;
    }

    private Node rotateRight(Node node) {
        Node leftNode = node.getLeft();
        Node extraNode = leftNode.getRight();
        leftNode.right = node;
        node.left = extraNode;
        updateHeight(node);
        updateHeight(leftNode);
        return leftNode;
    }

    private Node rotateLeft(Node node) {
        Node rightNode = node.getRight();
        Node extraNode = rightNode.getLeft();
        rightNode.left = node;
        node.right = extraNode;
        updateHeight(node);
        updateHeight(rightNode);
        return rightNode;
    }

    private int getBalanceFactor(Node node) {
        if (node == null) {
            return 0;
        }
        int leftHeight = (node.left != null) ? node.left.getHeight() : -1;
        int rightHeight = (node.right != null) ? node.right.getHeight() : -1;
        return leftHeight - rightHeight;
    }


    public T search(LocalDateTime time) {
        Node node = search(root, time);
        return (node == null) ? null : node.value;
    }

    private Node search(Node tree, LocalDateTime time) {
        if (tree == null || tree.getTime().equals(time)) {
            return tree;
        }
        if (time.compareTo(tree.getTime()) < 0) {
            return search(tree.left, time);
        }
        else {
            return search(tree.right, time);
        }
    }

    public List<NodeData<T>> traverseAndReturnDataWithTime() {
        List<NodeData<T>> list = new ArrayList<>();
        flattenTree(root, list);
        return list;
    }

    private void flattenTree(Node tree, List<NodeData<T>> list) {
        if (tree != null) {
            flattenTree(tree.left, list);
            NodeData<T> data = new NodeData<>(tree.getTime(), tree.getValue());
            list.add(data);
            flattenTree(tree.right, list);
        }
    }

    public void delete(LocalDateTime time) {
        if (!containsTime(root, time)) return;
        root = deleteInternal(root, time);
        size--;
    }

    private Node deleteInternal(Node tree, LocalDateTime time) {
        if (tree == null) return null;
        if (time.compareTo(tree.getTime()) < 0) {
            tree.left = deleteInternal(tree.left, time);
        }
        else if (time.compareTo(tree.getTime()) > 0) {
            tree.right = deleteInternal(tree.right, time);
        }
        else {
            if (tree.getLeft() == null) {
                return tree.getRight();
            }
            else if (tree.getRight() == null) {
                return tree.getLeft();
            }
            else {
                tree.time = getEarliestTime(tree.right);
                tree.right = deleteInternal(tree.right, tree.getTime());
            }
        }
        updateHeight(tree);
        return applyRotation(tree);
    }

    private LocalDateTime getLatestTime() {
        if (root == null) return null;
        return this.getLatestTime(root);
    }

    private LocalDateTime getLatestTime(Node tree) {
        if (tree.getRight() != null) {
            return getLatestTime(tree.right);
        }
        return tree.getTime();
    }

    private LocalDateTime getEarliestTime(Node tree) {
        if (tree.getLeft() != null) {
            return getEarliestTime(tree.left);
        }
        return tree.getTime();
    }

    public int size() {
        return size;
    }

    /**
    The findBetween method takes two parameters: startTime and endTime, representing
    the start and end time range (inclusive) within which we want to find the nodes.
     */
    public List<NodeData<T>> findBetween(LocalDateTime startTime, LocalDateTime endTime) {
        List<NodeData<T>> list = traverseAndReturnDataWithTime();
        List<NodeData<T>> betweenList = new ArrayList<>();
        for (NodeData<T> nodeData : list) {
            int compareToStart = nodeData.getDateTime().compareTo(startTime);
            int compareToEnd = nodeData.getDateTime().compareTo(endTime);
            if (compareToStart >= 0 && compareToEnd <= 0) {
                betweenList.add(nodeData);
            }
        }
        return betweenList;
    }

}