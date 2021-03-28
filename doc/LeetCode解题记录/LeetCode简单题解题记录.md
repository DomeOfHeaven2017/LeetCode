[TOC]

#### 本文档记录了解决的leetcode题目的思路与重要代码

#### (1) 两数之和

##### 题目描述:

```
给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。

你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。

示例:

给定 nums = [2, 7, 11, 15], target = 9

因为 nums[0] + nums[1] = 2 + 7 = 9
所以返回 [0, 1]
```

##### 解题思路:

> 遍历数组,使用一个`Map`来保存比较过的值,可以避免重复比较;每次检查`Map`中有无`targe -nums[i]`,如果有则直接输出对应的索引值;没有则将`num[i]`加到`Map`中,由于最后要求输出索引值,所以`Map`中的键为`num[i]`,值为`i`

##### 代码:

```java
//Java
public int[] twoSum(int[] nums, int target) {
        HashMap<Integer,Integer> result = new HashMap<>();

        for(int i = 0 ;i < nums.length ; i++){
            if(result.containsKey(target - nums[i])){
                return new int[]{result.get(target - nums[i]),i};
            }else{
                result.put(nums[i],i);
            }
        }
        return new int[]{-1,-1};
    }
```

```kotlin
//Kotlin
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
```

```python
#Python3
def twoSum(self, nums: List[int], target: int) -> List[int]:
        result = {}
        for i in range(len(nums)):
            if target - nums[i] not in result:
                result[nums[i]] = i
            else:
                return [result[target - nums[i]], i]
```

#### (7) 整数反转

##### 题目描述:

```
给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。

示例 1:

输入: 123
输出: 321
 示例 2:

输入: -123
输出: -321
示例 3:

输入: 120
输出: 21
注意:

假设我们的环境只能存储得下 32 位的有符号整数，则其数值范围为 [−2^31,  2^31 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。
```

##### 解题思路:

> 每次从所给数中取低位数字,然后将其乘10向高位移动;当所给数为0时则反转完毕;
> 
> 特别要注意数值范围的判断.

##### 代码:

```java
//Java
public static int reverse(int x) {

    long result = 0;

    long xAbs = Math.abs(new Long(x));

    if(xAbs > Integer.MAX_VALUE){
        return 0;
    }

    while (xAbs != 0){
        long temp = xAbs % 10;
        result = result * 10 + temp;
        xAbs = xAbs / 10;
    }

    if(x > 0 && result < Integer.MAX_VALUE){
        return (int) result;
    }else if(x < 0 && result <= Integer.MAX_VALUE){
        return (int) -result;
    }
    return 0;
}
```

```kotlin
//Kotlin
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
```

```python
#Python3
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
```

#### (9)回文数

##### 题目描述:

```text
判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。

示例 1:

输入: 121
输出: true
示例 2:

输入: -121
输出: false
解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
示例 3:

输入: 10
输出: false
解释: 从右向左读, 为 01 。因此它不是一个回文数。
```

##### 解题思路:

> 将所给数字进行整数反转,然后比较反转后的数字与原数字是否相等

##### 代码:

```java
//Java
public static int reverse(int x) {

    long result = 0;

    long xAbs = Math.abs(new Long(x));

    if(xAbs > Integer.MAX_VALUE){
        return 0;
    }

    while (xAbs != 0){
        long temp = xAbs % 10;
        result = result * 10 + temp;
        xAbs = xAbs / 10;
    }

    if(x > 0 && result < Integer.MAX_VALUE){
        return (int) result;
    }else if(x < 0 && result <= Integer.MAX_VALUE){
        return (int) -result;
    }
    return 0;
}

public static boolean isPalindrome(int x) {
    if(x < 0 ){
        return false;
    }
    return x == reverse(x);
}
```

```kotlin
//Kotlin
```

```python
#Python3
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
```

#### (13)罗马数字转整数

##### 题目描述:

```text
罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。

字符          数值
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。

通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：

I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
给定一个罗马数字，将其转换成整数。输入确保在 1 到 3999 的范围内。

示例 1:

输入: "III"
输出: 3
示例 2:

输入: "IV"
输出: 4
示例 3:

输入: "IX"
输出: 9
示例 4:

输入: "LVIII"
输出: 58
解释: L = 50, V= 5, III = 3.
示例 5:

输入: "MCMXCIV"
输出: 1994
解释: M = 1000, CM = 900, XC = 90, IV = 4.
```

##### 解题思路:

##### 代码:

```java
//Java
```

```kotlin
//Kotlin
```

```python
#Python3
```

#### (14)最长公共前缀

##### 题目描述:

```text
编写一个函数来查找字符串数组中的最长公共前缀。

如果不存在公共前缀，返回空字符串 ""。

示例 1:

输入: ["flower","flow","flight"]
输出: "fl"
示例 2:

输入: ["dog","racecar","car"]
输出: ""
解释: 输入不存在公共前缀。
```

##### 解题思路:

##### 代码:

```java
//Java
```

```kotlin
//Kotlin
```

```python
#Python3
```

#### (20)有效的括号

##### 题目描述:

```text
给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。

有效字符串需满足：

左括号必须用相同类型的右括号闭合。
左括号必须以正确的顺序闭合。
注意空字符串可被认为是有效字符串。

示例 1:

输入: "()"
输出: true
示例 2:

输入: "()[]{}"
输出: true
示例 3:

输入: "(]"
输出: false
示例 4:

输入: "([)]"
输出: false
示例 5:

输入: "{[]}"
输出: true
```

##### 解题思路:

#####　代码:

```java
//Java
```

```Kotlin
//Kotlin
```

```Python
#Python3
```

#### (21)合并两个有序链表

##### 题目描述:

```text
将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 

示例：

输入：1->2->4, 1->3->4
输出：1->1->2->3->4->4
```

##### 解题思路:

##### 代码:

```java
  //Java
```

```Kotlin
  //Kotlin
```

```Python
  #Python3
```

#### (26)删除排序数组中的重复项

##### 题目描述:

```text
将两个有序链表合并为一个新的有序链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 

  示例：

  输入：1->2->4, 1->3->4
  输出：1->1->2->3->4->4 
```

##### 解题思路:

##### 代码:

```java
//Java
```

```kotlin
//Kotlin
```

```python
#Python3
```

#### (27)移除元素

##### 题目描述:

```text
给定一个数组 nums 和一个值 val，你需要原地移除所有数值等于 val 的元素，返回移除后数组的新长度。

不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。

元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。

示例 1:

给定 nums = [3,2,2,3], val = 3,

函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。

你不需要考虑数组中超出新长度后面的元素。
示例 2:

给定 nums = [0,1,2,2,3,0,4,2], val = 2,

函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。

注意这五个元素可为任意顺序。

你不需要考虑数组中超出新长度后面的元素。
说明:

为什么返回数值是整数，但输出的答案是数组呢?

请注意，输入数组是以“引用”方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。

你可以想象内部操作如下:

// nums 是以“引用”方式传递的。也就是说，不对实参作任何拷贝
int len = removeElement(nums, val);

// 在函数里修改输入数组对于调用者是可见的。
// 根据你的函数返回的长度, 它会打印出数组中该长度范围内的所有元素。
for (int i = 0; i < len; i++) {
    print(nums[i]);
}
```

##### 解题思路:

> 声明两个变量`first`,`last`
> 
>     first : 数组遍历位置
> 
>     last : 元素替换位置
> 
> 遍历数组如果匹配到所给值则将当前位置的数组元素和last位置的数组元素进行交换,最后返回`last + 1`

##### 代码:

```java
//Java
public int removeElement(int[] nums, int val) {
    int first = 0;
    int last = nums.length -1;
    while(first <= last){
        if(nums[first] == val){
            int temp = nums[first];
            nums[first] = nums[last];
            nums[last] = temp;
            last -= 1;
        }else{
            first += 1;
        }
    }
    return last + 1;
}
```

```kotlin
//Kotlin
```

```python
#Python3
 def removeElement(nums: List[int], val: int) -> int:
        first,last = 0,len(nums) - 1
        while first <= last:
            if nums[first] == val:
                nums[first],nums[last] = nums[last],nums[first]
                last -= 1
            else:
                first +=1
        return last + 1
```



#### (198)打家劫舍

##### 问题描述

```
你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
示例 1:
输入: [1,2,3,1]
输出: 4
解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
     偷窃到的最高金额 = 1 + 3 = 4 。
示例 2:
输入: [2,7,9,3,1]
输出: 12
解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
     偷窃到的最高金额 = 2 + 9 + 1 = 12 。
来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/house-robber
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```



##### 解题思路

> 

##### 代码

```java
/**
     * 面试题198
     * 打家劫舍
     * @param nums
     * @return
     */
public static int rob(int[] nums) {
    int[] dp = new int[nums.length + 2];
    for (int i = 0; i < nums.length; i++) {
        dp[i + 2] = Math.max(dp[i] + nums[i], dp[i + 1]);
    }
    return dp[nums.length + 1];
}

```

#### (213)打家劫舍II

```
你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
示例 1:
输入: [2,3,2]
输出: 3
解释: 你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
示例 2:
输入: [1,2,3,1]
输出: 4
解释: 你可以先偷窃 1 号房屋（金额 = 1），然后偷窃 3 号房屋（金额 = 3）。
     偷窃到的最高金额 = 1 + 3 = 4 。
来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/house-robber-ii
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```

##### 解题思路

##### 代码

```java
/**
     * 面试题 213
     * 打家劫舍2
     * @param nums
     * @return
     */
public static int rob2(int[] nums) {
    if (nums.length == 0) return 0;
    if (nums.length == 1) return nums[0];
    int length = nums.length;
    return Math.max(rob(Arrays.copyOfRange(nums, 0, length - 1)), rob(Arrays.copyOfRange(nums, 1, length)));
}
```



#### (237)删除链表中的节点

##### 问题描述

```
请编写一个函数，使其可以删除某个链表中给定的（非末尾）节点，你将只被给定要求被删除的节点。
现有一个链表 -- head = [4,5,1,9]，它可以表示为:

示例 1:
输入: head = [4,5,1,9], node = 5
输出: [4,1,9]
解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
示例 2:
输入: head = [4,5,1,9], node = 1
输出: [4,5,9]
解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
说明:
链表至少包含两个节点。
链表中所有节点的值都是唯一的。
给定的节点为非末尾节点并且一定是链表中的一个有效节点。
不要从你的函数中返回任何结果。
来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/delete-node-in-a-linked-list
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```

##### 解题思路

> 由于所给条件只有当前要删除的节点，无法通过遍历进行删除；但是可以通过将后面的节点覆盖当前节点实现删除操作

##### 代码

```java
/**
     * 面试题237
     * 删除链表中的节点
     * @param node 待删除节点
     */
public void deleteNode(ListNode node) {
    node.val = node.next.val;
    node.next = node.next.next;
}
```



#### (344)反转字符串

##### 题目描述

```
编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
示例 1：
输入：["h","e","l","l","o"]
输出：["o","l","l","e","h"]
示例 2：
输入：["H","a","n","n","a","h"]
输出：["h","a","n","n","a","H"]
来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/reverse-string
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```

##### 解题思路

> 使用前后两个索引，分别交换两个索引的值

##### 代码

```java
/**
     * 面试题344
     * 反转字符串
     * @param s 原字符串数组
     */
public void reverseString(char[] s) {
    int start = 0, end = s.length - 1;
    char temp = 0;
    while (start < end) {
        temp = s[start];
        s[start] = s[end];
        s[end] = temp;
        start ++;
        end -- ;
    }
}
```





#### (1295)统计位数为偶数的数字

##### 题目描述:

```
给你一个整数数组 nums，请你返回其中位数为 偶数 的数字的个数。

示例 1：
输入：nums = [12,345,2,6,7896]
输出：2
解释：
12 是 2 位数字（位数为偶数） 
345 是 3 位数字（位数为奇数）  
2 是 1 位数字（位数为奇数） 
6 是 1 位数字 位数为奇数） 
7896 是 4 位数字（位数为偶数）  
因此只有 12 和 7896 是位数为偶数的数字

示例 2：
输入：nums = [555,901,482,1771]
输出：1 
解释： 
只有 1771 是位数为偶数的数字。

提示：
1 <= nums.length <= 500
1 <= nums[i] <= 10^5
```

##### 解题思路:

> 遍历所给数组,如果元素的位数为偶数,则计数器加1
> 
> $\log_ {10} S$的值为`S`数字的`length - 1`

##### 代码:

```java
//java   
public static int findNumbers(int[] nums) {
    if (nums == null || nums.length <= 0){
        return 0;
    }
    int count = 0;
    int j = 0;
    for (int i : nums) {
        if (i < 0 || i > 100000) {
            continue;
        }
        if (((int) Math.log10(i) + 1) % 2 == 0) {
            count += 1;
        }
        j = 0;
    }
    return count;
}

```

#### (LCP1)猜数字

##### 问题描述:

```
  小A 和 小B 在玩猜数字。小B 每次从 1, 2, 3 中随机选择一个，小A 每次也从 1, 2, 3 中选择一个猜。他们一共进行三次这个游戏，请返回 小A 猜对了几次？

  输入的guess数组为 小A 每次的猜测，answer数组为 小B 每次的选择。guess和answer的长度都等于3。

  示例 1：
  输入：guess = [1,2,3], answer = [1,2,3]
  输出：3
  解释：小A 每次都猜对了。

  示例 2：
  输入：guess = [2,2,3], answer = [3,2,1]
  输出：1
  解释：小A 只猜对了第二次。

  限制：
  guess的长度 = 3
  answer的长度 = 3
  guess的元素取值为 {1, 2, 3} 之一。
  answer的元素取值为 {1, 2, 3} 之一。
```

##### 解题思路:

  > 比较两个数组对应位置的数字是否相等

##### 代码:

  ```java
  public static int guessNumber(int[] guess, int[] answer) {
      if (guess == null || guess.length != 3
          || answer == null || answer.length != 3) {
          return 0;
      }
      int count = 0, i = 0;
      while (i < 3) {
          if (guess[i] == answer[i]) {
              count += 1;
          }
          i += 1;
      }
      return count;
  }
  ```

#### (1221)分割平衡字符串

##### 问题描述

```
在一个「平衡字符串」中，'L' 和 'R' 字符的数量是相同的。
给出一个平衡字符串 s，请你将它分割成尽可能多的平衡字符串。
返回可以通过分割得到的平衡字符串的最大数量。

示例 1：
输入：s = "RLRRLLRLRL"
输出：4
解释：s 可以分割为 "RL", "RRLL", "RL", "RL", 每个子字符串中都包含相同数量的 'L' 和 'R'。
示例 2：
输入：s = "RLLLLRRRLR"
输出：3
解释：s 可以分割为 "RL", "LLLRRR", "LR", 每个子字符串中都包含相同数量的 'L' 和 'R'。
示例 3：
输入：s = "LLLLRRRR"
输出：1
解释：s 只能保持原样 "LLLLRRRR".

提示：
1 <= s.length <= 1000
s[i] = 'L' 或 'R'
来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/split-a-string-in-balanced-strings
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```

##### 解题思路

> 声明一个变量`balance`，表示平衡值
>
> - 当遇到一个`L`字符时，平衡值加一
> - 遇到一个`R`字符时，平衡值减一
>
> 则当`balance`的值为0时即为平衡状态，此时计数加一。

##### 代码

```java
/**
     * 面试题1221
     * 分割平衡字符串
     * @param s 源字符串
     * @return 最大数量
     */
public int balancedStringSplit(String s) {
    int balance = 0,count = 0;
    for (char c : s.toCharArray()) {
        if (c == 'L') {
            balance ++;
        } else if (c == 'R'){
            balance --;
        }
        if (balance == 0) {
            count ++;
        }
    }
    return count;
}
```



#### (1281)整数的各位积和之差

##### 题目描述:

```
给你一个整数 n，请你帮忙计算并返回该整数「各位数字之积」与「各位数字之和」的差。

示例 1：
输入：n = 234
输出：15 
解释：
各位数之积 = 2 * 3 * 4 = 24 
各位数之和 = 2 + 3 + 4 = 9 
结果 = 24 - 9 = 15

示例 2：
输入：n = 4421
输出：21
解释： 
各位数之积 = 4 * 4 * 2 * 1 = 32 
各位数之和 = 4 + 4 + 2 + 1 = 11 
结果 = 32 - 11 = 21
 
提示：
1 <= n <= 10^5
```

##### 解题思路:

> 取数字的每一位,计算其乘积,和,然后计算积和之差

##### 代码:

```java
//java
public static int subtractProductAndSum(int n) {
    if (n < 1 || n > 100000) {
        return 0;
    }
    int sum = 0, product = 1, i = 0;
    while (n != 0) {
        i = n % 10;
        sum += i;
        product *= i;
        n /= 10;
    }
    return product - sum;
}
```

#### (1290)二进制链表转整数

##### 问题描述

```
给你一个单链表的引用结点 head。链表中每个结点的值不是 0 就是 1。已知此链表是一个整数数字的二进制表示形式。

请你返回该链表所表示数字的 十进制值 。
示例 1：
输入：head = [1,0,1]
输出：5
解释：二进制数 (101) 转化为十进制数 (5)
示例 2：
输入：head = [0]
输出：0
示例 3：
输入：head = [1]
输出：1
示例 4：
输入：head = [1,0,0,1,0,0,1,1,1,0,0,0,0,0,0]
输出：18880
来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/convert-binary-number-in-a-linked-list-to-integer
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
```

##### 解题思路

> 遍历链表得到二进制数，然后将二进制转为十进制

##### 代码

```java
/**
     * 面试题1290
     * 二进制链表转整数
     * @param head 头结点
     * @return 整数
     */
public int getDecimalValue(ListNode head) {
    StringBuilder sb = new StringBuilder(32);
    while (head != null) {
        sb.append(head.val);
        head = head.next;
    }
    return Integer.parseInt(sb.toString(),2);
}
```



#### (1313)解压缩编码列表

##### 问题描述:

```
给你一个以行程长度编码压缩的整数列表 nums 。

考虑每对相邻的两个元素 [a, b] = [nums[2*i], nums[2*i+1]] （其中 i >= 0 ），每一对都表示解压后有 a 个值为 b 的元素。
请你返回解压后的列表。

示例：
输入：nums = [1,2,3,4]
输出：[2,4,4,4]
解释：第一对 [1,2] 代表着 2 的出现频次为 1，所以生成数组 [2]。
第二对 [3,4] 代表着 4 的出现频次为 3，所以生成数组 [4,4,4]。
最后将它们串联到一起 [2] + [4,4,4,4] = [2,4,4,4]。
 
提示：
2 <= nums.length <= 100
nums.length % 2 == 0
1 <= nums[i] <= 100
```

##### 解题思路:

>先计算出解压后的数据数组长度,然后遍历数组填充数据
>
>*Java中使用ArrayList虽然不用获取长度,但由于其容量需要动态增加所以会增加耗时*

##### 代码:

```java
//java
public static int[] decompressRLElist(int[] nums) {
    int length = 0, i = 0, j = 0, index = 0;
    for (; i < nums.length ; i+=2) {
        length += nums[i];
    }
    int[] result = new int[length];
    for (i = 0 ; i < nums.length ; i+=2){
        for (j = 0; j < nums[i] ; j++){
            result[index] = nums[i + 1];
            index++;
        }
    }
    return result;
}
```


#### (1342)将数字变成 0 的操作次数

##### 问题描述:

```
给你一个非负整数 num ，请你返回将它变成 0 所需要的步数。 如果当前数字是偶数，你需要把它除以 2 ；否则，减去 1 。

示例 1：
输入：num = 14
输出：6
解释：
步骤 1) 14 是偶数，除以 2 得到 7 。
步骤 2） 7 是奇数，减 1 得到 6 。
步骤 3） 6 是偶数，除以 2 得到 3 。
步骤 4） 3 是奇数，减 1 得到 2 。
步骤 5） 2 是偶数，除以 2 得到 1 。
步骤 6） 1 是奇数，减 1 得到 0 。

示例 2：
输入：num = 8
输出：4
解释：
步骤 1） 8 是偶数，除以 2 得到 4 。
步骤 2） 4 是偶数，除以 2 得到 2 。
步骤 3） 2 是偶数，除以 2 得到 1 。
步骤 4） 1 是奇数，减 1 得到 0 。

示例 3：
输入：num = 123
输出：12
 
提示：
0 <= num <= 10^6
```

##### 解题思路:

> 根据要求偶数除2,奇数减一,循环遍历即可
>
> 可以使用位运算来进行优化
>
> 减一 : n & -2
>
> 除二 : n >> 1

##### 代码:

```java
public int numberOfSteps (int num) {
    if (num < 0 || num > 1000000) {
        return 0;
    }
    int count = 0;
    while (num != 0) {
        if ((num & 1) == 1) {
            num = num & -2;
        } else {
            num = num >> 1;
        }
        count += 1;
    }
    return count;
}
```