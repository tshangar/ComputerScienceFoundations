package com.tshangar.datastructures.graph.adjacencylist;

import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Collection;

/**
 * Unit testing for the class VertexTest
 */
public class VertexTest {
    /**
     * Default constructor
     */
    public VertexTest() {
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
    public void testAddNeighbor() {
        Vertex<String> vertex = new Vertex<>("A");
        Assert.assertEquals(vertex.getValue(), "A");
        Assert.assertEquals(0, vertex.size());

        vertex.addNeighbour("B");
        vertex.addNeighbour("C");
        Assert.assertEquals(vertex.getValue(), "A");
        Assert.assertEquals(2, vertex.size());
        Assert.assertTrue(vertex.isNeighbour("B"));
        Assert.assertTrue(vertex.isNeighbour("C"));

        Collection<String> neighbors = vertex.getNeighbours();
        Assert.assertEquals(2, neighbors.size());
        Assert.assertTrue(neighbors.contains("B"));
        Assert.assertTrue(neighbors.contains("C"));
        Assert.assertFalse(neighbors.contains("A"));
        Assert.assertFalse(neighbors.contains("X"));
    }

    /**
     * Describe about the test here
     */
    @Test(priority = 2)
    public void testRemoveNeighbor() {
        Vertex<String> vertex = new Vertex<>("A");
        vertex.removeNeighbour("A");
        vertex.removeNeighbour("X");
        Assert.assertEquals(0, vertex.size());

        vertex.addNeighbour("X");
        vertex.addNeighbour("Y");
        vertex.addNeighbour("Z");
        Assert.assertEquals(3, vertex.size());
        Assert.assertTrue(vertex.isNeighbour("X"));
        Assert.assertTrue(vertex.isNeighbour("Y"));
        Assert.assertTrue(vertex.isNeighbour("Z"));

        vertex.removeNeighbour("Y");
        Assert.assertEquals(2, vertex.size());
        Assert.assertTrue(vertex.isNeighbour("X"));
        Assert.assertFalse(vertex.isNeighbour("Y"));
        Assert.assertTrue(vertex.isNeighbour("Z"));

        vertex.addNeighbour("P");
        vertex.addNeighbour("Q");
        vertex.addNeighbour("R");
        Assert.assertEquals(5, vertex.size());
        Collection<String> neighbors1 = vertex.getNeighbours();
        Assert.assertEquals(5, neighbors1.size());
        Assert.assertTrue(neighbors1.contains("X"));
        Assert.assertTrue(neighbors1.contains("Z"));
        Assert.assertTrue(neighbors1.contains("P"));
        Assert.assertTrue(neighbors1.contains("Q"));
        Assert.assertTrue(neighbors1.contains("R"));
        Assert.assertFalse(neighbors1.contains("A"));
        Assert.assertFalse(neighbors1.contains("Y"));

        vertex.removeNeighbour("P");
        Collection<String> neighbors2 = vertex.getNeighbours();
        Assert.assertEquals(4, neighbors2.size());
        Assert.assertTrue(neighbors2.contains("X"));
        Assert.assertTrue(neighbors2.contains("Z"));
        Assert.assertFalse(neighbors2.contains("P"));
        Assert.assertTrue(neighbors2.contains("Q"));
        Assert.assertTrue(neighbors2.contains("R"));
        Assert.assertFalse(neighbors2.contains("A"));
        Assert.assertFalse(neighbors2.contains("Y"));
    }

    /**
     * Describe about the test here
     */
    @Test(priority = 3)
    public void testIsNeighbor() {
        Vertex<String> vertex = new Vertex<>("A");
        vertex.removeNeighbour("A");
        vertex.removeNeighbour("X");
        Assert.assertEquals(0, vertex.size());

        vertex.addNeighbour("X");
        vertex.addNeighbour("Y");
        vertex.addNeighbour("Z");
        vertex.removeNeighbour("Y");
        Assert.assertEquals(2, vertex.size());
        Assert.assertTrue(vertex.isNeighbour("X"));
        Assert.assertFalse(vertex.isNeighbour("Y"));
        Assert.assertTrue(vertex.isNeighbour("Z"));

        vertex.addNeighbour("P");
        vertex.addNeighbour("Q");
        vertex.addNeighbour("R");
        Assert.assertEquals(5, vertex.size());
        Assert.assertTrue(vertex.isNeighbour("X"));
        Assert.assertFalse(vertex.isNeighbour("Y"));
        Assert.assertTrue(vertex.isNeighbour("Z"));
        Assert.assertTrue(vertex.isNeighbour("P"));
        Assert.assertTrue(vertex.isNeighbour("Q"));
        Assert.assertTrue(vertex.isNeighbour("R"));

        vertex.removeNeighbour("P");
        Assert.assertEquals(4, vertex.size());
        Assert.assertTrue(vertex.isNeighbour("X"));
        Assert.assertFalse(vertex.isNeighbour("Y"));
        Assert.assertTrue(vertex.isNeighbour("Z"));
        Assert.assertFalse(vertex.isNeighbour("P"));
        Assert.assertTrue(vertex.isNeighbour("Q"));
        Assert.assertTrue(vertex.isNeighbour("R"));
    }
}