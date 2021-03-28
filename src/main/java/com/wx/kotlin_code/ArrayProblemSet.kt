package com.wx.kotlin_code

import kotlin.math.min

/**
 * Created by wx on 21-3-16
 * Description: LeetCode 数组相关问题集合
 *  350. 两个数组的交集II ${@link #intersect}
 */


/**
 * 350. 两个数组的交集II
 * @param nums1 集合1
 * @param nums2 集合2
 * @return 两个集合的交集
 */
fun intersect(nums1: IntArray, nums2: IntArray): IntArray {
    val result = IntArray(Math.min(nums1.size, nums2.size))
    nums1.sort()
    nums2.sort()
    var i = 0
    var j = 0
    var k = 0
    while (i < nums1.size && j < nums2.size) {
        if (nums1[i] < nums2[j]) {
            i++
        } else if (nums1[i] > nums2[j]) {
            j++
        } else {
            result[k++] = nums1[i]
            i++
            j++
        }
    }
    return result
}