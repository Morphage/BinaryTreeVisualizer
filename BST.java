
/**
 * Simplistic implementation of Binary Search Trees in Java. Designed as an API
 * to create binary search trees in the code, and to encode them in a JSON
 * format, so that the trees can be visualised in the Javascript
 * BinaryTreeVisualizer.
 *
 * @author achantreau Copyright (c) 2014
 * @param <K> key type of the binary search tree.
 */
public class BST<K extends Comparable<K>> {

    /**
     * Root node of binary search tree.
     */
    private BSTNode<K> root;

    /**
     * Node of binary search tree.
     *
     * @param <K> key type of the binary tree.
     */
    private class BSTNode<K> {

        public K key;
        public BSTNode<K> left;
        public BSTNode<K> right;

        public BSTNode(K key, BSTNode<K> left, BSTNode<K> right) {
            this.key = key;
            this.left = left;
            this.right = right;
        }

        public BSTNode(K key) {
            this(key, null, null);
        }
    }

    /**
     * Inserts a new node in the tree using an iterative approach. The node will
     * be inserted in the appropriate position to maintain the characteristic
     * property of binary search trees.
     *
     * @param key the key of the node to insert.
     */
    public void insert(K key) {
        BSTNode<K> parent = null;
        BSTNode<K> current = root;
        BSTNode<K> newNode = new BSTNode<>(key);

        while (current != null) {
            parent = current;

            if (key.compareTo(current.key) < 0) {
                current = current.left;
            } else if (key.compareTo(current.key) > 0) {
                current = current.right;
            }
        }

        if (parent == null) {
            root = newNode;
        } else {
            if (key.compareTo(parent.key) < 0) {
                parent.left = newNode;
            } else if (key.compareTo(parent.key) > 0) {
                parent.right = newNode;
            }
        }
    }

    /**
     * Returns the space separated String, representing the in-order traversal
     * of the binary search tree.
     * 
     * @return the in-order traversal of the binary search tree.
     */
    public String inOrderTraversal() {
        StringBuilder sb = new StringBuilder();
        inOrderTraversal(root, sb);
        return sb.toString();
    }

    private void inOrderTraversal(BSTNode<K> root, StringBuilder sb) {
        if (root == null) {
            return;
        }

        inOrderTraversal(root.left, sb);
        sb.append(root.key).append(" ");
        inOrderTraversal(root.right, sb);
    }

    /**
     * Returns the space separated String, representing the pre-order traversal
     * of the binary search tree.
     * 
     * @return the pre-order traversal of the binary search tree.
     */
    public String preOrderTraversal() {
        StringBuilder sb = new StringBuilder();
        preOrderTraversal(root, sb);
        return sb.toString();
    }

    private void preOrderTraversal(BSTNode<K> root, StringBuilder sb) {
        if (root == null) {
            return;
        }

        sb.append(root.key).append(" ");
        preOrderTraversal(root.left, sb);
        preOrderTraversal(root.right, sb);
    }

    /**
     * Returns the space separated String, representing the post-order traversal
     * of the binary search tree.
     * 
     * @return the post-order traversal of the binary search tree.
     */
    public String postOrderTraversal() {
        StringBuilder sb = new StringBuilder();
        postOrderTraversal(root, sb);
        return sb.toString();
    }

    private void postOrderTraversal(BSTNode<K> root, StringBuilder sb) {
        if (root == null) {
            return;
        }

        postOrderTraversal(root.left, sb);
        postOrderTraversal(root.right, sb);
        sb.append(root.key).append(" ");
    }

    public String levelOrderTraversal() {
        StringBuilder sb = new StringBuilder();

        int height = height();
        for (int i = 1; i <= height; i++) {
            sb.append(givenLevelAsString(i)).append(" ");
        }

        return sb.toString();
    }

    public String givenLevelAsString(int level) {
        StringBuilder sb = new StringBuilder();
        givenLevelAsString(root, level, sb);
        return sb.toString();
    }

    private void givenLevelAsString(BSTNode<K> root, int level, StringBuilder sb) {
        if (root == null) {
            sb.append("");
        } else {
            if (level == 1) {
                sb.append(root.key).append(" ");
            } else if (level > 1) {
                givenLevelAsString(root.left, level - 1, sb);
                givenLevelAsString(root.right, level - 1, sb);
            }
        }
    }

    public void printAllLevels() {
        for (int i = 1; i <= height(); i++) {
            System.out.println("Level " + i + "= " + givenLevelAsString(i));
        }
    }

    /**
     * Returns the number of nodes in the binary search tree.
     *
     * @return the number of nodes in the binary search tree.
     */
    public int size() {
        return size(root);
    }

    /**
     * Returns the number of nodes of a particular subtree.
     *
     * @param root the root node of the subtree.
     * @return the number of nodes in the subtree.
     */
    private int size(BSTNode<K> root) {
        if (root == null) {
            return 0;
        }

        return size(root.left) + 1 + size(root.right);
    }

    /**
     * Returns the height of the binary search tree.
     *
     * @return the height of the binary search tree.
     */
    public int height() {
        return height(root);
    }

    /**
     * Returns the height of a particular subtree.
     *
     * @param root the root node of the subtree.
     * @return the height of the subtree.
     */
    private int height(BSTNode<K> root) {
        if (root == null) {
            return 0;
        }

        return Math.max(height(root.left) + 1, height(root.right) + 1);
    }

    /**
     * Returns the JSON String representation of the binary search tree.
     *
     * @return the JSON representation of the binary search tree.
     */
    public String toJSON() {
        StringBuilder sb = new StringBuilder();
        toJSON(root, sb);
        return sb.toString() + ";";
    }

    private void toJSON(BSTNode<K> root, StringBuilder sb) {
        if ((root.left != null) || (root.right != null)) {
            sb.append("{\n" + "        id: \"")
                    .append(root.key).append("00")
                    .append("\",\n" + "        name: \"")
                    .append(root.key)
                    .append("\",\n")
                    .append("        data: {},\n")
                    .append("        children: [");
            if (root.left != null) {
                toJSON(root.left, sb);
                sb.append(", ");
            } else {
                sb.append("{\n" + "        id: \"")
                        .append((Integer) root.key - 1).append("90")
                        .append("\",\n" + "        name: \"null")
                        .append("\",\n")
                        .append("        data: {},\n")
                        .append("        children: []},");
            }
            if (root.right != null) {
                toJSON(root.right, sb);
            } else {
                sb.append("{\n" + "        id: \"")
                        .append((Integer) root.key + 1).append("90")
                        .append("\",\n" + "        name: \"null")
                        .append("\",\n")
                        .append("        data: {},\n")
                        .append("        children: []}");
            }
            sb.append("]}");
        } else {
            sb.append("{\n" + "        id: \"")
                    .append(root.key).append("00")
                    .append("\",\n" + "        name: \"")
                    .append(root.key)
                    .append("\",\n")
                    .append("        data: {},\n")
                    .append("        children: []\n    }");
        }
    }

    public static void main(String[] args) {
        BST<Integer> bst = new BST<>();

        bst.insert(30);
        bst.insert(25);
        bst.insert(35);
        bst.insert(20);
        bst.insert(26);
        bst.insert(21);
        bst.insert(27);
        bst.insert(33);
        bst.insert(38);
        bst.insert(40);

        bst.printAllLevels();
        System.out.println(bst.inOrderTraversal());
        System.out.println(bst.toJSON());
        System.out.println(bst.height());
    }
}
