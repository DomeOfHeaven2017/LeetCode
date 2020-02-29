package com.wx.java_code.resource;

import java.util.Stack;

class CQueue {
    //输入,用于数据插入
    private Stack<Integer> inStack;
    //输出栈，用于将尾部数据弹出
    private Stack<Integer> outStack;

    public CQueue() {
        inStack = new Stack<>();
        outStack = new Stack<>();
    }

    /**
     * 从尾部插入，则直接将数据压到输入当中
     * @param value 压入数据
     */
    public void appendTail(int value) {
        inStack.push(value);
    }

    /**
     * 从头部删除，由于需要保持队列的顺序需要注意输入和输出栈之中的数据
     * @return 删除元素
     */
    public int deleteHead() {
        //如果输出栈中有元素，则直接弹出
        if (!outStack.isEmpty()) {
            return outStack.pop();
        }
        //如果输出栈不为空且输入栈也不为空，就需要将输入栈中数据全部压到输出栈中以保持其顺序正确
        while (!inStack.isEmpty()) {
            outStack.push(inStack.pop());
        }
        return outStack.isEmpty() ? -1 : outStack.pop();
    }
}
