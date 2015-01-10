package com.shawn.leecode;

/**
 * User: Shawn cao
 * Date: 15/1/4
 * Time: PM5:54
 */

import java.util.ArrayList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Given two binary trees, write a function to check if they are equal or not.

 Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
 */
public class Q100SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {


        return false;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
