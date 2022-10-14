package com.tshangar.datastructures.graph.adjacencylist;

import org.testng.Assert;
import org.testng.annotations.*;

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

        List<String> depthFirst = graph.getBreadthFirst("A");
        Assert.assertEquals(depthFirst.size(), 8);
        Assert.assertEquals(depthFirst.get(0), "A");
        Assert.assertEquals(depthFirst.get(1), "B");
        Assert.assertEquals(depthFirst.get(2), "C");
        Assert.assertEquals(depthFirst.get(3), "B");
        Assert.assertEquals(depthFirst.get(4), "D");
        Assert.assertEquals(depthFirst.get(5), "E");
        Assert.assertEquals(depthFirst.get(6), "B");
        Assert.assertEquals(depthFirst.get(7), "E");
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
}