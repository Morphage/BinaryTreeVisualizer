/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jscape.exercise.bst;

/**
 *
 * @author achantreau
 * @param <K>
 */
public class BST<K extends Comparable<K>> {

    private BSTNode<K> root;

    public BST() {
        root = null;
    }

    public void insert(K key) {
        root = insert(root, key);
    }

    private BSTNode<K> insert(BSTNode<K> n, K key) {
        if (n == null) {
            return new BSTNode<>(key, null, null);
        }

        if (key.compareTo(n.getKey()) < 0) {
            n.setLeft(insert(n.getLeft(), key));
            return n;
        } else {
            n.setRight(insert(n.getRight(), key));
            return n;
        }
    }

    public String inOrderTraversal() {
        StringBuilder sb = new StringBuilder();
        inOrderTraversal(root, sb);
        return sb.toString().substring(0, sb.toString().length() - 2);
    }

    private void inOrderTraversal(BSTNode<K> root, StringBuilder sb) {
        if (root != null) {
            inOrderTraversal(root.getLeft(), sb);
            sb.append(root.getKey()).append(", ");
            inOrderTraversal(root.getRight(), sb);
        }
    }

    public String preOrderTraversal() {
        StringBuilder sb = new StringBuilder();
        preOrderTraversal(root, sb);
        return sb.toString().substring(0, sb.toString().length() - 2);
    }

    private void preOrderTraversal(BSTNode<K> root, StringBuilder sb) {
        if (root != null) {
            sb.append(root.getKey()).append(", ");
            preOrderTraversal(root.getLeft(), sb);
            preOrderTraversal(root.getRight(), sb);
        }
    }

    public String postOrderTraversal() {
        StringBuilder sb = new StringBuilder();
        postOrderTraversal(root, sb);
        return sb.toString().substring(0, sb.toString().length() - 2);
    }

    private void postOrderTraversal(BSTNode<K> root, StringBuilder sb) {
        if (root != null) {
            postOrderTraversal(root.getLeft(), sb);
            postOrderTraversal(root.getRight(), sb);
            sb.append(root.getKey()).append(", ");
        }
    }

    public String levelOrderTraversal() {
        StringBuilder sb = new StringBuilder();

        int height = height();
        for (int i = 1; i <= height; i++) {
            sb.append(givenLevelAsString(i)).append(", ");
        }

        return sb.toString().substring(0, sb.toString().length() - 2);
    }

    public String givenLevelAsString(int level) {
        StringBuilder sb = new StringBuilder();
        givenLevelAsString(root, level, sb);
        return sb.toString().substring(0, sb.toString().length() - 2);
    }

    private void givenLevelAsString(BSTNode<K> root, int level, StringBuilder sb) {
        if (root == null) {
            sb.append("");
        } else {
            if (level == 1) {
                sb.append(root.getKey()).append(", ");
            } else if (level > 1) {
                givenLevelAsString(root.getLeft(), level - 1, sb);
                givenLevelAsString(root.getRight(), level - 1, sb);
            }
        }
    }

    public void printAllLevels() {
        for (int i = 1; i <= height(); i++) {
            System.out.println("Level " + i + "= " + givenLevelAsString(i));
        }
    }

    public int height() {
        return height(root);
    }

    private int height(BSTNode<K> root) {
        if (root == null) {
            return 0;
        } else {
            int leftHeight = height(root.getLeft());
            int rightHeight = height(root.getRight());

            if (leftHeight > rightHeight) {
                return (leftHeight + 1);
            } else {
                return (rightHeight + 1);
            }
        }
    }

    public String toJSON() {
        StringBuilder sb = new StringBuilder();
        toJSON(root, sb);
        return sb.toString() + ";";
    }
    
    private void toJSON(BSTNode<K> root, StringBuilder sb) {
        if ((root.getLeft() != null) || (root.getRight() != null)) {
            sb.append("{\n" + "        id: \"")
                    .append(root.getKey()).append("00")
                    .append("\",\n" + "        name: \"")
                    .append(root.getKey())
                    .append("\",\n")
                    .append("        data: {},\n")
                    .append("        children: [");
            if (root.getLeft() != null) {
                toJSON(root.getLeft(), sb);
                sb.append(", ");
            } else {
                sb.append("{\n" + "        id: \"")
                    .append((Integer) root.getKey() - 1).append("90")
                    .append("\",\n" + "        name: \"null")
                    .append("\",\n")
                    .append("        data: {},\n")
                    .append("        children: []},");
            }
            if (root.getRight() != null) {
                toJSON(root.getRight(), sb);
            } else {
                sb.append("{\n" + "        id: \"")
                    .append((Integer) root.getKey() + 1).append("90")
                    .append("\",\n" + "        name: \"null")
                    .append("\",\n")
                    .append("        data: {},\n")
                    .append("        children: []}");
            }
            sb.append("]}");
        } else {
            sb.append("{\n" + "        id: \"")
                    .append(root.getKey()).append("00")
                    .append("\",\n" + "        name: \"")
                    .append(root.getKey())
                    .append("\",\n")
                    .append("        data: {},\n")
                    .append("        children: []\n    }");
        }
    }

    private class BSTNode<K> {

        private K key;
        private BSTNode<K> left;
        private BSTNode<K> right;       

        public BSTNode(K key, BSTNode<K> left, BSTNode<K> right) {
            this.key = key;
            this.left = left;
            this.right = right;
        }

        public K getKey() {
            return key;
        }

        public BSTNode<K> getLeft() {
            return left;
        }

        public void setLeft(BSTNode<K> left) {
            this.left = left;
        }

        public BSTNode<K> getRight() {
            return right;
        }

        public void setRight(BSTNode<K> right) {
            this.right = right;
        }
    }
}
