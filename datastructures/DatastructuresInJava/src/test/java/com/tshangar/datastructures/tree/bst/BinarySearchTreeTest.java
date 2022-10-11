package com.tshangar.datastructures.tree.bst;

import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.Objects;

/**
 * Unit testing for the class BinarySearchTreeTest
 */
public class BinarySearchTreeTest {
    private BinarySearchTree<Integer> bst;
    
    /**
     * Default constructor
     */
    public BinarySearchTreeTest() {
    }

    /**
     * Initialize the test scenarios
     */
    @BeforeClass
    public void initializeTestScenario() {
        bst = new BinarySearchTree<>();
        bst.add(53);
        bst.add(24);
        bst.add(76);
        bst.add(64);
        bst.add(42);
        bst.add(13);
        bst.add(93);
        bst.add(9);
        bst.add(15);
        bst.add(36);
        bst.add(48);
        bst.add(57);
        bst.add(72);
        bst.add(86);
        bst.add(98);
        bst.add(14);
        bst.add(22);
        bst.add(54);
        bst.add(59);
        bst.add(83);
        bst.add(91);
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
        Assert.assertEquals(bst.size(), 21);

        Assert.assertFalse(bst.isEmpty());

        Assert.assertTrue(bst.contains(53));
        Assert.assertTrue(bst.contains(24));
        Assert.assertTrue(bst.contains(76));

        Assert.assertTrue(bst.contains(13));
        Assert.assertTrue(bst.contains(42));

        Assert.assertTrue(bst.contains(64));
        Assert.assertTrue(bst.contains(93));
    }

    /**
     * Describe about the test here
     */
    @Test(priority = 2)
    public void testFind() {
        Assert.assertNull(bst.find(-43));
        Assert.assertNull(bst.find(152));

        Assert.assertEquals(Objects.requireNonNull(bst.find(53)).getData(), 53);
        Assert.assertEquals(Objects.requireNonNull(bst.find(53)).getLeft().getData(), 24);
        Assert.assertEquals(Objects.requireNonNull(bst.find(53)).getRight().getData(), 76);

        Assert.assertEquals(Objects.requireNonNull(bst.find(24)).getData(), 24);
        Assert.assertEquals(Objects.requireNonNull(bst.find(24)).getLeft().getData(), 13);
        Assert.assertEquals(Objects.requireNonNull(bst.find(24)).getRight().getData(), 42);

        Assert.assertEquals(Objects.requireNonNull(bst.find(76)).getData(), 76);
        Assert.assertEquals(Objects.requireNonNull(bst.find(76)).getLeft().getData(), 64);
        Assert.assertEquals(Objects.requireNonNull(bst.find(76)).getRight().getData(), 93);

        Assert.assertEquals(Objects.requireNonNull(bst.find(13)).getData(), 13);
        Assert.assertNotNull(Objects.requireNonNull(bst.find(13)).getLeft());
        Assert.assertNotNull(Objects.requireNonNull(bst.find(13)).getRight());

        Assert.assertEquals(Objects.requireNonNull(bst.find(42)).getData(), 42);
        Assert.assertNotNull(Objects.requireNonNull(bst.find(42)).getLeft());
        Assert.assertNotNull(Objects.requireNonNull(bst.find(42)).getRight());

        Assert.assertEquals(Objects.requireNonNull(bst.find(64)).getData(), 64);
        Assert.assertNotNull(Objects.requireNonNull(bst.find(64)).getLeft());
        Assert.assertNotNull(Objects.requireNonNull(bst.find(64)).getRight());

        Assert.assertEquals(Objects.requireNonNull(bst.find(93)).getData(), 93);
        Assert.assertNotNull(Objects.requireNonNull(bst.find(93)).getLeft());
        Assert.assertNotNull(Objects.requireNonNull(bst.find(93)).getRight());
    }

    /**
     * Describe about the test here
     */
    @Test(priority = 3)
    public void testBreadthFirstSearch() {
        List<Integer> results = bst.getBreadthFirstSearch();

        Assert.assertEquals(results.size(), 21);
        Assert.assertEquals(results.get(0), 53);
        Assert.assertEquals(results.get(1), 24);
        Assert.assertEquals(results.get(2), 76);
        Assert.assertEquals(results.get(3), 13);
        Assert.assertEquals(results.get(4), 42);
        Assert.assertEquals(results.get(5), 64);
        Assert.assertEquals(results.get(6), 93);
        Assert.assertEquals(results.get(7), 9);
        Assert.assertEquals(results.get(8), 15);
        Assert.assertEquals(results.get(9), 36);
        Assert.assertEquals(results.get(10), 48);
        Assert.assertEquals(results.get(11), 57);
        Assert.assertEquals(results.get(12), 72);
        Assert.assertEquals(results.get(13), 86);
        Assert.assertEquals(results.get(14), 98);
        Assert.assertEquals(results.get(15), 14);
        Assert.assertEquals(results.get(16), 22);
        Assert.assertEquals(results.get(17), 54);
        Assert.assertEquals(results.get(18), 59);
        Assert.assertEquals(results.get(19), 83);
        Assert.assertEquals(results.get(20), 91);
    }

    /**
     * Describe about the test here
     */
    @Test(priority = 4)
    public void testDepthFirstSearch() {
        List<Integer> results = bst.getDepthFirstSearch();

        Assert.assertEquals(results.size(), 21);
        Assert.assertEquals(results.get(0), 53);
        Assert.assertEquals(results.get(1), 24);
        Assert.assertEquals(results.get(2), 13);
        Assert.assertEquals(results.get(3), 9);
        Assert.assertEquals(results.get(4), 15);
        Assert.assertEquals(results.get(5), 14);
        Assert.assertEquals(results.get(6), 22);
        Assert.assertEquals(results.get(7), 42);
        Assert.assertEquals(results.get(8), 36);
        Assert.assertEquals(results.get(9), 48);
        Assert.assertEquals(results.get(10), 76);
        Assert.assertEquals(results.get(11), 64);
        Assert.assertEquals(results.get(12), 57);
        Assert.assertEquals(results.get(13), 54);
        Assert.assertEquals(results.get(14), 59);
        Assert.assertEquals(results.get(15), 72);
        Assert.assertEquals(results.get(16), 93);
        Assert.assertEquals(results.get(17), 86);
        Assert.assertEquals(results.get(18), 83);
        Assert.assertEquals(results.get(19), 91);
        Assert.assertEquals(results.get(20), 98);
    }

    /**
     * Describe about the test here
     */
    @Test(priority = 5)
    public void testPreOrderTraversal() {
        List<Integer> results = bst.getPreOrderTraversal();

        Assert.assertEquals(results.size(), 21);
        Assert.assertEquals(results.get(0), 53);
        Assert.assertEquals(results.get(1), 24);
        Assert.assertEquals(results.get(2), 13);
        Assert.assertEquals(results.get(3), 9);
        Assert.assertEquals(results.get(4), 15);
        Assert.assertEquals(results.get(5), 14);
        Assert.assertEquals(results.get(6), 22);
        Assert.assertEquals(results.get(7), 42);
        Assert.assertEquals(results.get(8), 36);
        Assert.assertEquals(results.get(9), 48);
        Assert.assertEquals(results.get(10), 76);
        Assert.assertEquals(results.get(11), 64);
        Assert.assertEquals(results.get(12), 57);
        Assert.assertEquals(results.get(13), 54);
        Assert.assertEquals(results.get(14), 59);
        Assert.assertEquals(results.get(15), 72);
        Assert.assertEquals(results.get(16), 93);
        Assert.assertEquals(results.get(17), 86);
        Assert.assertEquals(results.get(18), 83);
        Assert.assertEquals(results.get(19), 91);
        Assert.assertEquals(results.get(20), 98);
    }

    /**
     * Describe about the test here
     */
    @Test(priority = 6)
    public void testInOrderTraversal() {
        List<Integer> results = bst.getInOrderTraversal();

        Assert.assertEquals(results.size(), 21);
        Assert.assertEquals(results.get(0), 9);
        Assert.assertEquals(results.get(1), 13);
        Assert.assertEquals(results.get(2), 14);
        Assert.assertEquals(results.get(3), 15);
        Assert.assertEquals(results.get(4), 22);
        Assert.assertEquals(results.get(5), 24);
        Assert.assertEquals(results.get(6), 36);
        Assert.assertEquals(results.get(7), 42);
        Assert.assertEquals(results.get(8), 48);
        Assert.assertEquals(results.get(9), 53);
        Assert.assertEquals(results.get(10), 54);
        Assert.assertEquals(results.get(11), 57);
        Assert.assertEquals(results.get(12), 59);
        Assert.assertEquals(results.get(13), 64);
        Assert.assertEquals(results.get(14), 72);
        Assert.assertEquals(results.get(15), 76);
        Assert.assertEquals(results.get(16), 83);
        Assert.assertEquals(results.get(17), 86);
        Assert.assertEquals(results.get(18), 91);
        Assert.assertEquals(results.get(19), 93);
        Assert.assertEquals(results.get(20), 98);
    }

    /**
     * Describe about the test here
     */
    @Test(priority = 7)
    public void testPostOrderTraversal() {
        List<Integer> results = bst.getPostOrderTraversal();

        Assert.assertEquals(results.size(), 21);
        Assert.assertEquals(results.get(0), 9);
        Assert.assertEquals(results.get(1), 14);
        Assert.assertEquals(results.get(2), 22);
        Assert.assertEquals(results.get(3), 15);
        Assert.assertEquals(results.get(4), 13);
        Assert.assertEquals(results.get(5), 36);
        Assert.assertEquals(results.get(6), 48);
        Assert.assertEquals(results.get(7), 42);
        Assert.assertEquals(results.get(8), 24);
        Assert.assertEquals(results.get(9), 54);
        Assert.assertEquals(results.get(10), 59);
        Assert.assertEquals(results.get(11), 57);
        Assert.assertEquals(results.get(12), 72);
        Assert.assertEquals(results.get(13), 64);
        Assert.assertEquals(results.get(14), 83);
        Assert.assertEquals(results.get(15), 91);
        Assert.assertEquals(results.get(16), 86);
        Assert.assertEquals(results.get(17), 98);
        Assert.assertEquals(results.get(18), 93);
        Assert.assertEquals(results.get(19), 76);
        Assert.assertEquals(results.get(20), 53);
    }

    /**
     * Describe about the test here
     */
    @Test(priority = 8)
    public void testInOrderSuccessor() {
        Assert.assertEquals(bst.getInOrderSuccessor(9).getData(), 13);
        Assert.assertEquals(bst.getInOrderSuccessor(22).getData(), 24);
        Assert.assertEquals(bst.getInOrderSuccessor(42).getData(), 48);
        Assert.assertEquals(bst.getInOrderSuccessor(59).getData(), 64);
        Assert.assertEquals(bst.getInOrderSuccessor(72).getData(), 76);
        Assert.assertEquals(bst.getInOrderSuccessor(93).getData(), 98);
        Assert.assertNull(bst.getInOrderSuccessor(98));
    }



    /**
     * Describe about the test here
     */
    @Test(priority = 9)
    public void testRemove() {
        List<Integer> results;


        Assert.assertTrue(bst.remove(98));
        Assert.assertEquals(bst.size(), 20);

        results = bst.getInOrderTraversal();
        Assert.assertEquals(results.size(), 20);
        Assert.assertEquals(results.get(0), 9);
        Assert.assertEquals(results.get(1), 13);
        Assert.assertEquals(results.get(2), 14);
        Assert.assertEquals(results.get(3), 15);
        Assert.assertEquals(results.get(4), 22);
        Assert.assertEquals(results.get(5), 24);
        Assert.assertEquals(results.get(6), 36);
        Assert.assertEquals(results.get(7), 42);
        Assert.assertEquals(results.get(8), 48);
        Assert.assertEquals(results.get(9), 53);
        Assert.assertEquals(results.get(10), 54);
        Assert.assertEquals(results.get(11), 57);
        Assert.assertEquals(results.get(12), 59);
        Assert.assertEquals(results.get(13), 64);
        Assert.assertEquals(results.get(14), 72);
        Assert.assertEquals(results.get(15), 76);
        Assert.assertEquals(results.get(16), 83);
        Assert.assertEquals(results.get(17), 86);
        Assert.assertEquals(results.get(18), 91);
        Assert.assertEquals(results.get(19), 93);


        Assert.assertTrue(bst.remove(64));
        Assert.assertEquals(bst.size(), 19);

        results = bst.getInOrderTraversal();
        Assert.assertEquals(results.size(), 19);
        Assert.assertEquals(results.get(0), 9);
        Assert.assertEquals(results.get(1), 13);
        Assert.assertEquals(results.get(2), 14);
        Assert.assertEquals(results.get(3), 15);
        Assert.assertEquals(results.get(4), 22);
        Assert.assertEquals(results.get(5), 24);
        Assert.assertEquals(results.get(6), 36);
        Assert.assertEquals(results.get(7), 42);
        Assert.assertEquals(results.get(8), 48);
        Assert.assertEquals(results.get(9), 53);
        Assert.assertEquals(results.get(10), 54);
        Assert.assertEquals(results.get(11), 57);
        Assert.assertEquals(results.get(12), 59);
        Assert.assertEquals(results.get(13), 72);
        Assert.assertEquals(results.get(14), 76);
        Assert.assertEquals(results.get(15), 83);
        Assert.assertEquals(results.get(16), 86);
        Assert.assertEquals(results.get(17), 91);
        Assert.assertEquals(results.get(18), 93);


        Assert.assertTrue(bst.remove(53));
        Assert.assertEquals(bst.size(), 18);

        results = bst.getInOrderTraversal();
        Assert.assertEquals(results.size(), 18);
        Assert.assertEquals(results.get(0), 9);
        Assert.assertEquals(results.get(1), 13);
        Assert.assertEquals(results.get(2), 14);
        Assert.assertEquals(results.get(3), 15);
        Assert.assertEquals(results.get(4), 22);
        Assert.assertEquals(results.get(5), 24);
        Assert.assertEquals(results.get(6), 36);
        Assert.assertEquals(results.get(7), 42);
        Assert.assertEquals(results.get(8), 48);
        Assert.assertEquals(results.get(9), 54);
        Assert.assertEquals(results.get(10), 57);
        Assert.assertEquals(results.get(11), 59);
        Assert.assertEquals(results.get(12), 72);
        Assert.assertEquals(results.get(13), 76);
        Assert.assertEquals(results.get(14), 83);
        Assert.assertEquals(results.get(15), 86);
        Assert.assertEquals(results.get(16), 91);
        Assert.assertEquals(results.get(17), 93);
    }
}