package com.wx.java_code;

import com.wx.java_code.resource.Node;
import com.wx.java_code.resource.TreeNode;

import java.util.*;
import java.util.function.Consumer;

/**
 * Created by wx on 20-6-7
 * Description: LeetCode 树相关问题集合
 * 问题列表：
 * 面试题 04.02. 最小高度树{@link #sortedArrayToBST}
 *  面试题 04.04. 检查平衡性{@link #isBalanced}
 *  面试题55 - I  二叉树的深度 {@link #maxDepth}
 *  94.二叉树的中序遍历{@link #inorderTraversal}
 *  102. 二叉树的层序遍历{@link #levelOrder}
 *  144. 二叉树的前序遍历{@link #preorderTraversal}
 *  145. 二叉树的后序遍历{@link #postorderTraversal}
 *  429. N 叉树的层序遍历{@link #levelOrder}
 *  589. N 叉树的前序遍历{@link #preorder}
 *   590.N叉树的后续遍历{@link #postorder}
 *   1302. 层数最深叶子节点的和 {@link #deepestLeavesSum}
 *   1379.找出克隆二叉树中的相同节点{@link #getTargetCopy}
 *
 **/
public class TreeProblemSet {

    public static void main(String[] args) {

    }

    /**
     * 面试题 04.02. 最小高度树
     *
     * @param nums
     * @return
     */
    public TreeNode<Integer> sortedArrayToBST(int[] nums) {

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
    public int maxDepth(TreeNode root) {
        return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     *  94. 二叉树的中序遍历
     * @param root 二叉树根节点
     * @return 中序遍历集合
     */
    public List<Integer> inorderTraversal(TreeNode<Integer> root) {
        //递归
        List<Integer> list = new ArrayList<>();
//        treeNodeTraversal(list, root, 1);
//        return list;
        //迭代
        if (root == null) {
            return list;
        }
        Stack<TreeNode<Integer>> stack = new Stack<>();
        TreeNode<Integer> node = root;
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
    public List<List<Integer>> levelOrder(TreeNode<Integer> root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        //非递归方式
        Queue<TreeNode<Integer>> queue = new ArrayDeque<>();
        queue.add(root);
        while ( !queue.isEmpty()) {
            List<Integer> temp = new ArrayList<>();
            int size = queue.size();
            for (int i = 0 ; i < size ; i ++) {
                TreeNode<Integer> node = queue.poll();
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

    private void levelOrderDfs(int level, TreeNode<Integer> node, List<List<Integer>> lists) {
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
    public List<Integer> preorderTraversal(TreeNode<Integer> root) {
        //递归
        List<Integer> list = new ArrayList<>();
//        treeNodeTraversal(list, root, 0);
        //迭代
        if (root == null) {
            return list;
        }
        Stack<TreeNode<Integer>> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode<Integer> node = stack.pop();
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
    public List<Integer> postorderTraversal(TreeNode<Integer> root) {
        //递归
        List<Integer> list = new ArrayList<>();
        treeNodeTraversal(list, root, 2);
        //非递归
        if (root == null) {
            return list;
        }
        Stack<TreeNode<Integer>>  stack = new Stack<>();
        Stack<Integer> temp = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode<Integer> node = stack.pop();
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
    private void treeNodeTraversal(List<Integer> list, TreeNode<Integer> root, int flag){
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
     * 429. N 叉树的层序遍历
     * @param root 根节点
     * @return 遍历数组
     */
    public List<List<Integer>> levelOrderN(Node<Integer> root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root != null) {
            levelOrderNRecursive(result, root, 0);
        }
        return result;
    }

    /**
     * N叉树遍历递归方式
     */
    public void levelOrderNRecursive(List<List<Integer>> result, Node<Integer> node, int level) {
        if (result.size() <= level) {
            result.add(new ArrayList<>());
        }
        result.get(level).add(node.val);
        for (Node<Integer> n : node.children) {
            levelOrderNRecursive(result, n, level + 1);
        }
    }

    /**
     * N叉树遍历迭代方式
     */
    private List<List<Integer>> levelOrderNIterator(Node<Integer> root) {
        List<List<Integer>> result = new ArrayList<>();
        LinkedList<Node<Integer>> temp = new LinkedList<>();
        if (root != null) {
            temp.add(root);
           while (!temp.isEmpty()) {
               int size = temp.size();
               List<Integer> list = new ArrayList<>(size);
               for (int i = 0; i < size; i++) {
                   Node<Integer> node = temp.pop();
                   list.add(node.val);
                   temp.addAll(node.children);
               }
               result.add(list);
           }
        }
        return result;
    }

    /**
     * 589. N 叉树的前序遍历
     * @param root 根节点
     * @return 前序遍历序列
     */
    public List<Integer> preorder(Node<Integer> root) {
        return preorderRecursive(root, new ArrayList<>());
    }

    /**
     * 递归方式
     */
    private List<Integer> preorderRecursive(Node<Integer> root, List<Integer> result) {
        if (root == null ) return result;
        result.add(root.val);
        if (root.children != null) {
            root.children.forEach(node -> preorderRecursive(node, result));
        }
        return result;
    }
    /**
     * 迭代方式
     */
    private List<Integer> preorderIterator(Node<Integer> root) {
        List<Integer> result = new ArrayList<>();
        if (root != null) {
            Stack<Node<Integer>> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                Node<Integer> node = stack.pop();
                result.add(node.val);
                List<Node<Integer>> children = node.children;
                if (children != null && children.size() > 0) {
                    for (int i = children.size() - 1; i >= 0; i--) {
                        stack.push(children.get(i));
                    }
                }
            }
        }
        return result;
    }

    /**
     *  590.N叉树的后序遍历
     * @param root 根节点
     * @return 遍历数组
     */
    List<Integer> result = new ArrayList<>();
    public List<Integer> postorder(Node<Integer> root) {
        return postorderRecurize(root, new ArrayList<>());
    }
    /**
     * 递归方式
     */
    private List<Integer> postorderRecurize(Node<Integer> root, List<Integer> result) {
        if (root == null) return result;
        if (root.children != null) {
            root.children.forEach(node -> postorderRecurize(node, result));
        }
        result.add(root.val);
        return result;
    }
    /**
     * 迭代方式
     */
    private List<Integer> postorderIterator(Node<Integer> root) {
        List<Integer> result = new ArrayList<>();
        if (root != null) {
            Stack<Node<Integer>> stack = new Stack<>();
            stack.add(root);
            while (!stack.isEmpty()) {
                Node<Integer> node = stack.pop();
                result.add(node.val);
                List<Node<Integer>> children = node.children;
                if (children != null && !children.isEmpty()) {
                    for (Node<Integer> n : children) {
                        stack.push(n);
                    }
                }
            }
            Collections.reverse(result);
        }
        return result;
    }
    /**
     * 双栈方式
     */
    private List<Integer> postorderDoubleStack(Node<Integer> root) {
        List<Integer> result = new ArrayList<>();
        if (root != null) {
            Stack<Node<Integer>> stack = new Stack<>();
            Stack<Integer> temp = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                Node<Integer> node = stack.pop();
                temp.push(node.val);
                if (node.children != null) {
                    for (int i = node.children.size() - 1; i >= 0; i--) {
                        stack.push(node.children.get(i));
                    }
                }
            }
            while (!temp.isEmpty()) {
                result.add(temp.pop());
            }
        }
        return result;
    }

    /**
     * 1302. 层数最深叶子节点的和
     * @param root 根节点
     * @return 最深叶子节点的和
     */
    private int maxDepth = -1;
    private int sum = 0;
    public int deepestLeavesSum(TreeNode root) {
        return deepestLeavesSumDfs(root, 0);
    }

    /**
     * 深度优先搜索方式
     */
    private int deepestLeavesSumDfs(TreeNode<Integer> root, int depth) {
        if (root == null) return 0;
        if (maxDepth < depth) {
            maxDepth = depth;
            sum = root.val;
        } else {
            sum += root.val;
        }
        if (root.left != null) {
            deepestLeavesSumDfs(root.left, depth + 1);
        }
        if (root.right != null) {
            deepestLeavesSumDfs(root.right, depth + 1);
        }
        return sum;
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

    /**
     * 根据后序遍历数组还原二叉搜索树
     * @param posArr  后序遍历数组
     * @return 二叉树头结点
     */
    public TreeNode<Integer> posArrayToBst(int[] posArr) {
        return handleArrayToBst(posArr, 0, posArr.length - 1);
    }

    private TreeNode<Integer> handleArrayToBst(int[] posArr, int start, int end) {
        //无子结点
        if (start > end) {
            return null;
        }
        //后序遍历，最后面是父节点
        TreeNode<Integer> node = new TreeNode<>(posArr[end]);
        if (start == end) {
            return  node;
        }
        int m = start - 1;
        for (int i = start; i < end; i ++) {
            if (posArr[i] < posArr[end]) {
                m = i;
            }
        }
        node.left = handleArrayToBst(posArr, start, m);
        node.right = handleArrayToBst(posArr, m + 1, end - 1);
        return node;
    }



}