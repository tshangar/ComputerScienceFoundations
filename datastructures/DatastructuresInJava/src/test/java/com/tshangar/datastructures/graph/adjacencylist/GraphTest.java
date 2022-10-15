package com.tshangar.datastructures.graph.adjacencylist;

import org.testng.Assert;
import org.testng.annotations.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Unit testing for the class GraphTest
 */
public class GraphTest {
    /**
     * Default constructor
     */
    public GraphTest() {
    }

    /**
     * Initialize the test scenarios
     */
    @BeforeClass
    public void initializeTestScenario() {

    }

    /**
     * Clean up the test scenario
     */
    @AfterClass
    public void finalizeTestScenario() {

    }

    /**
     * Initialize the test
     */
    @BeforeMethod
    public void initializeTest() {

    }

    /**
     * Clean up the test
     */
    @AfterMethod
    public void finalizeTest() {

    }

    /**
     * Describe about the test here
     */
    @Test(priority = 1)
    public void testAdd() {
        Graph<String> graph = new Graph<>();
        Assert.assertTrue(graph.isDirected());

        graph.add("A", "B");
        Assert.assertTrue(graph.isNeighbour("A", "B"));
        Assert.assertFalse(graph.isNeighbour("B", "A"));

        graph.add("B", "C");
        Assert.assertTrue(graph.isNeighbour("B", "C"));
        Assert.assertFalse(graph.isNeighbour("C", "B"));

        graph.add("A", "C");
        Assert.assertTrue(graph.isNeighbour("A", "C"));
        Assert.assertFalse(graph.isNeighbour("C", "A"));

        Assert.assertTrue(graph.contains("A"));
        Assert.assertTrue(graph.contains("B"));
        Assert.assertTrue(graph.contains("C"));
        Assert.assertFalse(graph.contains("X"));

        Assert.assertEquals(graph.getNeighbours("C").size(), 0);
    }

    /**
     * Describe about the test here
     */
    @Test(priority = 2)
    public void testRemove() {
        Graph<String> graph = new Graph<>(true);
        Assert.assertFalse(graph.remove("X", "Y"));

        graph.add("A", "B");
        graph.add("B", "C");
        graph.add("A", "C");

        Assert.assertFalse(graph.remove("C", "B"));
        Assert.assertTrue(graph.remove("B", "C"));
        Assert.assertTrue(graph.contains("A"));
        Assert.assertTrue(graph.contains("B"));
        Assert.assertTrue(graph.contains("C"));
        Assert.assertFalse(graph.contains("X"));
        Assert.assertEquals(graph.getNeighbours("A").size(), 2);
        Assert.assertEquals(graph.getNeighbours("B").size(), 0);
        Assert.assertEquals(graph.getNeighbours("C").size(), 0);
        Assert.assertTrue(graph.isNeighbour("A", "B"));
        Assert.assertFalse(graph.isNeighbour("B", "A"));
        Assert.assertTrue(graph.isNeighbour("A", "C"));
        Assert.assertFalse(graph.isNeighbour("B", "A"));
        Assert.assertFalse(graph.isNeighbour("B", "C"));
        Assert.assertFalse(graph.isNeighbour("C", "B"));
        Assert.assertFalse(graph.isNeighbour("X", "Y"));
        Assert.assertFalse(graph.isNeighbour("Y", "X"));

        Assert.assertTrue(graph.remove("A", "B"));
        Assert.assertTrue(graph.contains("A"));
        Assert.assertFalse(graph.contains("B"));
        Assert.assertTrue(graph.contains("C"));
        Assert.assertFalse(graph.contains("X"));
        Assert.assertEquals(graph.getNeighbours("A").size(), 1);
        Assert.assertEquals(graph.getNeighbours("B").size(), 0);
        Assert.assertEquals(graph.getNeighbours("C").size(), 0);
    }

    /**
     * Describe about the test here
     */
    @Test(priority = 3)
    public void testGetBreadthFirst() {
        Graph<String> graph = new Graph<>();
        graph.add("A", "B");
        graph.add("A", "C");
        graph.add("C", "B");
        graph.add("C", "D");
        graph.add("C", "E");
        graph.add("D", "B");
        graph.add("D", "E");

        List<String> breadthFirst = graph.getBreadthFirst("A");
        Assert.assertEquals(breadthFirst.size(), 8);
        Assert.assertEquals(breadthFirst.get(0), "A");
        Assert.assertEquals(breadthFirst.get(1), "B");
        Assert.assertEquals(breadthFirst.get(2), "C");
        Assert.assertEquals(breadthFirst.get(3), "B");
        Assert.assertEquals(breadthFirst.get(4), "D");
        Assert.assertEquals(breadthFirst.get(5), "E");
        Assert.assertEquals(breadthFirst.get(6), "B");
        Assert.assertEquals(breadthFirst.get(7), "E");
    }

    /**
     * Describe about the test here
     */
    @Test(priority = 4)
    public void testGetDepthFirst() {
        Graph<String> graph = new Graph<>();
        graph.add("A", "B");
        graph.add("A", "C");
        graph.add("C", "B");
        graph.add("C", "D");
        graph.add("C", "E");
        graph.add("D", "B");
        graph.add("D", "E");

        List<String> depthFirst = graph.getDepthFirst("A");
        Assert.assertEquals(depthFirst.size(), 8);
        Assert.assertEquals(depthFirst.get(0), "A");
        Assert.assertEquals(depthFirst.get(1), "C");
        Assert.assertEquals(depthFirst.get(2), "E");
        Assert.assertEquals(depthFirst.get(3), "D");
        Assert.assertEquals(depthFirst.get(4), "E");
        Assert.assertEquals(depthFirst.get(5), "B");
        Assert.assertEquals(depthFirst.get(6), "B");
        Assert.assertEquals(depthFirst.get(7), "B");
    }

    /**
     * Describe about the test here
     */
    @Test(priority = 5)
    public void testGetDepthFirstRecursive() {
        Graph<String> graph = new Graph<>();
        graph.add("A", "B");
        graph.add("A", "C");
        graph.add("C", "B");
        graph.add("C", "D");
        graph.add("C", "E");
        graph.add("D", "B");
        graph.add("D", "E");

        List<String> depthFirst = graph.getDepthFirstRecursive("A");
        Assert.assertEquals(depthFirst.size(), 8);
        Assert.assertEquals(depthFirst.get(0), "A");
        Assert.assertEquals(depthFirst.get(1), "B");
        Assert.assertEquals(depthFirst.get(2), "C");
        Assert.assertEquals(depthFirst.get(3), "B");
        Assert.assertEquals(depthFirst.get(4), "D");
        Assert.assertEquals(depthFirst.get(5), "B");
        Assert.assertEquals(depthFirst.get(6), "E");
        Assert.assertEquals(depthFirst.get(7), "E");
    }

    /**
     * Describe about the test here
     */
    @Test(priority = 6)
    public void testHasPathBreadthFirstIterative() {
        Graph<String> graph = new Graph<>();
        graph.add("A", "B");
        graph.add("A", "C");
        graph.add("C", "B");
        graph.add("C", "D");
        graph.add("C", "E");
        graph.add("D", "B");
        graph.add("D", "E");

        Assert.assertTrue(graph.hasPathBreadthFirstIterative("A", "B"));
        Assert.assertTrue(graph.hasPathBreadthFirstIterative("A", "C"));
        Assert.assertTrue(graph.hasPathBreadthFirstIterative("A", "D"));
        Assert.assertTrue(graph.hasPathBreadthFirstIterative("A", "E"));
        Assert.assertFalse(graph.hasPathBreadthFirstIterative("D", "A"));
        Assert.assertFalse(graph.hasPathBreadthFirstIterative("D", "C"));
        Assert.assertFalse(graph.hasPathBreadthFirstIterative("B", "E"));
    }

    /**
     * Describe about the test here
     */
    @Test(priority = 7)
    public void testHasPathDepthFirstIterative() {
        Graph<String> graph = new Graph<>();
        graph.add("A", "B");
        graph.add("A", "C");
        graph.add("C", "B");
        graph.add("C", "D");
        graph.add("C", "E");
        graph.add("D", "B");
        graph.add("D", "E");

        Assert.assertTrue(graph.hasPathDepthFirstIterative("A", "B"));
        Assert.assertTrue(graph.hasPathDepthFirstIterative("A", "C"));
        Assert.assertTrue(graph.hasPathDepthFirstIterative("A", "D"));
        Assert.assertTrue(graph.hasPathDepthFirstIterative("A", "E"));
        Assert.assertFalse(graph.hasPathDepthFirstIterative("D", "A"));
        Assert.assertFalse(graph.hasPathDepthFirstIterative("D", "C"));
        Assert.assertFalse(graph.hasPathDepthFirstIterative("B", "E"));
    }

    /**
     * Describe about the test here
     */
    @Test(priority = 8)
    public void testHasPathDepthFirstRecursive() {
        Graph<String> graph = new Graph<>();
        graph.add("A", "B");
        graph.add("A", "C");
        graph.add("C", "B");
        graph.add("C", "D");
        graph.add("C", "E");
        graph.add("D", "B");
        graph.add("D", "E");

        Assert.assertTrue(graph.hasPathDepthFirstRecursive("A", "B"));
        Assert.assertTrue(graph.hasPathDepthFirstRecursive("A", "C"));
        Assert.assertTrue(graph.hasPathDepthFirstRecursive("A", "D"));
        Assert.assertTrue(graph.hasPathDepthFirstRecursive("A", "E"));
        Assert.assertFalse(graph.hasPathDepthFirstRecursive("D", "A"));
        Assert.assertFalse(graph.hasPathDepthFirstRecursive("D", "C"));
        Assert.assertFalse(graph.hasPathDepthFirstRecursive("B", "E"));
    }

    /**
     * Describe about the test here
     */
    @Test(priority = 9)
    public void testGraphPopulateFromEdges() {
        List<Edge<String>> edges = new LinkedList<>();
        edges.add(new Edge<>("A", "B"));
        edges.add(new Edge<>("A", "C"));
        edges.add(new Edge<>("C", "B"));
        edges.add(new Edge<>("C", "D"));
        edges.add(new Edge<>("C", "E"));
        edges.add(new Edge<>("D", "B"));
        edges.add(new Edge<>("D", "E"));

        Graph<String> graph = new Graph<>();
        graph.populate(edges);

        Assert.assertTrue(graph.hasPathDepthFirstRecursive("A", "B"));
        Assert.assertTrue(graph.hasPathDepthFirstRecursive("A", "C"));
        Assert.assertTrue(graph.hasPathDepthFirstRecursive("A", "D"));
        Assert.assertTrue(graph.hasPathDepthFirstRecursive("A", "E"));
        Assert.assertFalse(graph.hasPathDepthFirstRecursive("D", "A"));
        Assert.assertFalse(graph.hasPathDepthFirstRecursive("D", "C"));
        Assert.assertFalse(graph.hasPathDepthFirstRecursive("B", "E"));

        List<String> depthFirst = graph.getDepthFirstRecursive("A");
        Assert.assertEquals(depthFirst.size(), 8);
        Assert.assertEquals(depthFirst.get(0), "A");
        Assert.assertEquals(depthFirst.get(1), "B");
        Assert.assertEquals(depthFirst.get(2), "C");
        Assert.assertEquals(depthFirst.get(3), "B");
        Assert.assertEquals(depthFirst.get(4), "D");
        Assert.assertEquals(depthFirst.get(5), "B");
        Assert.assertEquals(depthFirst.get(6), "E");
        Assert.assertEquals(depthFirst.get(7), "E");

        List<String> breadthFirst = graph.getBreadthFirst("A");
        Assert.assertEquals(breadthFirst.size(), 8);
        Assert.assertEquals(breadthFirst.get(0), "A");
        Assert.assertEquals(breadthFirst.get(1), "B");
        Assert.assertEquals(breadthFirst.get(2), "C");
        Assert.assertEquals(breadthFirst.get(3), "B");
        Assert.assertEquals(breadthFirst.get(4), "D");
        Assert.assertEquals(breadthFirst.get(5), "E");
        Assert.assertEquals(breadthFirst.get(6), "B");
        Assert.assertEquals(breadthFirst.get(7), "E");
    }

    /**
     * Describe about the test here
     */
    @Test(priority = 10)
    public void testGetBreadthFirstForUndirectedCycleGraph() {
        List<Edge<String>> edges = new LinkedList<>();
        edges.add(new Edge<>(false, "A", "B"));
        edges.add(new Edge<>(false, "A", "C"));
        edges.add(new Edge<>(false, "C", "B"));
        edges.add(new Edge<>(false, "C", "D"));
        edges.add(new Edge<>(false, "C", "E"));
        edges.add(new Edge<>(false, "D", "B"));
        edges.add(new Edge<>(false, "D", "E"));

        Graph<String> graph = new Graph<>(false);
        graph.populate(edges);

        List<String> breadthFirst = graph.getBreadthFirst("A");
        Assert.assertEquals(breadthFirst.size(), 5);
        Assert.assertEquals(breadthFirst.get(0), "A");
        Assert.assertEquals(breadthFirst.get(1), "B");
        Assert.assertEquals(breadthFirst.get(2), "C");
        Assert.assertEquals(breadthFirst.get(3), "D");
        Assert.assertEquals(breadthFirst.get(4), "E");
    }

    /**
     * Describe about the test here
     */
    @Test(priority = 11)
    public void testGetDepthFirstForUndirectedCycleGraph() {
        List<Edge<String>> edges = new LinkedList<>();
        edges.add(new Edge<>(false, "A", "B"));
        edges.add(new Edge<>(false, "A", "C"));
        edges.add(new Edge<>(false, "C", "B"));
        edges.add(new Edge<>(false, "C", "D"));
        edges.add(new Edge<>(false, "C", "E"));
        edges.add(new Edge<>(false, "D", "B"));
        edges.add(new Edge<>(false, "D", "E"));

        Graph<String> graph = new Graph<>(false);
        graph.populate(edges);

        List<String> depthFirst = graph.getDepthFirst("A");
        Assert.assertEquals(depthFirst.size(), 5);
        Assert.assertEquals(depthFirst.get(0), "A");
        Assert.assertEquals(depthFirst.get(1), "C");
        Assert.assertEquals(depthFirst.get(2), "E");
        Assert.assertEquals(depthFirst.get(3), "D");
        Assert.assertEquals(depthFirst.get(4), "B");
    }

    /**
     * Describe about the test here
     */
    @Test(priority = 12)
    public void testGetDepthFirstRecursiveForUndirectedCycleGraph() {
        List<Edge<String>> edges = new LinkedList<>();
        edges.add(new Edge<>(false, "A", "B"));
        edges.add(new Edge<>(false, "A", "C"));
        edges.add(new Edge<>(false, "C", "B"));
        edges.add(new Edge<>(false, "C", "D"));
        edges.add(new Edge<>(false, "C", "E"));
        edges.add(new Edge<>(false, "D", "B"));
        edges.add(new Edge<>(false, "D", "E"));

        Graph<String> graph = new Graph<>(false);
        graph.populate(edges);

        List<String> depthFirst = graph.getDepthFirstRecursive("A");
        Assert.assertEquals(depthFirst.size(), 5);
        Assert.assertEquals(depthFirst.get(0), "A");
        Assert.assertEquals(depthFirst.get(1), "B");
        Assert.assertEquals(depthFirst.get(2), "C");
        Assert.assertEquals(depthFirst.get(3), "D");
        Assert.assertEquals(depthFirst.get(4), "E");
    }

    /**
     * Describe about the test here
     */
    @Test(priority = 13)
    public void testHasPathBreadthFirstIterativeForUndirectedCycleGraph() {
        List<Edge<String>> edges = new LinkedList<>();
        edges.add(new Edge<>(false, "A", "B"));
        edges.add(new Edge<>(false, "A", "C"));
        edges.add(new Edge<>(false, "C", "B"));
        edges.add(new Edge<>(false, "C", "D"));
        edges.add(new Edge<>(false, "C", "E"));
        edges.add(new Edge<>(false, "D", "B"));
        edges.add(new Edge<>(false, "D", "E"));

        Graph<String> graph = new Graph<>(false);
        graph.populate(edges);

        Assert.assertTrue(graph.hasPathBreadthFirstIterative("A", "B"));
        Assert.assertTrue(graph.hasPathBreadthFirstIterative("A", "C"));
        Assert.assertTrue(graph.hasPathBreadthFirstIterative("A", "D"));
        Assert.assertTrue(graph.hasPathBreadthFirstIterative("A", "E"));
        Assert.assertTrue(graph.hasPathBreadthFirstIterative("D", "A"));
        Assert.assertTrue(graph.hasPathBreadthFirstIterative("D", "C"));
        Assert.assertTrue(graph.hasPathBreadthFirstIterative("B", "E"));
    }

    /**
     * Describe about the test here
     */
    @Test(priority = 14)
    public void testGetIslandCount() {
        Graph<String> graph = new Graph<>(false);

        graph.add("A", "B");
        graph.add("A", "C");
        graph.add("C", "B");
        graph.add("C", "D");
        graph.add("C", "E");
        graph.add("D", "B");
        graph.add("D", "E");

        graph.add("P", "Q");
        graph.add("P", "R");
        graph.add("Q", "R");

        graph.add("X", "Y");
        graph.add("Y", "Z");

        Assert.assertEquals(graph.getIslandCount(), 3);
    }

    /**
     * Describe about the test here
     */
    @Test(priority = 15)
    public void testGetLargestIslandCount() {
        Graph<String> graph = new Graph<>(false);

        graph.add("A", "B");
        graph.add("A", "C");
        graph.add("C", "B");
        graph.add("C", "D");
        graph.add("C", "E");
        graph.add("D", "B");
        graph.add("D", "E");

        graph.add("P", "Q");
        graph.add("P", "R");
        graph.add("Q", "R");

        graph.add("W", "X");
        graph.add("X", "Y");
        graph.add("Z", "Y");

        Assert.assertEquals(graph.getLargestIslandCount(), 5);
    }
}