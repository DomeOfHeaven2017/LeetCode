package com.wx.java_code.sort;

public class InsertionSort {

    public static void main(String[] args) {
        int[] array = new int[] {3,4,5,1,2,4,6,7,8};
        insertionSort(array);
        System.out.println("[ ");
        for (int a : array) {
            System.out.print(a+" ");
        }
        System.out.print(" ]");
    }

    public static void insertionSort(int[] array) {
        for (int i  = 1; i < array.length ; i++){
            for (int j = i; j > 0; j--) {
                if (array[j] < array[j-1]) {
                    int temp  = array[j];
                    array[j]  = array[j-1];
                    array[j-1] = temp;
                }
            }
        }
    }
}
