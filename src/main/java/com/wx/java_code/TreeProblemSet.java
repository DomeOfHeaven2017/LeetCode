package com.wx.java_code;

import com.wx.java_code.resource.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wx on 20-6-7
 * Description: LeetCode 树相关问题集合
 * 问题列表：
 *   面试题 04.04. 检查平衡性{@link #isBalanced}
 *  94.二叉树的中序遍历{@link #inorderTraversal}
 *  面试题55 - I  二叉树的深度 {@link #maxDepth}
 **/
public class TreeProblemSet {

    public static void main(String[] args) {

    }

    /**
     * 面试题 04.04. 检查平衡性
     * @param root 二叉树根节点
     * @return 是否平衡
     */
    public boolean isBalanced(TreeNode root) {
        return (root == null) || (isBalanced(root.left) && isBalanced(root.right) && Math.abs(maxDepth(root.left) - maxDepth(root.right)) <= 1);
    }

    /**
     * 面试题55 - I  二叉树的深度
     * @param root 二叉树根节点
     * @return 深度
     */
    public static int maxDepth(TreeNode root) {
        return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     *  94. 二叉树的中序遍历
     * @param root 二叉树根节点
     * @return 中序遍历集合
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        //递归
        List<Integer> list = new ArrayList<>();
        midTraver(root, list);
        //迭代

        return list;
    }

    private static void midTraver(TreeNode root, List<Integer> list) {
        if (root != null) {
            list.add(root.val);
            midTraver(root.left, list);
            midTraver(root.right, list);
        }
    }

}
