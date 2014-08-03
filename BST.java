
import java.util.LinkedList;
import java.util.Queue;

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
    public class BSTNode<K> {

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
            } else {
                /* Node is already in the tree. */
                return;
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
    public String inorder() {
        StringBuilder sb = new StringBuilder();
        inorder(root, sb);
        return sb.toString();
    }

    private void inorder(BSTNode<K> root, StringBuilder sb) {
        if (root != null) {
            inorder(root.left, sb);
            sb.append(root.key).append(" ");
            inorder(root.right, sb);
        }
    }

    /**
     * Returns the space separated String, representing the pre-order traversal
     * of the binary search tree.
     *
     * @return the pre-order traversal of the binary search tree.
     */
    public String preorder() {
        StringBuilder sb = new StringBuilder();
        preorder(root, sb);
        return sb.toString();
    }

    private void preorder(BSTNode<K> root, StringBuilder sb) {
        if (root != null) {
            sb.append(root.key).append(" ");
            preorder(root.left, sb);
            preorder(root.right, sb);
        }
    }

    /**
     * Returns the space separated String, representing the post-order traversal
     * of the binary search tree.
     *
     * @return the post-order traversal of the binary search tree.
     */
    public String postorder() {
        StringBuilder sb = new StringBuilder();
        postorder(root, sb);
        return sb.toString();
    }

    private void postorder(BSTNode<K> root, StringBuilder sb) {
        if (root != null) {
            postorder(root.left, sb);
            postorder(root.right, sb);
            sb.append(root.key).append(" ");
        }
    }

    /**
     * Returns the space separated String, representing the level-order
     * traversal of the binary search tree. (Implementation based on
     * breadth-first search)
     *
     * @return the level-order traversal of the binary search tree.
     */
    public String levelorder() {
        StringBuilder sb = new StringBuilder();
        Queue<BSTNode<K>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            BSTNode<K> current = queue.remove();
            sb.append(current.key).append(" ");

            if (current.left != null) {
                queue.add(current.left);
            }

            if (current.right != null) {
                queue.add(current.right);
            }
        }

        return sb.toString().trim();
    }

    /**
     * Returns the space separated String, representing the level-order
     * traversal of the binary search tree. The levels are numbered from 1, i.e.
     * the root node of the binary search tree is at level 1.
     *
     * @param level the desired level in the binary search tree.
     * @return the level-order traversal of the binary search tree.
     */
    public String levelToString(int level) {
        StringBuilder sb = new StringBuilder();
        levelToString(root, level, sb);
        return sb.toString();
    }

    private void levelToString(BSTNode<K> root, int level, StringBuilder sb) {
        if (root != null) {
            if (level == 1) {
                sb.append(root.key).append(" ");
            } else if (level > 1) {
                levelToString(root.left, level - 1, sb);
                levelToString(root.right, level - 1, sb);
            }
        }
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
            sb.append("{id: \"").append(root.key).append("00")
                    .append("\", name: \"").append(root.key)
                    .append("\", data: {}, children: [");
            if (root.left != null) {
                toJSON(root.left, sb);
                sb.append(", ");
            } else {
                sb.append("{id: \"").append((Integer) root.key - 1).append("90")
                        .append("\", name: \"null\", data: {}, children: []}, ");
            }
            if (root.right != null) {
                toJSON(root.right, sb);
            } else {
                sb.append("id: \"").append((Integer) root.key + 1).append("90")
                        .append("\", name: \"null\", data: {}, children: []}");
            }
            sb.append("]}");
        } else {
            sb.append("{id: \"").append(root.key).append("00")
                    .append("\", name: \"").append(root.key)
                    .append("\", data: {}, children: []}");
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
     * Returns the minimum key of the binary search tree.
     *
     * @return the key of the minimum element.
     */
    public BSTNode<K> minimum() {
        BSTNode<K> current = root;

        while (current.left != null) {
            current = current.left;
        }

        return current;
    }

    /**
     * Returns the maximum key of the binary search tree.
     *
     * @return the key of the maximum element.
     */
    public BSTNode<K> maximum() {
        BSTNode<K> current = root;

        while (current.right != null) {
            current = current.right;
        }

        return current;
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

        System.out.println(bst.inorder());
        System.out.println(bst.levelorder());
        System.out.println(bst.toJSON());
        System.out.println(bst.levelToString(3));
        System.out.println(bst.minimum().key);
        System.out.println(bst.maximum().key);
    }
}
