package com.wx.java_code;

import com.wx.java_code.resource.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by wx on 20-6-7
 * Description: LeetCode 树相关问题集合
 * 问题列表：
 *   面试题 04.04. 检查平衡性{@link #isBalanced}
 *  面试题55 - I  二叉树的深度 {@link #maxDepth}
 *  94.二叉树的中序遍历{@link #inorderTraversal}
 *  102. 二叉树的层序遍历{@link #levelOrder}
 *  144. 二叉树的前序遍历{@link #preorderTraversal}
 *  145. 二叉树的后序遍历{@link #postorderTraversal}
 *
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
    public List<Integer> inorderTraversal(TreeNode root) {
        //递归
        List<Integer> list = new ArrayList<>();
//        treeNodeTraversal(list, root, 1);
//        return list;
        //迭代
        Stack<TreeNode> stack = new Stack<>();
        if (root == null) {
            return list;
        }
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();

        }
        return list;
    }

    /**
     * 102. 二叉树的层序遍历
     * @param root 二叉树根节点
     * @return 层序遍历集合
     */
    public List<List<Integer>> levelOrder(TreeNode root) {

    }

    /**
     * 144. 二叉树的前序遍历
     * @param root 二叉树根节点
     * @return 前序遍历集合
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        //递归
        List<Integer> list = new ArrayList<>();
//        treeNodeTraversal(list, root, 0);
        //迭代
        Stack<TreeNode> stack = new Stack<>();
        if (root == null) {
            return list;
        }
        stack.add(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            list.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return list;
    }

    /**
     * 145. 二叉树的后序遍历
     * @param root 二叉树根节点
     * @return 后序序遍历集合
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        treeNodeTraversal(list, root, 2);
        return list;
    }

    /**
     * 递归方式遍历二叉树
     * @param list 遍历集合
     * @param root 根节点
     * @param flag 遍历方式
     *             0   - >  前序遍历
     *             1   - >  中序遍历
     *             2  - >  后序遍历
     */
    private void treeNodeTraversal(List<Integer> list, TreeNode root, int flag){
        if (root == null) {
            return;
        };
        switch (flag) {
            case 0:
                list.add(root.val);
                treeNodeTraversal(list, root.left, 0);
                treeNodeTraversal(list, root.right, 0);
                break;
            case 1:
                treeNodeTraversal(list, root.left, 1);
                list.add(root.val);
                treeNodeTraversal(list, root.right, 1);
                break;
            case 2:
                treeNodeTraversal(list, root.left, 2);
                treeNodeTraversal(list, root.right, 2);
                list.add(root.val);
                break;
        }
    }

}
