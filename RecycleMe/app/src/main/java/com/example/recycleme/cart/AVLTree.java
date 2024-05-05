package com.example.recycleme.cart;
import java.util.ArrayList;
import java.util.List;

public class AVLTree<T extends Comparable<T>> {
    class Node {
        T value;
        Node left, right;
        int height;

        protected Node(T value) {
            this.value = value;
            this.left = null;
            this.right = null;
            this.height = 0;
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

    Node root;
    private int size;

    public AVLTree() {
        root = null;
        size = 0;
    }

    public void insert(T value) {
        Node newNode = new Node(value);
        if (containsValue(root, value)) return;
        root = insertInternal(root, newNode);
        this.size++;
    }

    private Node insertInternal(Node tree, Node node) {
        if (tree == null) {
            tree =  node;
            return tree;
        }
        if (node.getValue().compareTo(tree.getValue()) < 0) {
            tree.left = insertInternal(tree.left, node);
        }
        else if (node.getValue().compareTo(tree.getValue()) > 0) {
            tree.right = insertInternal(tree.right, node);
        }
        else return tree;
        updateHeight(tree);
        return applyRotation(tree);
    }

    private boolean containsValue(Node tree, T data) {
        if (tree == null) return false;
        int compare = data.compareTo(tree.getValue());
        if (compare == 0) return true;
        else if (compare < 0) {
            return containsValue(tree.left, data);
        }
        else return containsValue(tree.right, data);
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


    public T search(T data) {
        Node node = search(root, data);
        return (node == null) ? null : node.value;
    }

    private Node search(Node tree, T data) {
        if (tree == null || tree.getValue().equals(data)) {
            return tree;
        }
        if (data.compareTo(tree.getValue()) < 0) {
            return search(tree.left, data);
        }
        else {
            return search(tree.right, data);
        }
    }

    public T ceiling(T data) {
        Node node = ceiling(root, data);

        if (node == null) {
            return getLargestValue();
        }

        return node.getValue();
    }


    Node ceiling(Node tree, T data) {
        if (tree == null) {
            return null;
        }

        if (data.compareTo(tree.getValue()) == 0) {
            return tree;
        }

        if (data.compareTo(tree.getValue()) > 0) {
            return ceiling(tree.right, data);
        } else {
            Node leftCeiling = ceiling(tree.left, data);
            if (leftCeiling != null) {
                return leftCeiling;
            }

            return tree;
        }
    }

    public T floor(T data) {
        Node node = floor(root, data);
        if (node == null) {
            return getSmallestValue(root);
        }

        return node.getValue();
    }

    Node floor(Node tree, T data) {
        if (tree == null) {
            return null;
        }

        if (data.compareTo(tree.getValue()) == 0) {
            return tree;
        }

        if (data.compareTo(tree.getValue()) < 0) {
            return floor(tree.left, data);
        } else {
            Node rightFloor = floor(tree.right, data);
            if (rightFloor != null) {
                return rightFloor;
            }

            return tree;
        }
    }

    public List<T> traverse() {
        List<T> list = new ArrayList<>();
        flattenTree(root, list);
        return list;
    }

    private void flattenTree(Node tree, List<T> list) {
        if (tree != null) {
            flattenTree(tree.left, list);
            list.add(tree.getValue());
            flattenTree(tree.right, list);
        }
    }


    public void delete(T data) {
        if (!containsValue(root, data)) return;
        root = deleteInternal(root, data);
        size--;
    }

    private Node deleteInternal(Node tree, T data) {
        if (tree == null) return null;
        if (data.compareTo(tree.getValue()) < 0) {
            tree.left = deleteInternal(tree.left, data);
        }
        else if (data.compareTo(tree.getValue()) > 0) {
            tree.right = deleteInternal(tree.right, data);
        }
        else {
            if (tree.getLeft() == null) {
                return tree.getRight();
            }
            else if (tree.getRight() == null) {
                return tree.getLeft();
            }
            else {
                tree.value = getSmallestValue(tree.right);
                tree.right = deleteInternal(tree.right, tree.getValue());
            }
        }
        updateHeight(tree);
        return applyRotation(tree);
    }

    private T getLargestValue() {
        if (root == null) return null;
        return this.getLargestValue(root);
    }

    private T getLargestValue(Node tree) {
        if (tree.getRight() != null) {
            return getLargestValue(tree.right);
        }
        return tree.getValue();
    }

    private T getSmallestValue(Node tree) {
        if (tree.getLeft() != null) {
            return getSmallestValue(tree.left);
        }
        return tree.getValue();
    }

    public int size() {
        return size;
    }

    public List<T> findBetween(T floor, T ceiling) {
        List<T> result = new ArrayList<>();
        findBetweenInternal(root, floor, ceiling, result);
        return result;
    }

    private void findBetweenInternal(Node node, T floor, T ceiling, List<T> result) {
        if (node == null) {
            return;
        }

        if (floor.compareTo(node.getValue()) < 0) {
            findBetweenInternal(node.left, floor, ceiling, result);
        }

        if (floor.compareTo(node.getValue()) <= 0 && ceiling.compareTo(node.getValue()) >= 0) {
            result.add(node.getValue());
        }

        if (ceiling.compareTo(node.getValue()) > 0) {
            findBetweenInternal(node.right, floor, ceiling, result);
        }
    }


}