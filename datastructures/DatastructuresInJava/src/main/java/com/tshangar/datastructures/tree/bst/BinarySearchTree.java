package com.tshangar.datastructures.tree.bst;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.function.IntFunction;

/**
 * BinarySearchTree
 * References :
 * <ul>
 *     <li><a href="https://www.includehelp.com/data-structure-tutorial/traversal-technique-for-binary-tree.aspx">Include Help - Tree Traversal</a></li>
 *     <li><a href="https://www.javatpoint.com/binary-search-tree">Java Tutorial Point</a></li>
 *     <li><a href="https://www.geeksforgeeks.org/binary-search-tree-data-structure/">Geeks for Geeks - Binary Search Tree</a></li>
 *     <li><a href="https://www.geeksforgeeks.org/inorder-successor-in-binary-search-tree/">Geeks for Geeks - InOrder Successor</a></li>
 * </ul>
 *
 * @param <E> DataType of the element stored in the Binary Search Tree
 */
public class BinarySearchTree<E extends Comparable<E>> implements Collection<E> {
    private static final Logger logger = LoggerFactory.getLogger(BinarySearchTree.class);

    private Node<E> root;
    private int size = 0;

    /**
     * Default constructor to create Tree object
     */
    public BinarySearchTree() {
    }

    /**
     * Returns the number of elements in this collection.  If this collection
     * contains more than {@code Integer.MAX_VALUE} elements, returns
     * {@code Integer.MAX_VALUE}.
     *
     * @return the number of elements in this collection
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns {@code true} if this collection contains no elements.
     *
     * @return {@code true} if this collection contains no elements
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Ensures that this collection contains the specified element (optional
     * operation).  Returns {@code true} if this collection changed as a
     * result of the call.  (Returns {@code false} if this collection does
     * not permit duplicates and already contains the specified element.)<p>
     * <p>
     * Collections that support this operation may place limitations on what
     * elements may be added to this collection.  In particular, some
     * collections will refuse to add {@code null} elements, and others will
     * impose restrictions on the type of elements that may be added.
     * Collection classes should clearly specify in their documentation any
     * restrictions on what elements may be added.<p>
     * <p>
     * If a collection refuses to add a particular element for any reason
     * other than that it already contains the element, it <i>must</i> throw
     * an exception (rather than returning {@code false}).  This preserves
     * the invariant that a collection always contains the specified element
     * after this call returns.
     *
     * @param data element whose presence in this collection is to be ensured
     * @return {@code true} if this collection changed as a result of the
     * call
     * @throws UnsupportedOperationException if the {@code add} operation
     *                                       is not supported by this collection
     * @throws ClassCastException            if the class of the specified element
     *                                       prevents it from being added to this collection
     * @throws NullPointerException          if the specified element is null and this
     *                                       collection does not permit null elements
     * @throws IllegalArgumentException      if some property of the element
     *                                       prevents it from being added to this collection
     * @throws IllegalStateException         if the element cannot be added at this
     *                                       time due to insertion restrictions
     */
    @Override
    public boolean add(E data) {
        if (root == null) {
            root = new Node<>(data, null, null, null);
            size++;

            return true;
        }

        Node<E> current = root;
        while (current != null) {
            if (data.compareTo(current.getData()) < 0) {
                if (current.getLeft() == null) {
                    current.setLeft(new Node<>(data, current));
                    size++;

                    break;
                }

                current = current.getLeft();
            } else {
                if (current.getRight() == null) {
                    current.setRight(new Node<>(data, current));
                    size++;

                    break;
                }

                current = current.getRight();
            }
        }

        return true;
    }

    /**
     * Removes a single instance of the specified element from this
     * collection, if it is present (optional operation).  More formally,
     * removes an element {@code e} such that
     * {@code Objects.equals(o, e)}, if
     * this collection contains one or more such elements.  Returns
     * {@code true} if this collection contained the specified element (or
     * equivalently, if this collection changed as a result of the call).
     * <p>
     * Algorithm to delete a Node in the tree
     * <ul>
     *     <li>Delete the Node and update its parent if the node is the leaf Node</li>
     *     <li>Target Node has only one child, replace the nodes and delete</li>
     *     <li>Target Node has two children, find the inorder successor until the leaf node, swap the nodes and delete</li>
     * </ul>
     *
     * @param object element to be removed from this collection, if present
     * @return {@code true} if an element was removed as a result of this call
     * @throws ClassCastException            if the type of the specified element
     *                                       is incompatible with this collection
     *                                       (<a href="{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException          if the specified element is null and this
     *                                       collection does not permit null elements
     *                                       (<a href="{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws UnsupportedOperationException if the {@code remove} operation
     *                                       is not supported by this collection
     */
    @Override
    public boolean remove(Object object) {
        if (root.getData().getClass().isAssignableFrom(object.getClass())) {
            final Class<E> ELEMENT_TYPE = (Class<E>) object.getClass();
            E element = ELEMENT_TYPE.cast(object);
            return remove(find(element));
        }

        return false;
    }

    private boolean remove(Node<E> targetNode) {
        if (targetNode == null) {
            return false;
        }

        // Target Node has no child node, it's the leaf node
        if (targetNode.getLeft() == null && targetNode.getRight() == null) {
            Node<E> parent = targetNode.getParent();
            if (targetNode.equals(parent.getLeft())) {
                parent.setLeft(null);
            }

            if (targetNode.equals(parent.getRight())) {
                parent.setRight(null);
            }

            targetNode.setParent(null);
            targetNode.setData(null);

            size--;
            return true;
        }

        // Target Node has only one child
        // Swap the Nodes
        // Delete the Node
        if (targetNode.getLeft() != null && targetNode.getRight() == null) {
            Node<E> child = targetNode.getLeft();
            E targetData = targetNode.getData();
            targetNode.setData(child.getData());
            child.setData(targetData);

            child.setLeft(null);
            child.setRight(null);
            return remove(child);
        }

        // Target Node has only one child
        // Swap the Nodes
        // Delete the Node
        if (targetNode.getLeft() == null && targetNode.getRight() != null) {
            Node<E> child = targetNode.getRight();
            E targetData = targetNode.getData();
            targetNode.setData(child.getData());
            child.setData(targetData);

            child.setLeft(null);
            child.setRight(null);
            return remove(child);
        }

        // Target Node has both the left and right node available
        // Get the InOrder Successor and swap the target node with the InOrder Successor
        // Perform the above operation until the target node is placed at the leaf of the tree
        // Delete the target node
        Node<E> successor = getInOrderSuccessor(targetNode.getData());
        if (successor == null) {
            return false;
        }

        while (successor.getLeft() != null || successor.getRight() != null) {
            successor = getInOrderSuccessor(successor.getData());
            if (successor == null) {
                return false;
            }
        }

        E targetData = targetNode.getData();
        targetNode.setData(successor.getData());

        successor.setData(targetData);
        successor.setLeft(null);
        successor.setRight(null);

        return remove(successor);
    }

    /**
     * Returns {@code true} if this collection contains the specified element.
     * More formally, returns {@code true} if and only if this collection
     * contains at least one element {@code e} such that
     * {@code Objects.equals(o, e)}.
     *
     * @param object element whose presence in this collection is to be tested
     * @return {@code true} if this collection contains the specified
     * element
     * @throws ClassCastException   if the type of the specified element
     *                              is incompatible with this collection
     *                              (<a href="{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified element is null and this
     *                              collection does not permit null elements
     *                              (<a href="{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
     */
    @Override
    public boolean contains(Object object) {
        if (object == null || root == null) {
            return false;
        }

        Node<E> current = root;
        while (current != null) {
            if (current.getData() == null) {
                return false;
            }

            if (current.getData().equals(object)) {
                return true;
            }

            if (current.getData().getClass().isAssignableFrom(object.getClass())) {
                final Class<E> ELEMENT_TYPE = (Class<E>) object.getClass();
                E element = ELEMENT_TYPE.cast(object);

                if (element.compareTo(current.getData()) < 0) {
                    current = current.getLeft();
                } else {
                    current = current.getRight();
                }
            } else {
                return false;
            }
        }

        return false;
    }

    public Node<E> find(E data) {
        if (root == null) {
            return null;
        }

        Node<E> current = root;
        while (current != null) {
            if (current.getData().equals(data)) {
                return current;
            }

            if (data.compareTo(current.getData()) < 0) {
                current = current.getLeft();
            } else {
                current = current.getRight();
            }
        }

        return null;
    }

    /**
     * Adds all the elements in the specified collection to this collection
     * (optional operation).  The behavior of this operation is undefined if
     * the specified collection is modified while the operation is in progress.
     * (This implies that the behavior of this call is undefined if the
     * specified collection is this collection, and this collection is
     * nonempty.)
     *
     * @param collection collection containing elements to be added to this collection
     * @return {@code true} if this collection changed as a result of the call
     * @throws UnsupportedOperationException if the {@code addAll} operation
     *                                       is not supported by this collection
     * @throws ClassCastException            if the class of an element of the specified
     *                                       collection prevents it from being added to this collection
     * @throws NullPointerException          if the specified collection contains a
     *                                       null element and this collection does not permit null elements,
     *                                       or if the specified collection is null
     * @throws IllegalArgumentException      if some property of an element of the
     *                                       specified collection prevents it from being added to this
     *                                       collection
     * @throws IllegalStateException         if not all the elements can be added at
     *                                       this time due to insertion restrictions
     * @see #add(E Object)
     */
    @Override
    public boolean addAll(Collection<? extends E> collection) {
        collection.forEach(this::add);
        return true;
    }

    /**
     * Removes all of this collection's elements that are also contained in the
     * specified collection (optional operation).  After this call returns,
     * this collection will contain no elements in common with the specified
     * collection.
     *
     * @param collection collection containing elements to be removed from this collection
     * @return {@code true} if this collection changed as a result of the
     * call
     * @throws UnsupportedOperationException if the {@code removeAll} method
     *                                       is not supported by this collection
     * @throws ClassCastException            if the types of one or more elements
     *                                       in this collection are incompatible with the specified
     *                                       collection
     *                                       (<a href="{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException          if this collection contains one or more
     *                                       null elements and the specified collection does not support
     *                                       null elements
     *                                       (<a href="{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>),
     *                                       or if the specified collection is null
     * @see #remove(Object)
     * @see #contains(Object)
     */
    @Override
    public boolean removeAll(Collection<?> collection) {
        collection.forEach(this::remove);
        return true;
    }

    /**
     * Retains only the elements in this collection that are contained in the
     * specified collection (optional operation).  In other words, removes from
     * this collection all of its elements that are not contained in the
     * specified collection.
     *
     * @param c collection containing elements to be retained in this collection
     * @return {@code true} if this collection changed as a result of the call
     * @throws UnsupportedOperationException if the {@code retainAll} operation
     *                                       is not supported by this collection
     * @throws ClassCastException            if the types of one or more elements
     *                                       in this collection are incompatible with the specified
     *                                       collection
     *                                       (<a href="{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException          if this collection contains one or more
     *                                       null elements and the specified collection does not permit null
     *                                       elements
     *                                       (<a href="{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>),
     *                                       or if the specified collection is null
     * @see #remove(Object)
     * @see #contains(Object)
     */
    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    /**
     * Returns {@code true} if this collection contains all the elements
     * in the specified collection.
     *
     * @param c collection to be checked for containment in this collection
     * @return {@code true} if this collection contains all the elements
     * in the specified collection
     * @throws ClassCastException   if the types of one or more elements
     *                              in the specified collection are incompatible with this
     *                              collection
     *                              (<a href="{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified collection contains one
     *                              or more null elements and this collection does not permit null
     *                              elements
     *                              (<a href="{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>),
     *                              or if the specified collection is null.
     * @see #contains(Object)
     */
    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    /**
     * Removes all the elements from this collection (optional operation).
     * The collection will be empty after this method returns.
     *
     * @throws UnsupportedOperationException if the {@code clear} operation
     *                                       is not supported by this collection
     */
    @Override
    public void clear() {

    }

    /**
     * Returns an iterator over the elements in this collection.  There are no
     * guarantees concerning the order in which the elements are returned
     * (unless this collection is an instance of some class that provides a
     * guarantee).
     *
     * @return an {@code Iterator} over the elements in this collection
     */
    @Override
    public Iterator<E> iterator() {
        return null;
    }

    /**
     * Returns an array containing all the elements in this collection.
     * If this collection makes any guarantees as to what order its elements
     * are returned by its iterator, this method must return the elements in
     * the same order. The returned array's {@linkplain Class#getComponentType
     * runtime component type} is {@code Object}.
     *
     * <p>The returned array will be "safe" in that no references to it are
     * maintained by this collection.  (In other words, this method must
     * allocate a new array even if this collection is backed by an array).
     * The caller is thus free to modify the returned array.
     *
     * @return an array, whose {@linkplain Class#getComponentType runtime component
     * type} is {@code Object}, containing all the elements in this collection
     * @apiNote This method acts as a bridge between array-based and collection-based APIs.
     * It returns an array whose runtime type is {@code Object[]}.
     * Use {@link #toArray(Object[]) toArray(T[])} to reuse an existing
     * array, or use {@link #toArray(IntFunction)} to control the runtime type
     * of the array.
     */
    @Override
    public Object[] toArray() {
        return getInOrderTraversal().toArray();
    }

    /**
     * Returns an array containing all the elements in this collection;
     * the runtime type of the returned array is that of the specified array.
     * If the collection fits in the specified array, it is returned therein.
     * Otherwise, a new array is allocated with the runtime type of the
     * specified array and the size of this collection.
     *
     * <p>If this collection fits in the specified array with room to spare
     * (i.e., the array has more elements than this collection), the element
     * in the array immediately following the end of the collection is set to
     * {@code null}.  (This is useful in determining the length of this
     * collection <i>only</i> if the caller knows that this collection does
     * not contain any {@code null} elements.)
     *
     * <p>If this collection makes any guarantees as to what order its elements
     * are returned by its iterator, this method must return the elements in
     * the same order.
     *
     * @param a the array into which the elements of this collection are to be
     *          stored, if it is big enough; otherwise, a new array of the same
     *          runtime type is allocated for this purpose.
     * @return an array containing all the elements in this collection
     * @throws ArrayStoreException  if the runtime type of any element in this
     *                              collection is not assignable to the {@linkplain Class#getComponentType
     *                              runtime component type} of the specified array
     * @throws NullPointerException if the specified array is null
     * @apiNote This method acts as a bridge between array-based and collection-based APIs.
     * It allows an existing array to be reused under certain circumstances.
     * Use {@link #toArray()} to create an array whose runtime type is {@code Object[]},
     * or use {@link #toArray(IntFunction)} to control the runtime type of
     * the array.
     *
     * <p>Suppose {@code x} is a collection known to contain only strings.
     * The following code can be used to dump the collection into a previously
     * allocated {@code String} array:
     *
     * <pre>
     *     String[] y = new String[SIZE];
     *     ...
     *     y = x.toArray(y);</pre>
     *
     * <p>The return value is reassigned to the variable {@code y}, because a
     * new array will be allocated and returned if the collection {@code x} has
     * too many elements to fit into the existing array {@code y}.
     *
     * <p>Note that {@code toArray(new Object[0])} is identical in function to
     * {@code toArray()}.
     */
    @Override
    public <T> T[] toArray(T[] a) {
        return null;
    }


    public List<E> getBreadthFirstSearch() {
        List<E> results = new ArrayList<>(size);

        Deque<Node<E>> tempQueue = new LinkedList<>();
        if (root != null) {
            tempQueue.addLast(root);
        }

        while (tempQueue.size() != 0) {
            Node<E> current = tempQueue.removeFirst();
            results.add(current.getData());

            if (current.getLeft() != null) {
                tempQueue.addLast(current.getLeft());
            }

            if (current.getRight() != null) {
                tempQueue.addLast(current.getRight());
            }
        }

        return results;
    }

    public List<E> getDepthFirstSearch() {
        List<E> results = new ArrayList<>(size);

        Deque<Node<E>> tempNodes = new LinkedList<>();
        if (root != null) {
            tempNodes.addFirst(root);
        }

        while (tempNodes.size() != 0) {
            Node<E> current = tempNodes.removeFirst();

            results.add(current.getData());
            if (current.getRight() != null) {
                tempNodes.addFirst(current.getRight());
            }

            if (current.getLeft() != null) {
                tempNodes.addFirst(current.getLeft());
            }
        }

        return results;
    }

    /**
     * BST Pre Order Algorithm
     * <ul>
     *     <li>Visit and process the current Node (Root)</li>
     *     <li>Visit the left sub Tree of the current Node</li>
     *     <li>Visit the right sub Tree of the current Node</li>
     * </ul>
     * @return List of Element from the BST using Pre Order Algorithm
     */
    public List<E> getPreOrderTraversal() {
        List<E> results = new ArrayList<>(size);
        preOrderTraversal(root, results);

        return results;
    }

    private void preOrderTraversal(final Node<E> current, List<E> results) {
        if (current == null) {
            return;
        }

        results.add(current.getData());
        preOrderTraversal(current.getLeft(), results);
        preOrderTraversal(current.getRight(), results);
    }

    /**
     * BST In Order Algorithm
     * <ul>
     *     <li>Visit the left sub Tree of the current Node</li>
     *     <li>Visit and process the current Node (Root)</li>
     *     <li>Visit the right sub Tree of the current Node</li>
     * </ul>
     * @return List of Element from the BST using In Order Algorithm
     */
    public List<E> getInOrderTraversal() {
        List<E> results = new ArrayList<>(size);
        inOrderTraversal(root, results);

        return results;
    }

    private void inOrderTraversal(final Node<E> current, List<E> results) {
        if (current == null) {
            return;
        }

        inOrderTraversal(current.getLeft(), results);
        results.add(current.getData());
        inOrderTraversal(current.getRight(), results);
    }

    /**
     * BST Post Order Algorithm
     * <ul>
     *     <li>Visit the left sub Tree of the current Node</li>
     *     <li>Visit the right sub Tree of the current Node</li>
     *     <li>Visit and process the current Node (Root)</li>
     * </ul>
     * @return List of Element from the BST using Post Order Algorithm
     */
    public List<E> getPostOrderTraversal() {
        List<E> results = new ArrayList<>(size);
        postOrderTraversal(root, results);

        return results;
    }

    private void postOrderTraversal(final Node<E> current, List<E> results) {
        if (current == null) {
            return;
        }

        postOrderTraversal(current.getLeft(), results);
        postOrderTraversal(current.getRight(), results);
        results.add(current.getData());
    }

    /**
     * InOrder Successor Algorithm
     * @param data Data stored in the Node
     * @return InOrder Successor for the given data
     *
     * <ul>
     *     <li>Find the BST Node for the given data</li>
     *     <li>If BST Node for the given data is not available then the Successor is not available</li>
     *     <li>If BST Node for the given data is available, then find the right subtree of the current node</li>
     *     <li>If the right subtree of the current node is not available, one of the ancestors of the current node is the successor</li>
     *     <li>If the right subtree of the current node is not available, find the node with the smallest value in right subtree</li>
     *     <li>Traverse the tree to find then node for the given value</li>
     * </ul>
     */
    public Node<E> getInOrderSuccessor(E data) {
        if (data == null) {
            return null;
        }

        return inOrderSuccessor(root, data);
    }

    private Node<E> inOrderSuccessor(Node<E> current, E data) {
        if (current == null) {
            return null;
        }

        // If the node is found
        // Check the right subtree for the successor
        // If the right subtree is not available return the ancestors as the successor
        if (current.getData().equals(data)) {
            // Find the Parent Node with the Smallest value, higher than the current node
            if (current.getRight() == null) {
                Node<E> ancestor = current.getParent();
                while (ancestor != null && ancestor.getData().compareTo(data) < 0) {
                    ancestor = ancestor.getParent();
                }

                return ancestor;
            }

            Node<E> successor = current.getRight();
            while (successor.getLeft() != null) {
                successor = successor.getLeft();
            }

            return successor;
        }

        if (data.compareTo(current.getData()) < 0) {
            return inOrderSuccessor(current.getLeft(), data);
        }

        return inOrderSuccessor(current.getRight(), data);
    }
}
