[TOC]

### 剑指Offer问题解题记录

---

#### (05)替换空格

##### 问题描述

```
请实现一个函数，把字符串 s 中的每个空格替换成"%20"。

示例 1：
输入：s = "We are happy."
输出："We%20are%20happy."
 
限制：
0 <= s 的长度 <= 10000
```

##### 解题思路

> 1.遍历字符串，遇到空格就加入“%20”，遇到非空格直接加入。
>
> 2.通过正则表达式进行匹配替换（java中的replace API，但是提交发现比较耗时）

##### 代码

```java
public static String replaceSpace(String s) {
    StringBuilder sb = new StringBuilder(s.length() * 3);
    for (char c : s.toCharArray()) {
        if (c == ' '){
            sb.append("%20");
        } else {
            sb.append(c);
        }
    }
    return sb.toString();
//  return s.replaceAll(" ", "%20");
}
```



#### (06)从尾到头打印链表

##### 问题描述

```
输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。

示例 1：
输入：head = [1,3,2]
输出：[2,3,1]
 
限制：
0 <= 链表长度 <= 10000
```

##### 解题思路

>问题难点在于链表无法知道长度，以及无法进行随机访问
>
>1. 使用栈或类似栈先进后出机制的数据结构来保存链表遍历的结果，最后将结果转换成数组即可，由于Java中有装箱拆箱机制，所以会增加耗时
>2. 遍历两次链表，第一次取得链表的长度，以此来初始化数组长度；第二次遍历利用数组可以随机访问的特点，从数组的尾到头进行赋值。

##### 代码

```java
public int[] reversePrint(ListNode head) {
    int count = 0;
    ListNode temp = head;
    while (temp != null) {
        count ++;
        temp = temp.next;
    }
    int[] result = new int[count];
    while (head != null) {
        result[count - 1] = head.val;
        count -- ;
        head = head.next;
    }
    return result;
}
```



#### (09)用两个栈实现队列

##### 问题描述

```
用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )

示例 1：
输入：
["CQueue","appendTail","deleteHead","deleteHead"]
[[],[3],[],[]]
输出：[null,null,3,-1]

示例 2：
输入：
["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
[[],[],[5],[2],[],[]]
输出：[null,-1,null,null,5,2]

提示：
1 <= values <= 10000
最多会对 appendTail、deleteHead 进行 10000 次调用
```

##### 解题思路

>首先必须记住栈的特点是：先进后出；队列的特点是先入先出
>
>以此为基础则数据分别从两个栈先后进入弹出则可以实现队列的特点。
>
>核心思想是两个栈一个为输出栈，一个为输入栈，在插入数据时可以直接将数据压到输入栈中；在删除头数据时就需要对不同情况进行分别处理：
>
>1.输出栈不为空：直接弹出
>
>2.输出栈为空，输入栈不为空：先将输入栈中的数据压到输出栈中，再从输出栈中弹出数据
>
>3.输出栈为孔，输入栈为孔：则整个队列无数据，直接返回-1

##### 代码

```java
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
}class CQueue {
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
```



#### (15)二进制中1的个数

##### 问题描述

```
请实现一个函数，输入一个整数，输出该数二进制表示中 1 的个数。例如，把 9 表示成二进制是 1001，有 2 位是 1。因此，如果输入 9，则该函数输出 2。

示例 1：
输入：00000000000000000000000000001011
输出：3
解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。

示例 2：
输入：00000000000000000000000010000000
输出：1
解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。

示例 3：
输入：11111111111111111111111111111101
输出：31
解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
```

##### 解题思路

>使用位运算，因为**n & n - 1**相当于把n中的一个1给抵消掉，所以我们可以循环做与操作来计算n中1的个数

##### 代码

```java
public static int hammingWeight(int n) {
    int count = 0;
    while (n != 0) {
        n &= (n - 1);
        count ++;
    }
    return count;
}
```



#### (17)打印从1到最大的n位数

##### 问题描述

```
输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数 999。

示例 1:
输入: n = 1
输出: [1,2,3,4,5,6,7,8,9]

说明：
用返回一个整数列表来代替打印
n 为正整数
```

##### 解题思路

>首先根据所给数字需要计算数组大小,即最大数字,然后遍历打印即可
>
>$10^x - 1$即为x位最大数
>
>优化空间:由于n为正整数,可以将计算最大数的过程转化为数组映射,来减少计算的耗时

##### 代码

```java
public int[] printNumbers(int n) {
    int size = (int) Math.pow(10, n) - 1;
    int[] result = new int[size];
    for (int i = 0 ;i < size ; i++){
        result[i] = i + 1;
    }
    return result;
}
```



#### (22)链表中倒数第k个节点

##### 问题描述

```
输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。例如，一个链表有6个节点，从头节点开始，它们的值依次是1、2、3、4、5、6。这个链表的倒数第3个节点是值为4的节点。

示例：
给定一个链表: 1->2->3->4->5, 和 k = 2.
返回链表 4->5.
```

##### 解题思路

>- 两次遍历，第一次遍历获取链表长度，然后计算目标位置，第二次遍历到目标位置；
>- 一次遍历，使用两个指针，它们相隔k个距离，则当后一个指针到达链表尾部时，前一个指针就是目标位置

##### 代码

```java
//两次遍历
public ListNode getKthFromEnd(ListNode head, int k) {
    if (head == null) {
        return null;
    }
    ListNode temp = head;
    int size = 1;
    while (temp.next != null) {
        temp = temp.next;
        size++;
    }
    int position = size - k + 1;
    while (position > 1) {
        head = head.next;
        position --;
    }
    return head;
}
//一次遍历
public ListNode getKthFromEnd(ListNode head, int k) {
    ListNode p = head,q = head;
    int i = 0;
    while (q != null) {
        if (i < k) {
            q = q.next;
            i++;
            continue;
        }
        p = p.next;
        q = q.next;
    }
    return p;
}
```



#### (24)反转链表

##### 问题描述

```
定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。

示例:
输入: 1->2->3->4->5->NULL
输出: 5->4->3->2->1->NULL
 
限制：
0 <= 节点个数 <= 5000。
```

##### 解题思路

>在遍历链表时，每次都生成一个当前值的节点，然后让这个节点指向前一个；
>
>前一个节点由temp进行保存，result为新生成的节点

##### 代码

```java
/**
     * 面试题24
     * 反转链表
     * @param head 链表头结点
     * @return 反转后的链表头节点
     */
public ListNode reverseList(ListNode head) {
    ListNode result = null, temp = null;
    while (head != null) {
        result = new ListNode(head.val);
        result.next = temp;
        temp = result;
        head = head.next;
    }
    return result;
}
```



#### (25)合并两个排序的链表

##### 问题描述

```
输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。

示例1：
输入：1->2->4, 1->3->4
输出：1->1->2->3->4->4

限制：
0 <= 链表长度 <= 1000
```

##### 解题思路

>声明两个节点，**head**表示结果的头结点，用来返回结果；**temp**节点用来串连整个结果；
>
>核心思想是每次比较l1与l2的值，并将temp节点指向其较小的值上，知道链表结束。
>
>值得注意的事两个链表长短不一，但都是递增序列。

##### 代码

```java
/**
     * 面试题25
     * 合并两个排序的链表
     * @param l1 递增链表1头结点
     * @param l2 递增链表2头结点
     * @return 合并后的链表头结点
     */
public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    ListNode head,temp;
    if (l1 == null) {
        return l2;
    } else if (l2 == null) {
        return l1;
    } else {
        if (l1.val < l2.val) {
            temp = l1;
            l1 = l1.next;
        } else {
            temp = l2;
            l2 = l2.next;
        }
    }
    head = temp;
    while (l1 != null && l2 != null) {
        if (l1.val < l2.val) {
            temp.next = l1;
            l1 = l1.next;
        } else {
            temp.next = l2;
            l2 = l2.next;
        }
        temp = temp.next;
    }
    if (l1 == null) {
        temp.next = l2;
    } else if (l2 == null) {
        temp.next = l1;
    }
    return head;
}
```



#### (27)二叉树的镜像

##### 问题描述

```
请完成一个函数，输入一个二叉树，该函数输出它的镜像。

例如输入：
     4
   /   \
  2     7
 / \   / \
1   3 6   9
镜像输出：
     4
   /   \
  7     2
 / \   / \
9   6 3   1

示例 1：
输入：root = [4,2,7,1,3,6,9]
输出：[4,7,2,9,6,3,1]

限制：
0 <= 节点个数 <= 1000
```

##### 解题思路

>镜像的核心是交换节点的左右子树；接着通过分治的思想来遍历整棵树

##### 代码

```java
public TreeNode mirrorTree(TreeNode root) {
    if (root == null){
        return null;
    }
    //交换左右子树节点
    TreeNode node = root.left;
    root.left = root.right;
    root.right = node;
    //递归交换左子树
    mirrorTree(root.left);
    //递归交换右子树
    mirrorTree(root.right);
    return root;
}
```



#### (55-I)二叉树的深度

##### 问题描述

```
输入一棵二叉树的根节点，求该树的深度。从根节点到叶节点依次经过的节点（含根、叶节点）形成树的一条路径，最长路径的长度为树的深度。

例如：
给定二叉树 [3,9,20,null,null,15,7]，

    3
   / \
  9  20
    /  \
   15   7
返回它的最大深度 3 。

提示：
节点总数 <= 10000
```

##### 解题思路

>先分治，分别计算根节点左子树的深度和右子树的深度，取其最大值即为最大深度

##### 代码

```java
public int maxDepth(TreeNode root) {
    return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
}
```



#### (56-I)数组中数字出现的次数

##### 问题描述

```
一个整型数组 nums 里除两个数字之外，其他数字都出现了两次。请写程序找出这两个只出现一次的数字。要求时间复杂度是O(n)，空间复杂度是O(1)。

示例 1：
输入：nums = [4,1,4,6]
输出：[1,6] 或 [6,1]

示例 2：
输入：nums = [1,2,10,4,1,4,3,3]
输出：[2,10] 或 [10,2]
 
限制：
2 <= nums <= 10000
```

##### 解题思路

>三种解法：
>
>1. 排序法：先排序，则相等的两个会被排在一起，然后分别从前后遍历找到不等的两个数
>
>2. HashMap:使用Hash表来判断是否有相同的值
>
>   前两种方法虽然能解决问题，但都无法满足时间复杂度 $O(n)$ 和空间复杂度 $O(1)$的要求
>
>3. 异或法：
>
>   - 根据相同的数进行异或后抵消的特点可以遍历数组，得到两个单次出现数的异或
>
>   - 然后使用$lowbit$函数取得数第一个1出现的位置
>
>     $lowbit(x) = 2^k$ 第k位即x中第一个1出现的位置
>
>   - 因为两个数不相等，所以通过将这个数分别和数组中的数进行与运算时，会将数组中的数分成两个组（与运算为0的为一组，不为0的为一组），两个不同的数分在两个组中
>
>   - 然后将筛选出来的数再进行异或，抵消掉组中相同的数，剩下的即为结果中的一个数

##### 代码

```java
/**
     * 面试题56-I
     * 数组中数字出现的次数
     * @param nums 所给数组
     * @return 单次次数出现的数字集合
     */
public static int[] singleNumbers(int[] nums) {
    if (nums == null || nums.length <= 2 || nums.length >= 10000) {
        return nums;
    }
    //抵消掉相同的数，计算出两个单次数字的异或
    int xor = 0;
    for (int i : nums) {
        xor ^= i;
    }
    //lowbit函数计算从低到高第一个1出现的位置
    int index = xor & (-xor);
    int x = 0;
    for (int i : nums) {
        //将数据分成两组
        if ((index & i) != 0) {
            //再次将相同的数抵消
            x ^= i;
        }
    }
    return new int[]{x, x ^ xor};
}
```



#### (56-II)数组中数字出现的次数II

##### 问题描述

```
在一个数组 nums 中除一个数字只出现一次之外，其他数字都出现了三次。请找出那个只出现一次的数字。

示例 1：
输入：nums = [3,4,3,3]
输出：4

示例 2：
输入：nums = [9,1,7,9,7,9,7]
输出：1
 
限制：
1 <= nums.length <= 10000
1 <= nums[i] < 2^31
```



##### 解题思路

##### 代码

#### (58-II)左旋转字符串

##### 问题描述

```
字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。

示例 1：
输入: s = "abcdefg", k = 2
输出: "cdefgab"

示例 2：
输入: s = "lrloseumgh", k = 6
输出: "umghlrlose"

限制：
1 <= k < s.length <= 10000
```

##### 解题思路

>将字符串按所给位置分为两部分,然后将前一部分衔接在后一部分即可

##### 代码

```java
public String reverseLeftWords(String s, int n) {
    if (s == null || s.length() < 1 || n < 1 || n > 10000) {
        return "";
    }
    return s.substring(n) + s.substring(0, n);
}
```

#### (64)求1+2+...+n

##### 问题描述

```
求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。

示例 1：
输入: n = 3
输出: 6

示例 2：
输入: n = 9
输出: 45
 
限制：
1 <= n <= 10000
```

##### 解题思路

>利用短路运算来实现判断逻辑，利用递归来实现循环逻辑。

##### 代码

```java
/**
* 求1+2+...+n
* @param n 最大求值
* @return n阶和
*/
public static int sumNums(int n) {
    int sum = n;
    boolean is = (n > 0) && ((sum = sum + sumNums(n - 1)) > 0);
    return sum;
}
```



