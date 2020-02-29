# Created by wx on 19-3-11
# Description:leetcode简单问题记录:Java代码
# 问题编号:1,


# 两数之和
from typing import List


def twoSum(nums: List[int], target: int) -> List[int]:
    result = {}
    for i in range(len(nums)):
        if target - nums[i] not in result:
            result[nums[i]] = i
        else:
            return [result[target - nums[i]], i]


# 整数反转
def reverse(x: int) -> int:
    result = 0
    num = abs(x)
    while num != 0:
        temp = num % 10
        result = result * 10 + temp
        num = int(num / 10)
    if x > 0 and result < 2147483647:
        return result
    elif x < 0 and result < 2147483647:
        return -result
    return 0


# 回文数
def isPalindrome(x: int) -> bool:
    if x < 0:
        return False
    result = 0
    x_temp = x
    while x_temp != 0:
        temp = x_temp % 10
        result = result * 10 + temp
        x_temp = int(x_temp / 10)
    return result == x


# 最长公共前缀
def longestCommonPrefix(strs: List[str]) -> str:
    # if not strs:
    #     return ""
    # for i in range(len(strs[0])):
    #     for item in strs[1:]:
    #         if i >= len(item) or strs[0][i] != item[i]:
    #             return strs[0][:i]
    # return strs[0]
    result = ""
    i = 0

    while True:
        try:
            sets = set(string[i] for string in strs)
            if len(sets) == 1:
                result = sets.pop()
                i += 1
            else:break
        except Exception as e:
            break
    return result


# 罗马数字转整数
def romanToInt(s: str) -> int:
    result = 0
    dict = {"I": 1, "V": 5, "X": 10, "L": 50, "C": 100, "D": 500, "M": 1000}
    for i in range(len(s)):
        if i > 0 and dict[s[i]] > dict[s[i - 1]]:
            result = result + dict[s[i]] - 2 * dict[s[i - 1]]
        else:
            result = result + dict[s[i]]
    return result

#有效的括号
def isValid(s: str) -> bool:
    dict = {'(': ')', ']': '[', '{': '}'}
    stack = []
    for i in range(len(s)):
        if

if __name__ == '__main__':
    print(romanToInt("MCMXCIV"))
