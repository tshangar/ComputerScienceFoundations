package com.tshangar.datastructures.graph.adjacencylist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Vertex
 */
public class Vertex<E> {
    private static final Logger logger = LoggerFactory.getLogger(Vertex.class);

    private E value;
    private final List<E> neighbourVertices = new LinkedList<>();

    /**
     * Constructor to create Vertex object
     */
    public Vertex(E value) {
        this.value = value;
    }

    public E getValue() {
        return value;
    }

    public void setValue(E value) {
        this.value = value;
    }

    public Collection<E> getNeighbours() {
        return Collections.unmodifiableCollection(neighbourVertices);
    }

    public int size() {
        return neighbourVertices.size();
    }

    public void addNeighbour(E vertex) {
        neighbourVertices.add(vertex);
    }

    public boolean removeNeighbour(E neighbor) {
        return neighbourVertices.removeIf(vertex -> vertex.equals(neighbor));
    }

    public boolean isNeighbour(E neighbor) {
        return neighbourVertices.stream().anyMatch(vertex -> vertex.equals(neighbor));
    }

    public Iterator<E> iterator() {
        return neighbourVertices.iterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vertex<?> vertex)) return false;
        return Objects.equals(getValue(), vertex.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
