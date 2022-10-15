package com.tshangar.datastructures.graph.adjacencylist;

import org.apache.commons.lang.NullArgumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

/**
 * Graph
 * Reason to Use Map instead of Set, because Set don't provide get() method
 * Reference : <a href="https://stackoverflow.com/questions/7283338/getting-an-element-from-a-set">Getting an element from a Set</a>
 */
public class Graph<E> {
    private static final Logger logger = LoggerFactory.getLogger(Graph.class);

    private final Map<E, Vertex<E>> adjacencyList = new HashMap<>();
    private final boolean isDirected;

    /**
     * Default constructor to create Graph object
     */
    public Graph() {
        this.isDirected = true;
    }

    public Graph(boolean isDirected) {
        this.isDirected = isDirected;
    }

    public boolean isDirected() {
        return isDirected;
    }

    public void add(E source, E destination) {
        if (source == null || destination == null) {
            throw new NullArgumentException("Source and Destination Vertex should not be null");
        }

        if (isDirected) {
            addVertex(source, destination);
        } else {
            addVertex(source, destination);
            addVertex(destination, source);
        }
    }

    private void addVertex(E source, E destination) {
        addVertex(source);
        addVertex(destination);

        Vertex<E> sourceVertex = adjacencyList.get(source);
        sourceVertex.addNeighbour(destination);
    }

    private void addVertex(E vertexValue) {
        if (adjacencyList.containsKey(vertexValue)) {
            return;
        }

        Vertex<E> vertex = new Vertex<>(vertexValue);
        adjacencyList.put(vertexValue, vertex);
    }

    public boolean remove(E source, E destination) {
        if (source == null || destination == null) {
            throw new NullArgumentException("Source and Destination Vertex should not be null");
        }

        if (isDirected) {
            return removeVertex(source, destination);
        }

        boolean status = removeVertex(source, destination);
        status = status && removeVertex(destination, source);

        return  status;
    }

    private boolean removeVertex(E source, E destination) {
        if (!adjacencyList.containsKey(source)) {
            return false;
        }

        boolean status = adjacencyList.get(source).removeNeighbour(destination);
        if (adjacencyList.get(source).size() == 0 && !isNeighbour(source)) {
            adjacencyList.remove(source);
        }

        if (adjacencyList.get(destination).size() == 0 && !isNeighbour(destination)) {
            adjacencyList.remove(destination);
        }

        return status;
    }

    public boolean contains(E source) {
        return adjacencyList.containsKey(source);
    }

    public boolean isNeighbour(E vertex) {
        for (Vertex<E> value: adjacencyList.values()){
            if (value != vertex && isNeighbour(value.getValue(), vertex)) {
                return true;
            }
        }

        return false;
    }

    public boolean isNeighbour(E source, E destination) {
        if (source == null || destination == null) {
            throw new NullArgumentException("Source and Destination Vertex should not be null");
        }

        if (!adjacencyList.containsKey(source)) {
            return false;
        }

        return adjacencyList.get(source).isNeighbour(destination);
    }

    public Collection<E> getNeighbours(E source) {
        if (adjacencyList.get(source) == null) {
            return Collections.emptyList();
        }

        return adjacencyList.get(source).getNeighbours();
    }

    public List<E> getBreadthFirst(E source) {
        if (!adjacencyList.containsKey(source)) {
            return Collections.emptyList();
        }

        List<E> results = new ArrayList<>();
        Deque<E> queue = new LinkedList<>();
        queue.addLast(adjacencyList.get(source).getValue());

        while (!queue.isEmpty()) {
            E vertex = queue.removeFirst();
            results.add(vertex);

            if (!adjacencyList.containsKey(vertex)) {
                continue;
            }

            Iterator<E> iterator = adjacencyList.get(vertex).iterator();
            while (iterator.hasNext()) {
                queue.addLast(iterator.next());
            }
        }

        return results;
    }

    public List<E> getDepthFirst(E source) {
        if (!adjacencyList.containsKey(source)) {
            return Collections.emptyList();
        }

        List<E> results = new ArrayList<>();
        Deque<E> stack = new LinkedList<>();
        stack.addFirst(adjacencyList.get(source).getValue());

        while (!stack.isEmpty()) {
            E vertex = stack.removeFirst();
            results.add(vertex);

            if (!adjacencyList.containsKey(vertex)) {
                continue;
            }

            Iterator<E> iterator = adjacencyList.get(vertex).iterator();
            while (iterator.hasNext()) {
                stack.addFirst(iterator.next());
            }
        }

        return results;
    }

    public List<E> getDepthFirstRecursive(E source) {
        List<E> results = new ArrayList<>();
        getDepthFirstRecursive(source, results);

        return results;
    }

    private void getDepthFirstRecursive(E source, List<E> results) {
        if (!adjacencyList.containsKey(source)) {
            return;
        }

        results.add(source);
        adjacencyList.get(source).getNeighbours().forEach(vertex -> getDepthFirstRecursive(vertex, results));
    }

    public boolean hasPathBreadthFirstIterative(E source, E destination) {
        if (source == null || destination == null) {
            throw new NullArgumentException("Source and Destination Vertex should not be null");
        }

        if ( !adjacencyList.containsKey(source) || !adjacencyList.containsKey(destination) ) {
            return false;
        }

        if (source.equals(destination)) {
            return true;
        }

        Deque<E> queue = new LinkedList<>();
        queue.addLast(adjacencyList.get(source).getValue());

        while (!queue.isEmpty()) {
            E vertex = queue.removeFirst();
            if (vertex.equals(destination)) {
                return true;
            }

            if (!adjacencyList.containsKey(vertex)) {
                continue;
            }

            Iterator<E> iterator = adjacencyList.get(vertex).iterator();
            while (iterator.hasNext()) {
                queue.addLast(iterator.next());
            }
        }

        return false;
    }

    public boolean hasPathDepthFirstIterative(E source, E destination) {
        if (source == null || destination == null) {
            throw new NullArgumentException("Source and Destination Vertex should not be null");
        }

        if ( !adjacencyList.containsKey(source) || !adjacencyList.containsKey(destination) ) {
            return false;
        }

        if (source.equals(destination)) {
            return true;
        }

        Deque<E> stack = new LinkedList<>();
        stack.addFirst(adjacencyList.get(source).getValue());

        while (!stack.isEmpty()) {
            E vertex = stack.removeFirst();
            if (vertex.equals(destination)) {
                return true;
            }

            if (!adjacencyList.containsKey(vertex)) {
                continue;
            }

            Iterator<E> iterator = adjacencyList.get(vertex).iterator();
            while (iterator.hasNext()) {
                stack.addFirst(iterator.next());
            }
        }

        return false;
    }

    public boolean hasPathDepthFirstRecursive(E source, E destination) {
        if (source == null || destination == null) {
            throw new NullArgumentException("Source and Destination Vertex should not be null");
        }

        if ( !adjacencyList.containsKey(source) || !adjacencyList.containsKey(destination) ) {
            return false;
        }

        if (source.equals(destination)) {
            return true;
        }

        for (E vertex : adjacencyList.get(source).getNeighbours()) {
            if (hasPathDepthFirstRecursive(vertex, destination)) {
                return true;
            }
        }

        return false;
    }
}
