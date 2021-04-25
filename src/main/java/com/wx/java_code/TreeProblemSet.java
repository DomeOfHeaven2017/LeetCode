package com.wx.java_code;

import com.wx.java_code.resource.Node;
import com.wx.java_code.resource.TreeNode;

import java.util.*;

/**
 * Created by wx on 20-6-7
 * Description: LeetCode 树相关问题集合
 * 问题列表：
 * 面试题 04.02. 最小高度树{@link #sortedArrayToBST}
 *  面试题 04.04. 检查平衡性{@link #isBalanced}
 *  面试题55 - I  二叉树的深度 {@link #maxDepth}
 *  94.二叉树的中序遍历{@link #inorderTraversal}
 *  98. 验证二叉搜索树 {@link #isValidBST}
 *  100. 相同的树 {@link #isSameTree}
 *  101. 对称二叉树 {@link #isSymmetric}
 *  102. 二叉树的层序遍历{@link #levelOrder}
 *  103. 二叉树的锯齿形层序遍历 {@link #zigzagLevelOrder}
 *  105. 从前序与中序遍历序列构造二叉树 {@link #buildTreeFromPreAndIn}
 *  106. 从中序与后序遍历序列构造二叉树 {@link #buildTreeFromInAndPost}
 *  107. 二叉树的层序遍历 II {@link #levelOrderBottom}
 *  108. 将有序数组转换为二叉搜索树 {@link #sortedArrayToBST}
 *  111. 二叉树的最小深度 {@link #minDepth}
 *  112. 路径总和 {@link #hasPathSum}
 *  113. 路径总和 II {@link #pathSum}
 *  144. 二叉树的前序遍历{@link #preorderTraversal}
 *  145. 二叉树的后序遍历{@link #postorderTraversal}
 *  226. 翻转二叉树 {@link #invertTree}
 *  230. 二叉搜索树中第K小的元素 {@link #}
 *  429. N 叉树的层序遍历{@link #levelOrder}
 *  450. 删除二叉搜索树中的节点 {@link #deleteBSTNode}
 *  559. N 叉树的最大深度{@link #maxDepthN}
 *  589. N 叉树的前序遍历{@link #preorder}
 *   590.N叉树的后续遍历{@link #postorder}
 *   654. 最大二叉树 {@link #constructMaximumBinaryTree}
 *   700. 二叉搜索树中的搜索 {@link #searchBST}
 *   701. 二叉搜索树中的插入操作 {@link #insertIntoBST}
 *   1302. 层数最深叶子节点的和 {@link #deepestLeavesSum}
 *   1379.找出克隆二叉树中的相同节点{@link #getTargetCopy}
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
    public boolean isBalanced(TreeNode<Integer> root) {
        return (root == null) || (isBalanced(root.left) && isBalanced(root.right) && Math.abs(maxDepth(root.left) - maxDepth(root.right)) <= 1);
    }

    /**
     * 面试题55 - I  二叉树的深度
     * @param root 二叉树根节点
     * @return 深度
     */
    public int maxDepth(TreeNode<Integer> root) {
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
     * 98. 验证二叉搜索树
     * @param root 根节点
     * @return 是否合法
     */
    public boolean isValidBST(TreeNode<Integer> root) {
        return isValidBSTHelper(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBSTHelper(TreeNode<Integer> root, long min, long max) {
        if (root == null) return true;
        if (min >= root.val || max <= root.val) {
            return false;
        }
        return isValidBSTHelper(root.left, min, root.val)
                && isValidBSTHelper(root.right, root.val, max);
    }

    /**
     * 100. 相同的树
     * @param p p二叉树
     * @param q q二叉树
     * @return 两个树是否相同
     */
    public boolean isSameTree(TreeNode<Integer> p, TreeNode<Integer> q) {
        return isSameTreeDfs(p, q);
    }

    /**
     * 深度优先搜索方式
     */
    private boolean isSameTreeDfs(TreeNode<Integer> p, TreeNode<Integer> q) {
        if (p == null && q== null) {
            return true;
        }
        if (p == null || q == null) {
            return false;
        }
        return p.val.equals(q.val)
                && isSameTreeDfs(p.left, q.left)
                && isSameTreeDfs(p.right, q.right);
    }

    /**
     * 广度优先搜索方式
     */
    private boolean isSameTreeBfs(TreeNode<Integer> p, TreeNode<Integer> q) {
        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.add(p);
        queue.add(q);
        while (!queue.isEmpty()) {
            TreeNode<Integer> n1 = queue.poll();
            TreeNode<Integer> n2 = queue.poll();
            if (n1 == null && n2 == null) continue;
            if (n1 == null || n2 == null) return false;
            if (!n1.val.equals(n2.val)) return false;
            queue.offer(n1.left);
            queue.offer(n2.left);

            queue.offer(n1.right);
            queue.offer(n2.right);
        }
        return true;
    }

    /**
     * 101. 对称二叉树
     * @param root 根节点
     * @return 是否为对称二叉树
     */
    public boolean isSymmetric(TreeNode<Integer> root) {
        return isSymmetricDfs(root, root);
    }

    /**
     * 递归，深度优先搜索方式
     */
    private boolean isSymmetricDfs(TreeNode<Integer> n1, TreeNode<Integer> n2) {
        //n1 == n2 == null
        if (n1 == null && n2 == null) {
            return true;
        }
        //n1 ,n2有且只有一个为null
        if (n1 == null || n2 == null) {
            return false;
        }
        //n1,n2不为null,对称需要其根节点相等并且左右子树镜像对称
        return (n1.val.equals(n2.val))
                && isSymmetricDfs(n1.left, n2.right)
                && isSymmetricDfs(n1.right, n2.left);
    }
    /**
     * 队列，广度优先搜索方式
     * 每次将其两个子树的左右节点交叉入队，然后每次弹出两个节点（p的左节点和q的右节点），比较其大小
     */
    private boolean isSymmetricBfs(TreeNode<Integer> root) {
        Queue<TreeNode<Integer>> queue = new LinkedList<>();
        queue.add(root);
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode<Integer> p = queue.poll();
            TreeNode<Integer> q = queue.poll();
            if (p == null && q == null) continue;
            if (p == null || q == null) return false;
            if (!p.val.equals(q.val)) return false;
            //交叉入队
            queue.add(p.left);
            queue.add(q.right);

            queue.add(p.right);
            queue.add(q.left);
        }
        return true;
    }

    /**
     * 230. 二叉搜索树中第K小的元素
     * @param root 根节点
     * @param k 第k小
     * @return 第k小的元素
     */
    int kthCount = 0;
    int kthAns = -1;
    public int kthSmallest(TreeNode<Integer> root, int k) {
        kthSmallestDfs(root, k);
        return result;
    }

    /**
     * 二叉搜索树中第k小元素递归解法
     * 因为二叉搜索树的中序遍历即为排序序列，所以第k小即为中序遍历序列的第k个值
     */
    private void kthSmallestDfs(TreeNode<Integer> root, int k) {
        if (root == null) {
            return;
        }
        kthSmallestDfs(root.left, k);
        kthCount ++;
        if (kthCount == k) {
            kthAns = root.val;
            return;
        }
        kthSmallestDfs(root.right, k);
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
        levelOrderDfs(1, root, result, false);
        return result;
    }

    /**
     * 二叉树层序遍历递归方式
     * @param level 层级
     * @param node 节点
     * @param lists 结果集
     * @param isZigzag 是否是锯齿遍历，兼容103题
     */
    private void levelOrderDfs(int level, TreeNode<Integer> node, List<List<Integer>> lists, boolean isZigzag) {
        if (lists.size() < level) {
            lists.add(new ArrayList<>());
        }
        if (isZigzag && level % 2 == 0) {
            lists.get(level - 1).add(0, node.val);
        } else {
            lists.get(level - 1).add(node.val);
        }
        if (node.left != null) {
            levelOrderDfs(level + 1, node.left, lists, isZigzag);
        }
        if (node.right != null) {
            levelOrderDfs(level + 1, node.right, lists, isZigzag);
        }
    }

    /**
     * 103. 二叉树的锯齿形层序遍历
     * @param root 根节点
     * @return 锯齿排列
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode<Integer> root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        levelOrderDfs(1, root, result, true);
        return root;
    }

    /**
     * 105. 从前序与中序遍历序列构造二叉树
     * @param preorder 前序遍历序列
     * @param inorder 中序遍历序列
     * @return 二叉树根节点
     */
    public TreeNode<Integer> buildTreeFromPreAndIn(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null
                || preorder.length != inorder.length) {
            return null;
        }
        //将中序遍历序列中值与索引的关系保存到列表中，方便进行查找
        Map<Integer, Integer> map = new HashMap<>(inorder.length);
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTreeFromPreAndInDfs(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1, map);
    }

    /**
     * 从前序与中序遍历序列构造二叉树递归辅助方法
     * @param preorder 前序遍历序列
     * @param preLeft 前序遍历序列子区间的左边界
     * @param preRight 前序遍历序列子区间的有边界
     * @param inorder 中序遍历序列
     * @param inLeft 中序遍历序列子区间的左边界
     * @param inRight 中序遍历序列子区间的有边界
     * @param map 中序值索引列表《中序节点值， 中序节点索引》
     */
    private TreeNode<Integer> buildTreeFromPreAndInDfs(int[] preorder, int preLeft, int preRight, int[] inorder, int inLeft, int inRight, Map<Integer, Integer> map) {
        if (preLeft > preRight || inLeft > inRight) {
            return null;
        }
        //前序遍历的第一个值为根节点的值
        int rootVal = preorder[preLeft];
        TreeNode<Integer> root = new TreeNode<>(rootVal);
        //获取中序遍历序列中根节点的索引
        int index = map.get(rootVal);
        //根据根节点在前序和中序遍历序列中的位置，递归构建左右子树
        root.left = buildTreeFromPreAndInDfs(preorder, preLeft + 1, index - inLeft + preLeft, inorder, inLeft, index - 1, map);
        root.right = buildTreeFromPreAndInDfs(preorder, index - inLeft + preLeft + 1, preRight, inorder, index + 1, inRight, map);
        return root;
    }

    /**
     * 106. 从中序与后序遍历序列构造二叉树
     * @param inorder 中序遍历序列
     * @param postorder 后序遍历序列
     * @return 二叉树根节点
     */
    public TreeNode<Integer> buildTreeFromInAndPost(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || inorder.length != postorder.length) {
            return null;
        }
        Map<Integer, Integer> map = new HashMap<>(inorder.length);
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTreeFromInAndPostDfs(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1, map);
    }

    /**
     * 从中序与后序遍历序列构造二叉树递归辅助方法
     * @param inorder 前序遍历序列
     * @param inLeft 前序遍历序列子区间的左边界
     * @param inRight 前序遍历序列子区间的有边界
     * @param postorder 中序遍历序列
     * @param postLeft 中序遍历序列子区间的左边界
     * @param postRight 中序遍历序列子区间的有边界
     * @param map 中序值索引列表《中序节点值， 中序节点索引》
     */
    private TreeNode<Integer> buildTreeFromInAndPostDfs(int[] inorder, int inLeft, int inRight, int[] postorder, int postLeft, int postRight, Map<Integer, Integer> map) {
        if (inLeft > inRight || postLeft > postRight) {
            return null;
        }
        //后序遍历的最后一个值为根节点值
        int val = postorder[postRight];
        TreeNode<Integer> root = new TreeNode<>(val);
        int index = map.get(val);
        //根据根节点在中序和后序遍历序列中的位置，递归构建左右子树
        root.left = buildTreeFromInAndPostDfs(inorder, inLeft, index - 1, postorder, postLeft, index - 1 - inLeft + postLeft, map);
        root.right = buildTreeFromInAndPostDfs(inorder, index + 1, inRight, postorder, index - inLeft + postLeft, postRight - 1, map);
        return root;
    }

    /**
     * 107. 二叉树的层序遍历 II
     * @param root 根节点
     * @return 层序遍历结果
     */
    public List<List<Integer>> levelOrderBottom(TreeNode<Integer> root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root != null) {
            Queue<TreeNode<Integer>> queue  = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                List<Integer> list = new ArrayList<>();
                for (int i = 0; i < size; i++) {
                    TreeNode<Integer> node = queue.poll();
                    if (node != null) {
                        list.add(node.val);
                        if (node.left != null) {
                            queue.add(node.left);
                        }
                        if (node.right != null) {
                            queue.add(node.right);
                        }
                    }
                }
                result.add(list);
            }
            Collections.reverse(result);
        }
        return result;
    }

    /**
     * 108. 将有序数组转换为二叉搜索树
     * @param nums 有序数组
     * @return BST
     */
    public TreeNode<Integer> sortedArrayToBST(int[] nums) {
        return arrayToBSTHelper(nums, 0, nums.length - 1);
    }

    private TreeNode<Integer> arrayToBSTHelper(int[] nums, int left, int right) {
        if (left > right) return null;
        int mid = (left - right) / 2 + right;
        TreeNode<Integer> root = new TreeNode<>(nums[mid]);
        root.left = arrayToBSTHelper(nums, left, mid - 1);
        root.right = arrayToBSTHelper(nums, mid + 1, right);
        return root;
    }

    /**
     * 111. 二叉树的最小深度
     * @param root 根节点
     * @return 最小深度
     */
    public int minDepth(TreeNode<Integer> root) {
        return minDepthDfs(root);
    }

    /**
     * 深度优先搜索方式
     */
    private int minDepthDfs(TreeNode<Integer> root) {
        if (root == null) return 0;
        int left = minDepthDfs(root.left);
        int right = minDepthDfs(root.right);

        if (left == 0) return left + 1;
        if (right == 0) return right + 1;
        return Math.min(left, right) + 1;
    }

    /**
     * 广度优先搜索方式
     */
    private int minDepthBfs(TreeNode<Integer> root) {
        int mindepth = 0;
        if (root != null) {
            Queue<TreeNode<Integer>> queue = new LinkedList<>();
            queue.add(root);
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i = 0; i < size; i++) {
                    TreeNode<Integer> node = queue.poll();
                    if (node.left == null && node.right == null) {
                        return mindepth + 1;
                    }
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }
                mindepth++;
            }
        }
        return mindepth;
    }

    /**
     * 112. 路径总和
     * @param root 根节点
     * @param targetSum 目标路径和
     * @return 是否满足要求
     */
    public boolean hasPathSum(TreeNode<Integer> root, int targetSum) {
        return hasPathSumDfs(root, targetSum);
    }

    /**
     * 路径总和递归解法
     */
    private boolean hasPathSumDfs(TreeNode<Integer> root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }
        return hasPathSumDfs(root.left, targetSum - root.val)
                || hasPathSumDfs(root.right, targetSum - root.val);
    }

    /**
     * 113. 路径总和 II
     * @param root 根节点
     * @param targetSum 目标路径和
     * @return 满足要求的序列
     */
    public List<List<Integer>> pathSum(TreeNode<Integer> root, int targetSum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        return pathSumDfs(root, targetSum, result, list);
    }

    /**
     * 深度优先搜索，回溯算法
     */
    private List<List<Integer>> pathSumDfs(TreeNode<Integer> root, int target, List<List<Integer>> result, List<Integer> list) {
        if (root == null) {
            return result;
        }
        list.add(root.val);
        target -= root.val;
        if (root.left == null && root.right == null && target == 0) {
            result.add(new ArrayList<>(list));
        }
        pathSumDfs(root.left, target, result, list);
        pathSumDfs(root.right, target, result, list);
        list.remove(list.size() - 1);
        return result;
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
        }
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
     * 226. 翻转二叉树
     * @param root 根节点
     * @return 翻转后的根节点
     */
    public TreeNode<Integer> invertTree(TreeNode<Integer> root) {
        if (root == null) {
            return null;
        }
        TreeNode<Integer> left = invertTree(root.left);
        TreeNode<Integer> right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
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
     * 450. 删除二叉搜索树中的节点
     * @param root 根节点
     * @param key 待删除节点值
     * @return 删除后BST根节点
     */
    public TreeNode<Integer> deleteBSTNode(TreeNode<Integer> root, int key) {
        if (root == null) return null;
        if (root.val == key) {
            //找到待删除节点
            if (root.left == null) {
                //左节点为空，使用右节点替换
                return root.right;
            }
            if (root.right == null) {
                //右节点为空，使用左节点替换
                return root.left;
            }
            //两个子节点都不为空，需要删除后保持二叉搜索树有效
            //可以找到左子树中的最大值来替换当前值
            TreeNode<Integer> maxNode = findMaxNodeFromLeft(root.left);
            root.val = maxNode.val;
            root.left = deleteBSTNode(root.left, maxNode.val);
            //也可以找到右子树中的最小值来替换当前值
            TreeNode<Integer> minNode = findMinNodeFromRight(root.right);
            root.val = minNode.val;
            root.right = deleteBSTNode(root.right, minNode.val);
        } else if (root.val > key) {
            root.left = deleteBSTNode(root.left, key);
        } else if (root.val < key) {
            root.right = deleteBSTNode(root.right, key);
        }
        return root;
    }

    private TreeNode<Integer> findMaxNodeFromLeft(TreeNode<Integer> node) {
        //左子树中最大的就是最右边的节点
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    private TreeNode<Integer> findMinNodeFromRight(TreeNode<Integer> node) {
        //右子树中最小的就是最左边的节点
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    /**
     * 559. N 叉树的最大深度
     * @param root 根节点
     * @return 最大深度
     */
    public int maxDepthN(Node<Integer> root) {
        return maxDepthNDfs(root);
    }

    /**
     * 深度优先搜索方式
     */
    private int maxDepthNDfs(Node<Integer> node) {
        if (node == null) {
            return 0;
        }
        int maxDepth = 1;
        for (Node<Integer> n : node.children) {
            maxDepth = Math.max(maxDepth, 1 + maxDepthNDfs(n));
        }
        return maxDepth;
    }

    /**
     * 广度优先算法方式
     */
    private int maxDepthNBfs(Node<Integer> node) {
        int depth = 0;
        if (node != null) {
            LinkedList<Node<Integer>> temp = new LinkedList<>();
            temp.push(node);
            while (!temp.isEmpty()) {
                int size = temp.size();
                for (int i = 0; i < size; i++) {
                    Node<Integer> n = temp.pop();
                    temp.addAll(n.children);
                }
                depth++;
            }
        }
        return depth;
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
     * 654. 最大二叉树
     * @param nums 无序数组
     * @return 最大二叉树根节点
     */
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructMaxBtHelper(nums, 0, nums.length - 1);
    }

    /**
     * 最大二叉树递归解法
     * @param nums 源数组
     * @param start 左边界
     * @param end 右边界
     */
    private TreeNode<Integer> constructMaxBtHelper(int[] nums, int start, int end) {
        if (start > end) return null;
        //找数组中的最大值的索引
        int index = start;
        for (int i = start + 1; i <= end; i ++) {
            if (nums[i] > nums[index]) {
                index = i;
            }
        }
        //根据最大值构建父节点
        TreeNode<Integer> node = new TreeNode<>(nums[index]);
        //根据左边序列递归构建左子节点
        node.left = constructMaxBtHelper(nums, start, index - 1);
        //根据右边序列递归构建右子节点
        node.right = constructMaxBtHelper(nums, index + 1, end);
        return node;
    }

    /**
     * 700. 二叉搜索树中的搜索
     * @param root 根节点
     * @param val 目标值
     * @return 目标节点
     */
    public TreeNode<Integer> searchBST(TreeNode<Integer> root, int target) {
        if (root == null) return null;
        if (root.val == target) {
            return root;
        } else if (root.val < target) {
            return searchBST(root.right, target);
        } else if (root.val > target) {
            return searchBST(root.left, target);
        }
        return null;
    }

    /**
     * 701. 二叉搜索树中的插入操作
     * @param root 根节点
     * @param val 插入节点值
     * @return 插入后的根节点
     */
    public TreeNode<Integer> insertIntoBST(TreeNode<Integer> root, int val) {
        if (root == null) return new TreeNode<>(val);
        if (root.val < val) {
            root.right =  insertIntoBST(root.right, val);
        } else if (root.val > val) {
            root.left =  insertIntoBST(root.left, val);
        }
        return root;
    }

    /**
     * 迭代方式
     */
    private TreeNode<Integer> searchBSTIterator(TreeNode<Integer> root, int target) {
        while (root != null) {
            if (root.val == target) {
                return root;
            } else if (root.val > target) {
                root = root.left;
            } else if (root.val < target) {
                root = root.right;
            }
        }
        return null;
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
    public final TreeNode<Integer> getTargetCopy(final TreeNode<Integer> original, final TreeNode<Integer> cloned, final TreeNode<Integer> target) {
        //由于两棵树相同，只判断一个即可
        if (original == null) {
            return null;
        }
        //访问根节点，比较地址是否相同
        if (original == target) {
            return cloned;
        }
        //递归遍历左子节点
        TreeNode<Integer> node = getTargetCopy(original.left, cloned.left, target);
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