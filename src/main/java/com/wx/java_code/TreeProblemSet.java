package com.wx.java_code;

import com.wx.java_code.resource.Node;
import com.wx.java_code.resource.TreeNode;

import java.util.*;

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
 *   590.N叉树的后续遍历{@link #postorder}
 *   1379.找出克隆二叉树中的相同节点{@link #getTargetCopy}
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
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                list.add(node.val);
                node = node.right;
            }
        }
        return list;
    }

    /**
     * 102. 二叉树的层序遍历
     * @param root 二叉树根节点
     * @return 层序遍历集合
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        //非递归方式
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        while ( !queue.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            int size = queue.size();
            for (int i = 0 ; i < size ; i ++) {
                TreeNode node = queue.poll();
                if (node !=null) {
                    temp.add(node.val);
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }
            }
            result.add(temp);
        }

        //递归方式
        levelOrderDfs(1, root, result);
        return result;
    }

    private void levelOrderDfs(int level, TreeNode node, List<List<Integer>> lists) {
        if (lists.size() < level) {
            lists.add(new ArrayList<>());
        }
        lists.get(level - 1).add(node.val);
        if (node.left != null) {
            levelOrderDfs(level + 1, node.left, lists);
        }
        if (node.right != null) {
            levelOrderDfs(level + 1, node.right, lists);
        }
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
        if (root == null) {
            return list;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
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
        //递归
        List<Integer> list = new ArrayList<>();
        treeNodeTraversal(list, root, 2);
        //非递归
        if (root == null) {
            return list;
        }
        Stack<TreeNode>  stack = new Stack<>();
        Stack<Integer> temp = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            temp.push(node.val);
            if (node.left != null) {
                stack.push(node.left);
            }
            if (node.right != null) {
                stack.push(node.right);
            }
        }
        while (!temp.isEmpty()) {
            list.add(temp.pop());
        }
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

    /**
     *  590.N叉树的后序遍历
     * @param root 根节点
     * @return 遍历数组
     */
    List<Integer> result = new ArrayList<>();
    public List<Integer> postorder(Node root) {
        if (root == null) {
            return result;
        }
        //递归
        if (root.children != null) {
            root.children.forEach(this::postorder);
        }
        result.add(root.val);
        //非递归
        return result;
    }

    /**
     *  1379. 找出克隆二叉树中的相同节点
     * @param original 原始树
     * @param cloned 克隆树
     * @param target 目标节点
     * @return 克隆树目标节点
     *  两个树同时进行遍历，对地址进行比较
     */
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        //由于两棵树相同，只判断一个即可
        if (original == null) {
            return null;
        }
        //访问根节点，比较地址是否相同
        if (original == target) {
            return cloned;
        }
        //递归遍历左子节点
        TreeNode node = getTargetCopy(original.left, cloned.left, target);
        if (node != null) {
            return node;
        }
        //递归遍历右子节点
        node = getTargetCopy(original.right, cloned.right, target);
        if (node != null) {
            return node;
        }
        return null;
    }


}