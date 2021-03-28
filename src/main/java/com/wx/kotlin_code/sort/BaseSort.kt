package com.wx.kotlin_code.sort

import java.util.Comparator

abstract class BaseSort {

    /**
     * 打印数组
     */
    fun <T> show(msg: String, a : Array<T>){
        println("$msg [ ")
        for ( i in a) {
            print("$i ")
        }
        print(" ]")
    }

    /**
     * 交换数组中两个位置的值
     */
    fun <T> exch(array: Array<T>, i: Int, j: Int) {
        val temp = array[i]
        array[i] = array[j]
        array[j] = temp
    }

    /**
     * 比较两个数据的大小
     */
    fun <T> less(a: T, b: T) : Boolean = a
}