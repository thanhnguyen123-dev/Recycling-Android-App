package com.example.recycleme.treedb;


public class RedBlackTree<T extends Comparable<T>> {

    class Node<T> {
        T value;
        Node<T> left, right, parent;
        boolean isLeft;
        boolean isBlack;
        public Node(T value) {
            this.value = value;
            this.left = this.right = this.parent = null;
            isBlack = false;
            isLeft = false;
        }

    }
    int size = 0;
    Node<T> root;
    private boolean moreThan(T x, T y) {
        return x.compareTo(y) > 0;
    }

    public Node<T> search(T value) {
        return searchInternal(root, value);
    }

    private Node<T> searchInternal(Node<T> tree, T value) {
        if (tree.value == value) {
            return tree;
        }
        else if (moreThan(value, tree.value)) {
            return searchInternal(tree.right, value);
        }
        else {
            return searchInternal(tree.left, value);
        }

    }


    public void add(T value) {
        Node<T> newNode = new Node<>(value);
        if (root == null) {
            root = newNode;
            root.isBlack = true;
            size++;
        }
        addInternal(root, newNode);
    }

    private void addInternal(Node<T> parent, Node<T> newNode) {
        if (parent.value.compareTo(newNode.value) == 0) {
            return;
        }
        else if (moreThan(newNode.value, parent.value)) {
            if (parent.right == null) {
                parent.right = newNode;
                newNode.parent = parent;
                newNode.isLeft = false;
            }
            else {
                addInternal(parent.right, newNode);
            }
        }
        else {
            if (parent.left == null) {
                parent.left = newNode;
                newNode.parent = parent;
                newNode.isLeft = true;
            }
            else {
                addInternal(parent.left, newNode);
            }
        }
        checkColor(newNode);
    }

    private void checkColor(Node<T> node) {
        if (node == root) {
            return;
        }
        if (!node.isBlack && !node.parent.isBlack) {
            correctTree(node);
        }
        checkColor(node.parent);

    }

    private void correctTree(Node<T> node) {
        if (node.parent.isLeft) {
            // uncle is node.parent.parent.right
            if (node.parent.parent.right == null || node.parent.parent.right.isBlack) {
                rotate(node);
            }
            if (node.parent.parent.right != null) {
                node.parent.parent.right.isBlack = true;
            }
        }
        else {
            if (node.parent.parent.left == null || node.parent.parent.left.isBlack) {
                rotate(node);
            }
            if (node.parent.parent.left != null) {
                node.parent.parent.left.isBlack = true;
            }
        }
        node.parent.parent.isBlack = false;
        node.parent.isBlack = true;

    }

    private void rotate(Node<T> node) {

        if (node.isLeft) {
            // left-left
            if (node.parent.isLeft) {
                rightRotate(node.parent.parent);
                node.isBlack = false;
                node.parent.isBlack = true;
                if (node.parent.right != null) {
                    node.parent.right.isBlack = false;
                }
            }
            else {
                // left-right
                rightLeftRotate(node.parent.parent);
                node.isBlack = true;
                node.left.isBlack = false;
                node.right.isBlack = false;
            }
        }

        else {
            // right-right
            if (!node.parent.isLeft) {
                leftRotate(node.parent.parent);
                node.isBlack = false;
                node.parent.isBlack = true;
                if (node.parent.left != null) {
                    node.parent.left.isBlack = false;
                }
            }
            //right-left
            else {
                leftRightRotate(node.parent.parent);
                node.isBlack = true;
                node.left.isBlack = false;
                node.right.isBlack = true;
            }
        }

    }


    private void leftRotate(Node<T> node) {
        Node<T> tmp = node.right;
        node.right = tmp.left;
        if (node.right != null) {
            node.right.parent = node;
            node.right.isLeft = false;
        }
        if (node.parent == null) {
            root = tmp;
            tmp.parent = null;
        }
        else {
            tmp.parent = node.parent;
            if (node.isLeft) {
                tmp.isLeft = true;
                tmp.parent.left = tmp;
            }
            else {
                tmp.isLeft = false;
                tmp.parent.right = tmp;
            }
        }
        tmp.left = node;
        node.isLeft = true;
        node.parent = tmp;
    }

    //
    private void rightRotate(Node<T> node) {
        Node<T> tmp = node.left;
        node.left = tmp.right;
        if (node.left != null) {
            node.left.parent = node;
            node.left.isLeft = true;
        }
        if (node.parent == null) {
            root = tmp;
            tmp.parent = null;
        }
        else {
            tmp.parent = node.parent;
            if (node.isLeft) {
                tmp.isLeft = true;
                tmp.parent.left = tmp;
            }
            else {
                tmp.isLeft = false;
                tmp.parent.right = tmp;
            }
        }
        tmp.right = node;
        node.isLeft = false;
        node.parent = tmp;
    }

    private void leftRightRotate(Node<T> node) {
        leftRotate(node.left);
        rightRotate(node);
    }

    private void rightLeftRotate(Node<T> node) {
        rightRotate(node.right);
        leftRotate(node);

    }




}

