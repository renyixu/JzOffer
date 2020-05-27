package JzOffer;

import java.util.HashMap;

/**
 * @author 骑士逸
 * @version 1.0
 * @date 2020/5/27 7:51
 */
public class Day07 {
    /*
    题目1：
    数组中有一个数字出现的次数超过数组长度的一半，
    请找出这个数字。例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
    由于数字2在数组中出现了5次，超过数组长度的一半，
    因此输出2。如果不存在则输出0。
     */
    public int MoreThanHalfNum_Solution(int[] array) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : array) {
            if (!map.containsKey(i))
                map.put(i, 1);
            else {
                int c = map.get(i);
                map.put(i, ++c);
            }
        }
        int length = array.length;
        for (int key : map.keySet()) {
            if (map.get(key) > length / 2)
                return key;
        }
        return 0;
    }

    /*
    题目2：
    最大连续子数组和
    思路：这是最基本的动态规划问题，假设dp[i]表示以array[i]结尾的最大连续子数组和
    则dp[i+1]=max{dp[i]+array[i+1],array[i+1]}，这里的核心思想是如果前面
    的dp[i]是负的，那么就表示对整体没有贡献；反之就表示对整体有贡献（因为加上array[i+1]会更大）
     */
    public int FindGreatestSumOfSubArray(int[] array) {
        if (array == null || array.length == 0)
            return 0;
        int max = array[0], total = 0;
        for (int i : array) {
            total = total > 0 ? total + i : i;
            if (total > max)
                max = total;
        }
        return max;
    }

    /*
    题目3：
    输入一个正整数数组，把数组里所有数字拼接起来排成一个数，
    打印能拼接出的所有数字中最小的一个。
    例如输入数组{3，32，321}，则打印出这三个数字能排成的最小数字为321323。
    思路：给String数组排序，排序规则：若s1+s2<s2+s1，则s1排在s2的前面
     */
    public String PrintMinNumber(int[] numbers) {
        if (numbers == null || numbers.length == 0)
            return "";
        String[] strArray = new String[numbers.length];
        for (int i = 0; i < numbers.length; ++i)
            strArray[i] = numbers[i] + "";
        //给String数组排序
        for (int i = strArray.length - 1; i >= 1; --i) {
            for (int j = 0; j <= i - 1; ++j) {
                if ((strArray[j] + strArray[j + 1]).compareTo(strArray[j + 1] + strArray[j]) > 0) {
                    //交换strArray[j]和strArray[j+1]
                    String t = strArray[j];
                    strArray[j] = strArray[j + 1];
                    strArray[j + 1] = t;
                }
            }
        }
        String retStr = "";
        for (String str : strArray)
            retStr += str;
        return retStr;
    }

    /*
    题目4：
    在数组中的两个数字，如果前面一个数字大于后面的数字，
    则这两个数字组成一个逆序对。输入一个数组,求出这个数组中的逆序对的总数P。
    并将P对1000000007取模的结果输出。 即输出P%1000000007
     */
//    public int InversePairs(int[] array) {
//        if (array == null || array.length == 0)
//            return 0;
//        int c = 0;
//        for (int i = 0; i < array.length - 1; ++i) {
//            for (int j = i + 1; j < array.length; ++j) {
//                if (array[i] > array[j])
//                    ++c;
//            }
//        }
//        return c % 1000000007;
//    }
}
