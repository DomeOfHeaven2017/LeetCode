package com.wx.kotlin_code.sort


/**
 * 选择排序算法
 **/
class SelectionSort <T> : BaseSort() {

    fun selectSort(array: Array<T>) {
        show("排序前：", array)
        for (i in array.indices) {
            var min = i
            for(j in i + 1 until  array.size) {
                if (less(array[j], array[min])) {
                    min = j
                }
                if (min != i) {
                    exch(array, min, j)
                }
            }
        }
        show("排序后：", array)
    }

}

fun main() {
    val selectionSort = SelectionSort<Int>()
    val data = arrayOf(3,4,5,1,2,4,6,7,8)
    selectionSort.selectSort(data)
}