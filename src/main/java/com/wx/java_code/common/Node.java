package com.wx.java_code.common;

import java.util.List;

/**
 * 树节点
 */
public class Node<T> {
    public T val;
    public List<Node<T>> children;

    public Node() {}

    public Node(T _val) {
        val = _val;
    }

    public Node(T _val, List<Node<T>> _children) {
        val = _val;
        children = _children;
    }
};
