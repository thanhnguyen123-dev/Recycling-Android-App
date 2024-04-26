package com.example.recycleme.cart;

import androidx.annotation.NonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RedBlackTree<T> {

    public enum Color {
        RED, BLACK
    }

    public class Node {
        LocalDateTime key;
        T value;
        Node left, right, parent;
        Color color;

        Node(LocalDateTime key, T value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.parent = null;
            this.color = Color.RED; // New nodes are red by default
        }

        @NonNull
        @Override
        public String toString() {
            String leftValue = left == null ? "null" : left.value.toString();
            String rightValue = right == null ? "null" : right.value.toString();
            String nodeColor = color == Color.RED ? "Red" : "Black";
            return "Node{value = " + value + " , color=" + nodeColor + ", leftValue=" + leftValue + ", rightValue=" + rightValue + "}";
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

        public Node getParent() {
            return parent;
        }

        public Color getColor() {
            return color;
        }

        public void flipColor() {
            if (this.color == Color.RED) {
                this.color = Color.BLACK;
            } else {
                this.color = Color.RED;
            }
        }
    }

    public Node root;

    public RedBlackTree() {
        root = null;
    }

    public void insert(LocalDateTime key, T value) {
        Node nodeToInsert = new Node(key, value);

        root = insert(root, nodeToInsert);
        fixRecolorRotate(nodeToInsert);
    }

    public Node insert(Node node, Node nodeToInsert) {
        // we go left and right based on the time

        if (node == null) {
            return nodeToInsert;
        }

        if (nodeToInsert.getKey().compareTo(node.getKey()) < 0) {
            // if the node to insert is earlier than parent node, go left
            node.left = insert(node.left, nodeToInsert);
            node.left.parent = node;
        } else if (nodeToInsert.getKey().compareTo(node.getKey()) > 0) {
            // go right
            node.right = insert(node.right, nodeToInsert);
            node.right.parent = node;
        }

        return node;
    }

    private void rotateLeft(Node currentNode) {
        Node rightNode = currentNode.getRight();
        currentNode.right = rightNode.getLeft();

        // we want to set the parent of the currentNode.right to point out current node
        // but it can be null, so we check first

        if (currentNode.right != null) {
            currentNode.right.parent = currentNode;
        }

        rightNode.left = currentNode;
        rightNode.parent = currentNode.parent;

        // must update the kid of the currentnode parent
        if (currentNode.parent == null) {
            // this means the currentNode was a root, and now the root must be changed to rightNode
            root = rightNode;
        } else if (currentNode == currentNode.parent.left) {
            // if node is leftChild, then we get the parent node and set the left child of it
            // as the current node

            currentNode.parent.left = rightNode;
        } else {
            // vice versa
            currentNode.parent.right = rightNode;
        }
    }

    private void rotateRight(Node currentNode) {
        Node leftNode = currentNode.getLeft();
        currentNode.left = leftNode.getRight();

        // we want to set the parent of the currentNode.right to point out current node
        // (we have just set it as kid of current node)
        // but it can be null, so we check first

        if (currentNode.left != null) {
            currentNode.left.parent = currentNode;
        }

        leftNode.right = currentNode;
        leftNode.parent = currentNode.parent;

        // must update the kid of the currentnode parent
        if (currentNode.parent == null) {
            // this means the currentNode was a root, and now the root must be changed to rightNode
            root = leftNode;
        } else if (currentNode == currentNode.parent.left) {
            // if node is leftChild, then we get the parent node and set the left child of it
            // as the current node

            currentNode.parent.left = leftNode;
        } else {
            // vice versa
            currentNode.parent.right = leftNode;
        }
    }

    private void fixRecolorRotate(Node newNode) {
        // we can do this by two ways: using while loop or using recursion
        Node currentNode = newNode;

        while (currentNode != root && currentNode.getParent().getColor() == Color.RED) {
            // we enter a while loop that continues as long as current Node is not the root and parent is red
            // remember one of the chars of red black tree is that a parent should be red

            Node grandparent = currentNode.getParent().getParent();

            // check if the currentNode parent is the left child of its grandparent
            if (currentNode.getParent() == grandparent.getLeft()) {
                // if it is then the uncle is right child of grandparent
                Node uncle = grandparent.getRight();
                // now check if the uncle is not null and the color of the uncle is red
                // if the color of the uncle is red then all we need to do is flip the color of the parent, uncle,
                // and child

                if (uncle != null && uncle.color == Color.RED) {
                    currentNode.getParent().flipColor();
                    uncle.flipColor();
                    grandparent.flipColor();
                    currentNode = currentNode.getParent().getParent();
                } else {
                    // we know that this needs a rotation, but first we need to check if it's a LR or LL
                    // check if the currentNode is right

                    if (currentNode == currentNode.getParent().getRight()) {
                        // if currentNode is right, we need to rotate left
                        currentNode = currentNode.parent;
                        rotateLeft(currentNode);
                    }

                    currentNode.getParent().flipColor();
                    grandparent.flipColor();
                    rotateRight(grandparent);
                }
            } else {
                // same, but change left and right
                Node uncle = grandparent.getLeft();

                if (uncle != null && uncle.color == Color.RED) {
                    currentNode.getParent().flipColor();
                    uncle.flipColor();
                    grandparent.flipColor();
                    currentNode = currentNode.getParent().getParent();
                } else {
                    // we know that this needs a rotation, but first we need to check if it's a LR or LL
                    // check if the currentNode is right

                    if (currentNode == currentNode.getParent().getLeft()) {
                        // if currentNode is right, we need to rotate left
                        currentNode = currentNode.parent;
                        rotateRight(currentNode);
                    }

                    currentNode.getParent().flipColor();
                    grandparent.flipColor();
                    rotateLeft(grandparent);
                }
            }
        }

        root.color = Color.BLACK;
    }



    public void traverse() {
        traversePreOrder(root);
    }
    private void traversePreOrder(Node node) {
        if (node != null) {
            System.out.println(node);
            traversePreOrder(node.left);
            traversePreOrder(node.right);
        }
    }
    public List<NodeData<T>> traverseAndReturnDataWithTime() {
        List<NodeData<T>> nodeDataList = new ArrayList<>();
        traverseInOrderDescendingReturnData(root, nodeDataList);
        return nodeDataList;
    }

    private void traverseInOrderDescendingReturnData(Node node, List<NodeData<T>> returnedData) {
        if (node != null) {
            traverseInOrderDescendingReturnData(node.right, returnedData);
            NodeData<T> currentData = new NodeData<>(node.getKey(), node.getValue());
            returnedData.add(currentData);
            traverseInOrderDescendingReturnData(node.left, returnedData);
        }
    }

    public T search(LocalDateTime dateTime) {
        Node node = search(root, dateTime);
        return node == null? null: node.value;
    }

    private Node search(Node currentNode, LocalDateTime time) {
        if (currentNode == null || currentNode.getKey().equals(time)) {
            return currentNode;
        }

        int compare = time.compareTo(currentNode.getKey());
        if (compare > 0) {
            return search(currentNode.getRight(), time);
        } else {
            return search(currentNode.getLeft(), time);
        }
    }

    public void delete(LocalDateTime dateTime) {
        //TODO
    }

}