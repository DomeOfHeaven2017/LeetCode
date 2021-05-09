package com.wx.java_code.common;

/**
 * 并查集
 **/
public class UF {
    int[] sites;
    int count;

    public UF(int size) {
        count = size;
        sites = new int[size];
        for (int i = 0; i < size; i++) {
            sites[i] = i;
        }
    }

    public int find(int p) {
        if (p != sites[p]) {
            sites[p] = find(sites[p]);
        }
        return sites[p];
    }

    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);
        if (pRoot == qRoot) {
            return;
        }
        sites[pRoot] = qRoot;
        count--;
    }

    public int getCount() {
        return count;
    }
}
