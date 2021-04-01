package com.wx.java_code.resource;

import java.util.List;

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
