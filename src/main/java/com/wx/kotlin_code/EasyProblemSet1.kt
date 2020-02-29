package com.wx.kotlin_code

/**
 * Created by wx on 19-3-12
 * Description:leetcode简单问题记录:Kotlin代码
 * 问题编号:1,
 **/

//两数之和
fun twoSum(nums: IntArray, target: Int): IntArray {

    val result = hashMapOf<Int,Int>()
    for (i in 0 until nums.size){
        if(result.containsKey(target - nums[i])){
            return intArrayOf(result.get(target - nums[i])!!,i)
        }else{
            result.put(nums[i],i)
        }
    }
    return intArrayOf(-1,-1)
}

//整数反转Integer.MAX_VALUE
fun reverse(x: Int): Int {

    var result: Long = 0

    var xAbs = Math.abs(x)

    if (xAbs > Integer.MAX_VALUE) {
        return 0
    }

    while (!xAbs.equals(0L)) {
        val temp = xAbs % 10
        result = result * 10 + temp
        xAbs = xAbs / 10
    }

    if (x > 0 && result < Integer.MAX_VALUE) {
        return result.toInt()
    } else if (x < 0 && result <= Integer.MAX_VALUE) {
        return (-result).toInt()
    }
    return 0
}


fun main() {

    //两数之和
    twoSum(intArrayOf(2, 7, 11, 15),9).forEach {
        print("$it ")
    }
}