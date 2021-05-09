package com.wx.java_code.common;

/**
 * 二叉树节点
 */
public class TreeNode<T> {
    public T val;
    public TreeNode<T> left;
    public TreeNode<T> right;

    public TreeNode(T x) {
        val = x;
    }
}
