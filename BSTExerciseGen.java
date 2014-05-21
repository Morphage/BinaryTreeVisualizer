/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jscape.exercise.bst;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author achantreau
 */
public class BSTExerciseGen {

    private static final String xmlExercise = "<?xml version=\"1.0\"?>\n"
            + "<exercise>\n"
            + "    <display>\n"
            + "        <view>BinaryTree</view>\n"
            + "        <value>";

    private static Random random = new Random();

    private static List<String> traversalOrders;

    public String makeExercise() {
        List<Integer> randomNumbers = getRandomNumbers();
        BST<Integer> bst;

        int type = random.nextInt(3);
        if ((type == 0) || (type == 1)) {
            bst = createBalancedBST(randomNumbers);
        } else {
            bst = createRandomBST(randomNumbers);
        }

        traversalOrders = new ArrayList<>();
        traversalOrders.add(bst.inOrderTraversal());
        traversalOrders.add(bst.preOrderTraversal());
        traversalOrders.add(bst.postOrderTraversal());
        traversalOrders.add(bst.levelOrderTraversal());

        String exercise = xmlExercise;
        
        exercise += bst.toJSON();

        int rand = random.nextInt(4);
        String solutionTraversalOrder = traversalOrders.get(rand);

        String traversalOrder = "";
        if (rand == 0) {
            traversalOrder = "in-order";
        } else if (rand == 1) {
            traversalOrder = "pre-order";
        } else if (rand == 2) {
            traversalOrder = "post-order";
        } else if (rand == 3) {
            traversalOrder = "level-order";
        }

        Collections.shuffle(traversalOrders);

        String rest = "</value>\n"
                + "    </display>\n"
                + "    <display>\n"
                + "        <view>Exercise</view>\n"
                + "        <value>What should be printed if the binary tree is traversed using the "
                + traversalOrder + " algorithm?</value>\n"
                + "        <choice0>" + traversalOrders.get(0) + "</choice0>\n"
                + "        <choice1>" + traversalOrders.get(1) + "</choice1>\n"
                + "        <choice2>" + traversalOrders.get(2) + "</choice2>\n"
                + "        <choice3>" + traversalOrders.get(3) + "</choice3>\n"
                + "        <solution>" + solutionTraversalOrder + "</solution>\n"
                + "    </display>\n"
                + "</exercise>";

        exercise += rest;

        return exercise;
    }

    private BST createBalancedBST(List<Integer> data) {
        BST<Integer> bst = new BST();
        Collections.sort(data);
        insertBalanced(bst, data, 0, data.size());

        return bst;
    }

    private void insertBalanced(BST<Integer> bst, List<Integer> data, int start, int end) {
        int add = (end - start) / 2;
        if ((add == 0) && (start == 0)) {
            bst.insert(data.get(0));
        }
        
        if (add != 0) {
            int mid = start + (end - start) / 2;
            bst.insert(data.get(mid));
            insertBalanced(bst, data, start, mid);
            insertBalanced(bst, data, mid, end);
        }
    }

    private BST createRandomBST(List<Integer> data) {
        BST<Integer> bst = new BST();

        for (Integer i : data) {
            bst.insert(i);
        }

        return bst;
    }

    private List<Integer> getRandomNumbers() {
        ArrayList<Integer> nums = new ArrayList<>();

        for (int i = 1; i < 60; i++) {
            nums.add(i);
        }

        int r = random.nextInt(13) + 3;

        Collections.shuffle(nums);
        return nums.subList(0, r);
    }
}
