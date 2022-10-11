package com.tshangar.datastructures.tree.bst;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

/**
 * Binary Search Tree Node
 * @param <E> DataType of the element stored in the Node
 */
public class Node<E extends Comparable<E>> {
    private static final Logger logger = LoggerFactory.getLogger(Node.class);

    private E data;

    private Node<E> parent;
    private Node<E> left;
    private Node<E> right;

    /**
     * Constructor to create Node object
     */
    public Node() {
    }

    /**
     * Constructor to create Node object
     * @param data Data Element
     */
    public Node(E data) {
        this.data = data;
    }

    /**
     * Constructor to create Node object
     * @param data Data Element
     * @param parent Parent Node
     */
    public Node(E data, Node<E> parent) {
        this.data = data;
        this.parent = parent;
    }

    /**
     * Constructor to create Node object
     * @param data Data Element
     * @param parent Parent Node
     * @param left Left Node
     * @param right Right Node
     */
    public Node(E data, Node<E> parent, Node<E> left, Node<E> right) {
        this.data = data;
        this.parent = parent;
        this.left = left;
        this.right = right;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public Node<E> getParent() {
        return parent;
    }

    public void setParent(Node<E> parent) {
        this.parent = parent;
    }

    public Node<E> getLeft() {
        return left;
    }

    public void setLeft(Node<E> left) {
        this.left = left;
    }

    public Node<E> getRight() {
        return right;
    }

    public void setRight(Node<E> right) {
        this.right = right;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node<?> node)) return false;
        return Objects.equals(getData(), node.getData());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getData());
    }
}
