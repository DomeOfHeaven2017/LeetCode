package com.wx.java_code.array;


import com.wx.java_code.common.Difference;

import java.util.*;

/**
 * Created by wx on 20-6-7
 * Description: LeetCode 数组相关问题集合
 *
 * 面试题 01.01 判定字符是否唯一 {@link #isUnique}
 *
 * 剑指 Offer 03 数组中重复的数字 {@link #findRepeatNumber}
 *
 * 14. 最长公共前缀 {@link #longestCommonPrefix}
 * 26.删除排序数组中的重复项 {@link #removeDuplicates}
 * 27. 移除元素 {@link #removeElement}
 * 189. 旋转数组 {@link #rotate}
 * 283. 移动零  {@link #moveZeroes}
 *349. 两个数组的交集 {@link #intersection}
 *  350. 两个数组的交集II {@link #intersect}
 * 461. 汉明距离 {@link #hammingDistance}
 * 867. 转置矩阵 {@link #transpose}
 * 1109.航班预订统计 {@link #corpFlightBookings}
 * 1207. 独一无二的出现次数 {@link #uniqueOccurrences}
 * 1365. 有多少小于当前数字的数字 {@link #smallerNumbersThanCurrent}
 * 1389. 按既定顺序创建目标数组 {@link #createTargetArray}
 * 1431. 拥有最多糖果的孩子 {@link #kidsWithCandies}
 * 1450. 在既定时间做作业的学生人数 {@link #busyStudent}
 * 1470.重新排列数组 {@link #shuffle}
 * 1480. 一维数组的动态和 {@link #runningSum}
 * 1482. 制作 m 束花所需的最少天数 {@link #minDays}
 * 1672. 最富有客户的资产总量 {@link #maximumWealth}
 * 1678. 设计 Goal 解析器 {@link #interpret}
 * 1704. 判断字符串的两半是否相似 {@link #halvesAreAlike}
 * 1773. 统计匹配检索规则的物品数量 {@link #countMatches}
 * 1832. 判断句子是否为全字母句 {@link #checkIfPangram}
 */
public class ArrayProblemSet {

    public static void main(String[] args) {
        System.out.println(hammingDistance(1,4));;
    }

    /**
     * 面试题 01.01 判定字符是否唯一
     * @param astr 所给字符串
     * @return 字符串中的字符是否唯一
     */
    public static boolean isUnique(String astr) {
        if (astr == null) {
            return false;
        }
        Map<Character,Integer> map = new HashMap<>(astr.length());
        char[] chars = astr.toCharArray();
        for (char c : chars) {
            if (map.containsKey(c)) {
                return false;
            } else {
                map.put(c, 1);
            }
        }
        return true;
    }

    /**
     * 剑指 Offer 03 数组中重复的数字
     * @param nums 所给数组
     * @return 重复数字
     */
    public int findRepeatNumber(int[] nums) {
        if (nums == null || nums.length < 2 || nums.length > 100000) {
            return 0;
        }
//        int[] temp = new int[nums.length];
//        for (int i = 0 ; i < nums.length ; i++) {
//            temp[nums[i]] ++;
//            if (temp[nums[i]] > 1) {
//                return nums[i];
//            }
//        }
//        return 0;
        int temp = 0;
        for (int i = 0 ; i < nums.length ; i ++) {
            while (nums[i] != i) {
                if (nums[i] == nums[nums[i]]) {
                    return nums[i];
                } else {
                    temp = nums[i];
                    nums[i] = nums[temp];
                    nums[temp] = temp;
                }
            }
        }
        return 0;
    }

    /**
     * 14. 最长公共前缀
     * @param strs 字符串数组
     * @return 公共前缀
     */
    public String longestCommonPrefix(String[] strs) {
        // TODO: 2021/5/9
        return null;
    }

    /**
     * 26.删除排序数组中的重复项 
     * @param nums 原始排序数组
     * @return 去重后的数组末尾序号
     */
    public int removeDuplicates(int[] nums) {
        int slow = 0, fast = 0;
        while(fast < nums.length) {
            if(nums[fast] != nums[slow]) {
                slow ++;
                nums[slow] = nums[fast];
            }
            fast++;
        }
        return slow+1;
    }

    /**
     * 27. 移除元素
     * @param nums 原数组
     * @param val 待移除元素
     * @return 移除后的数组末尾序号
     */
    public int removeElement(int[] nums, int val) {
        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow ++;
            }
            fast ++;
        }
        return slow + 1;
    }
    
    /**
     * 189.旋转数组
     * @param nums 原数组
     * @param k 旋转点
     */
    public void rotate(int[] nums, int k) {
        rotateHelper(nums, 0, nums.length);
        rotateHelper(nums, 0, k% nums.length - 1);
        rotateHelper(nums, k % nums.length, nums.length - 1);
    }

    /**
     * 对称交换数组元素
     * @param array 原数组
     * @param start 起始位置
     * @param end 结束位置
     */
    private void rotateHelper(int[] array, int start, int end) {
        while (start < end) {
            array[start] = array[start] ^ array[end];
            array[end] = array[start] ^ array[end];
            array[start] = array[start] ^ array[end];
            start++;
            end --;
        }
    }


    /**
     * 283. 移动零
     * @param nums 原数组
     */
    public void moveZeroes(int[] nums) {
        int index = removeElement(nums, 0);
        for (int i = index; i< nums.length; i++) {
            nums[i] = 0;
        }
    }

    /**
     *349. 两个数组的交集
     * @param nums1 数组1
     * @param nums2 数组2
     * @return 交集
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        int[] result = new int[Math.min(nums1.length, nums2.length)];
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0, j = 0, k = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else if (nums1[i] == nums2[j]) {
                if (k == 0 || result[k-1] != nums1[i]) {
                    result[k++] = nums1[i];
                }
                i++;
                j++;
            }
        }
        return Arrays.copyOf(result, k);
    }

    /**
     * 350. 两个数组的交集II
     * @param nums1 集合1
     * @param nums2 集合2
     * @return 两个集合的交集
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        int[] result = new int[Math.min(nums1.length, nums2.length)];
        int i = 0, j = 0, k = 0;
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i ++;
            } else if (nums1[i] > nums2[j]) {
                j ++;
            } else {
                result[k ++] = nums1[i];
                i ++;
                j ++;
            }
        }
        return Arrays.copyOf(result, k);
    }

    /**
     * 461. 汉明距离
     * @param x 参数x
     * @param y 参数y
     * @return x与y之间的汉明距离
     */
    public static int hammingDistance(int x, int y) {
        int xor = x ^ y;
        int distance = 0;
        while (xor != 0 ){
            if ((xor & 1) == 1) {
                distance++;
            }
            xor = xor >> 1;
        }
        return distance;
    }

    /**
     * 867. 转置矩阵
     * @param A 原矩阵
     * @return 转置矩阵
     */
    public int[][] transpose(int[][] A) {
        int m = A.length;
        int n = A[0].length;
        int[][] result = new int[n][m];
        for (int i = 0;i < m; i ++) {
            for (int j = 0; j < n; j++){
                result[j][i] = A[i][j];
            }
        }
        return result;
    }

    /**
     * 1109.航班预订统计
     * @param bookings 航班信息
     * @param n 航班数
     * @return 航班座位数组
     */
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] result = new int[n];
        Difference diff = new Difference(result);
        for (int i = 0; i < bookings.length; i ++) {
            int[] nums = bookings[i];
            diff.increment(nums[0] - 1, nums[1] - 1, nums[2]);
        }
        return diff.result();
    }

    /**
     * 1207. 独一无二的出现次数
     * @param arr  数据数组
     * @return 数组中的数字出现次数是否都不相同
     */
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>(arr.length);
        //计算数字出现次数，并以该数字为键存入哈希表中
        for (int i : arr) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        //将出现次数存到集合中，利用集合没有重复元素的特点来过滤相同次数的数字
        //如果集合的长度等于哈希表的长度，则每个数字出现次数都不相同
        Set<Integer> set = new HashSet<>();
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            set.add(entry.getValue());
        }
        return set.size() == map.size();
    }

    /**
     * 1365. 有多少小于当前数字的数字
     * @param nums 数据
     * @return
     */
    public int[] smallerNumbersThanCurrent(int[] nums) {
        //新建一个桶数组，因为 0 =<nums <= 100
        int[] bucket = new int[101];
        //计算对应nums[i]的频率
        for (int num : nums) {
            bucket[num]++;
        }
        //计算前缀和
        for (int i = 1;i < bucket.length;i++) {
            bucket[i] = bucket[i] + bucket[i-1];
        }
        int[] result = new int[nums.length];
        //计算结果
        for (int i = 0; i < result.length; i++) {
            result[i] = bucket[nums[i] - 1];
        }
        return result;
    }

    /**
     * 1389. 按既定顺序创建目标数组
     * @param nums 源数组
     * @param index 索引数组
     * @return 既定顺序数组
     */
    public int[] createTargetArray(int[] nums, int[] index) {
        List<Integer> temp = new ArrayList<>(nums.length);
        for (int i = 0; i < nums.length ; i ++) {
            temp.add(index[i], nums[i]);
        }
        int[] ret = new int[nums.length];
        for (int i = 0; i < temp.size(); i++) {
            ret[i] = temp.get(i);
        }
        return ret;
    }

    /**
     * 1431. 拥有最多糖果的孩子
     * @param candies 拥有的糖果数
     * @param extraCandies 额外糖果
     * @return
     */
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max = candies[0];
        for (int i = 0;i < candies.length;i ++) {
            if (candies[i] >= max) {
                max = candies[i];
            }
            candies[i] += extraCandies;
        }
        List<Boolean> result = new ArrayList<>(candies.length);
        for (int candy : candies) {
            result.add(candy >=  max);
        }
        return result;
    }

    /**
     * 1450. 在既定时间做作业的学生人数
     * @param startTime 开始时间数组
     * @param endTime 结束时间数组
     * @param queryTime 查询时间
     * @return 查询时间时正在做作业的学生数
     */
    public int busyStudent(int[] startTime, int[] endTime, int queryTime) {
        int i = 0, count = 0;
        while (i < startTime.length) {
            if (queryTime >= startTime[i] && queryTime <= endTime[i]) {
                count++;
            }
            i++;
        }
        return count;
    }

    /**
     * 1470.重新排列数组
     * @param nums 原数组
     * @param n 中间索引
     * @return 重排数组
     */
    public int[] shuffle(int[] nums, int n) {
        int[] result = new int[2*n];
        for (int i = 0; i <  n; i ++) {
            result[i*2] = nums[i];
            result[i*2 + 1] = nums[n+i];
        }
        return result;
    }

    /**
     * 1480. 一维数组的动态和
     * @param nums 原数组
     * @return 动态和
     */
    public int[] runningSum(int[] nums) {
        if (nums == null || nums.length == 0) {
            return nums;
        }
        int[] result = new int[nums.length];
        result[0] = nums[0];
        for (int i = 1 ; i < nums.length ; i++) {
            result[i] = result[i - 1] + nums[i];
        }
        return result;
    }

    /**
     * 1482. 制作 m 束花所需的最少天数
     * @param bloomDay 花开需要等待的时间
     * @param m 需要制作的花束数量
     * @param k 每束花需要的相邻花束数量
     * @return 制作m束花需要的最少天数
     */
    public int minDays(int[] bloomDay, int m, int k) {
        //需要的花束数量大于提供的花束数量，直接返回-1
        if (bloomDay == null || m * k > bloomDay.length) {
            return -1;
        }
        //求制作花束等待的最大时间
        int min = 1, max = 1;
        for (int i : bloomDay) {
            max = Math.max(max, i);
        }
        //二分遍历最小天数到最大天数，获取满足所需的最小天数
        while (min < max) {
            int mid = min + (max - min) / 2;
            if (checkMinDays(bloomDay, mid, m, k)) {
                max = mid;
            } else {
                min = mid + 1;
            }
        }
        return max;
    }

    /**
     * 检查当前天数是否能制作出所需的花束数
     * @param days 制作指定天数
     */
    private boolean checkMinDays(int[] bloomDay, int days, int m, int k) {
        //flowers表示当前已经开花的花的数量，开花数量达到m时能制作一个花束
        //sum表示当前已制作好的花束数量
        int flowers = 0, sum = 0;
        for (int i = 0; i < bloomDay.length && sum < m; i ++) {
            if (bloomDay[i] <= days) {
                flowers ++;
                if (flowers == m)  {
                    //制作成功一个花束，重置开花数量
                    sum ++;
                    flowers = 0;
                }
            } else {
                flowers = 0;
            }
        }
        //当前天数下制作的花束数量是否大于等于所需的花束数量
        return sum >= m;
    }

    /**
     * 1672. 最富有客户的资产总量
     * @param accounts 银行资产数据
     * @return 最富有客户的资产总量
     */
    public int maximumWealth(int[][] accounts) {
        int n = accounts[0].length;
        int max = 0;
        for (int[] account : accounts) {
            int temp = 0;
            for (int j = 0; j < n; j++) {
                temp += account[j];
            }
            if (temp > max) {
                max = temp;
            }
        }
        return max;
    }

    /**
     * 1678. 设计 Goal 解析器
     * @param command 命令
     * @return 解析后的字符串
     */
    public String interpret(String command) {
        if (command == null || command.length() <= 0) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        char[] cmds = command.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char c : cmds) {
            if ('G' == c) {
                result.append('G');
            } else if (')' == c) {
                if ("(".equals(temp.toString())) {
                    result.append('o');
                } else if ("(al".equals(temp.toString())) {
                    result.append("al");
                }
                temp.delete(0, temp.length());
            } else {
                temp.append(c);
            }
        }
        return result.toString();
    }

    /**
     * 1704. 判断字符串的两半是否相似
     * @param s 字符串
     * @return 是否相似
     */
    public boolean halvesAreAlike(String s) {
        if (s == null || s.length() <=0) return true;
        int length = s.length();
        int prev = 0, next = 0;
        char[] chars = s.toLowerCase().toCharArray();
        for (int i = 0; i < length ; i++) {
            if (chars[i] == 'a' || chars[i] == 'e' || chars[i] == 'i' || chars[i] == 'o' || chars[i] == 'u') {
                if (i <= length / 2) {
                    prev++;
                } else {
                    next++;
                }
            }
        }
        return prev == next;
    }

    /**
     * 1773. 统计匹配检索规则的物品数量
     * @param items 物品数据
     * @param ruleKey 匹配key
     * @param ruleValue 匹配值
     * @return 匹配的物品数量
     */
    public int countMatches(List<List<String>> items, String ruleKey, String ruleValue) {
        int count = 0, index = 0;
        switch (ruleKey) {
            case "color":
                index = 1;
                break;
            case "name":
                index = 2;
                break;
            default:
                break;
        }
        for (List<String> item : items) {
            if (ruleValue.equals(item.get(index))) {
                count ++;
            }
        }
        return count;
    }

    /**
     * 1832. 判断句子是否为全字母句
     * @param sentence 句子字符串
     * @return 是否包含全字母
     */
    public boolean checkIfPangram(String sentence) {
        if(sentence == null || sentence.length() < 26) {
            return false;
        }
        boolean[] map = new boolean[26];
        char[] chars = sentence.toCharArray();
        for (char c : chars) {
            int index = c - 'a';
            map[index] = true;
        }
        for (boolean b : map) {
            if (!b) {
                return false;
            }
        }
        return true;
    }

}
