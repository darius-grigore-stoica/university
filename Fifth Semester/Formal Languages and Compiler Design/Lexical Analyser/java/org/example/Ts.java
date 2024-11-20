
package org.example;

import java.util.concurrent.atomic.AtomicInteger;

class Node {
    String key;
    int index;
    Node left, right;

    Node(String key, int index) {
        this.key = key;
        this.index = index;
        this.left = this.right = null;
    }
}

public class Ts {
    private Node root;
    private final AtomicInteger currentIndex = new AtomicInteger(0);

    // Adaugă un atom în arbore
    public int addAtom(String element) {
        if (root == null) {
            root = new Node(element, currentIndex.getAndIncrement());
            return root.index;
        }
        return addRecursive(root, element);
    }

    private int addRecursive(Node current, String element) {
        int comparison = element.compareTo(current.key);

        if (comparison == 0) {
            // Element deja existent
            return current.index;
        } else if (comparison < 0) {
            // Inserare în subarborele stâng
            if (current.left == null) {
                current.left = new Node(element, currentIndex.getAndIncrement());
                return current.left.index;
            }
            return addRecursive(current.left, element);
        } else {
            // Inserare în subarborele drept
            if (current.right == null) {
                current.right = new Node(element, currentIndex.getAndIncrement());
                return current.right.index;
            }
            return addRecursive(current.right, element);
        }
    }

    // Găsește un atom în arbore
    public Integer findAtom(String element) {
        return findRecursive(root, element);
    }

    private Integer findRecursive(Node current, String element) {
        if (current == null) {
            return null;
        }

        int comparison = element.compareTo(current.key);

        if (comparison == 0) {
            return current.index;
        } else if (comparison < 0) {
            return findRecursive(current.left, element);
        } else {
            return findRecursive(current.right, element);
        }
    }

    // Afișează arborele (inorder traversal pentru ordine lexicografică)
    public String printMaxNotNull() {
        StringBuilder result = new StringBuilder();
        printInOrder(root, result);
        return result.toString();
    }

    private void printInOrder(Node node, StringBuilder result) {
        if (node != null) {
            printInOrder(node.left, result);
            result.append(String.format("cod TS: %d; atom: %s\n", node.index, node.key));
            printInOrder(node.right, result);
        }
    }
}