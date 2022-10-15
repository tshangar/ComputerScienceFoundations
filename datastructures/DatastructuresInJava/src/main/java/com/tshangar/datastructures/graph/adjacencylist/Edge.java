package com.tshangar.datastructures.graph.adjacencylist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Edge
 */
public class Edge<E> {
    private static final Logger logger = LoggerFactory.getLogger(Edge.class);

    private final boolean isDirected;
    private E source;
    private E destination;

    /**
     * Default constructor to create Edge object
     */
    public Edge(E source, E destination) {
        isDirected = true;

        this.source = source;
        this.destination = destination;
    }

    public Edge(boolean isDirected, E source, E destination) {
        this.isDirected = isDirected;

        this.source = source;
        this.destination = destination;
    }

    public E getSource() {
        return source;
    }

    public void setSource(E source) {
        this.source = source;
    }

    public E getDestination() {
        return destination;
    }

    public void setDestination(E destination) {
        this.destination = destination;
    }
}
