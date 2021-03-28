package com.wx.java_code.sort;

public class SelectionSort {


    public static void main(String[] args) {
        int[] array = new int[] {3,4,5,1,2,4,6,7,8};
        selectionSort(array);
        System.out.println("[ ");
        for (int a : array) {
            System.out.print(a+" ");
        }
        System.out.print(" ]");
    }

    public static void selectionSort(int[] array) {
        for (int i = 0; i < array.length; i++){
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[min]) {
                    min = j;
                }
            }
            if (min != i) {
                int temp = array[i];
                array[i] = array[min];
                array[min] = temp;
            }
        }
    }
}
