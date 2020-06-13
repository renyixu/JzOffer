package JzOffer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 * @author 骑士逸
 * @version 1.0
 * @date 2020/6/13 8:51
 */
public class Day14 {
    private Stack<Integer> stack = new Stack<>();

    public static void main(String[] args) {
        System.out.println(new Day14().IsPopOrder(new int[]{1, 2, 3, 4, 5}, new int[]{4, 3, 5, 2, 1}));
    }

    /*
    题目1：
    输入一个递增排序的数组和一个数字S，在数组中查找两个数，
    使得他们的和正好是S，如果有多对数字的和等于S，输出两个数的乘积最小的。
    对应每个测试案例，输出两个数，小的先输出。
     */
    public ArrayList<Integer> FindNumbersWithSum(int[] array, int sum) {
        ArrayList<Integer> list = new ArrayList<>();
        int l = -1, r = array.length;
        int i = 0, j = array.length - 1;
        int mulMin = Integer.MAX_VALUE;
        while (i < j) {
            if (i < j && (array[i] + array[j]) > sum)
                --j;
            else if (i < j && (array[i] + array[j]) < sum)
                ++i;
            else if (i < j && (array[i] + array[j]) == sum) {
                if ((array[i] * array[j]) < mulMin) {
                    mulMin = array[i] * array[i];
                    l = i;
                    r = j;
                }
                ++i;
                --j;
            }
        }
        if (l != -1 && r != array.length) {
            list.add(array[l]);
            list.add(array[r]);
        }
        return list;
    }

    /*
    题目2：
    小明很喜欢数学,有一天他在做数学作业时,要求计算出9~16的和,
    他马上就写出了正确答案是100。但是他并不满足于此,
    他在想究竟有多少种连续的正数序列的和为100(至少包括两个数)。没多久,
    他就得到另一组连续正数和为100的序列:18,19,20,21,22。
    现在把问题交给你,你能不能也很快的找出所有和为S的连续正数序列? Good Luck!

    输出所有和为S的连续正数序列。序列内按照从小至大的顺序，序列间按照开始数字从小到大的顺序
     */
    public ArrayList<ArrayList<Integer>> FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> list = new ArrayList<>();
        for (int i = 1; i <= sum / 2; ++i) {
            int s = 0;
            for (int j = i; ; ++j) {
                s += j;
                if (s == sum) {
                    ArrayList<Integer> l = new ArrayList<>();
                    for (int k = i; k <= j; ++k)
                        l.add(k);
                    list.add(l);
                    break;
                } else if (s > sum)
                    break;
            }
        }
        return list;
    }

    /*
    题目3:
    在一个字符串(0<=字符串长度<=10000，全部由字母组成)中找到第一个只出现一次的字符,
    并返回它的位置,
    如果没有则返回 -1（需要区分大小写）.（从0开始计数）
     */
    public int FirstNotRepeatingChar(String str) {
        HashMap<Character, Integer> map = new HashMap<>();
        char[] ch = str.toCharArray();
        for (char c : ch) {
            if (!map.containsKey(c))
                map.put(c, 1);
            else {
                int val = map.get(c);
                map.put(c, ++val);
            }
        }
        for (char c : ch) {
            if (map.get(c) == 1)
                return str.indexOf(c);
        }
        return -1;
    }

    /*
    题目4:
    把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，
    但14不是，因为它包含质因子7。
    习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
    思路：如果n是一个丑数，那么n=2^a+3^b+5^c，只要给a,b,c赋值即可
    并且如果2n,3n,5n也是丑数
     */
    public int GetUglyNumber_Solution(int index) {
        if (index <= 0)
            return 0;
        int t2 = 0, t3 = 0, t5 = 0;
        int num[] = new int[index];
        num[0] = 1;
        //因为num[0]已经有值了，所以从1开始
        for (int i = 1; i < index; ++i) {
            num[i] = Math.min(num[t2] * 2, Math.min(num[t3] * 3, num[t5] * 5));
            //使用3个if是为了防止有一样的值，故而不用else
            if (num[i] == num[t2] * 2)
                ++t2;
            if (num[i] == num[t3] * 3)
                ++t3;
            if (num[i] == num[t5] * 5)
                ++t5;
        }
        return num[index - 1];
    }

    /*
    题目5：
    输入n个整数，找出其中最小的K个数。
    例如输入4,5,1,6,2,7,3,8这8个数字，
    则最小的4个数字是1,2,3,4,。
    思路：使用堆排序
     */
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        if (input == null || k <= 0)
            return list;
        //建立小顶堆
        int i;
        if ((input.length - 1) % 2 == 0)
            i = (input.length - 1) / 2 - 1;
        else
            i = (input.length - 1) / 2;
        for (int j = i; j >= 0; --j)
            sift(input, j, input.length - 1);
        int size = input.length - 1;
        for (int j = 0; j < k; ++j) {
            list.add(input[0]);
            input[0] = input[size--];
            //调整小顶锥
            sift(input, 0, size);
        }
        return list;
    }


    //修改下标j位置的数
    private void sift(int[] input, int low, int high) {
        int t = input[low];
        int i = low, j = 2 * i + 1; //j是i的左孩子节点
        while (j <= high) {
            //找出子节点中较小的那一个
            if (j < high && input[j] > input[j + 1])
                ++j;
            if (input[j] < t) {
                input[i] = input[j]; //此时j位置空出来了
                i = j; //相当于把j的位置给了i,因为原来的数字已经在
                j = 2 * i + 1; //j是i的孩子节点
            } else
                break;
        }
        input[i] = t; //填充位置i
    }

    /*
    题目6：
    输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否可能为该栈的弹出顺序。
    假设压入栈的所有数字均不相等。例如序列1,2,3,4,5是某栈的压入顺序，
    序列4,5,3,2,1是该压栈序列对应的一个弹出序列，但4,3,5,1,2就不可能是该压栈序列的弹出序列。
    （注意：这两个序列的长度是相等的）
     */
    public boolean IsPopOrder(int[] pushA, int[] popA) {
        int i = 0, j = 0, p = -1;
        int stack[] = new int[pushA.length];
        while (i < pushA.length) {
            if (pushA[i] == popA[j]) {
                ++i;
                ++j;
            }
            //比较popA[j]与stack栈顶的数据是否相等
            else if (p != -1 && popA[j] == stack[p]) {
                ++j;
                p--;
            } else if (pushA[i] != popA[j]) {
                stack[++p] = pushA[i];
                i++;
            }
        } //入栈序列遍历结束

        while (p != -1 && j < popA.length) {
            if (stack[p] != popA[j])
                return false;
            p--;
            j++;
        }
        return true;

    }

     /*
    题目7：
    定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的min函数（时间复杂度应为O（1））。
    注意：保证测试中不会当栈为空的时候，对栈调用pop()或者min()或者top()方法。
     */

//    public void push(int node) {
//        stack.push(node);
//    }
//
//    public void pop() {
//        if (!stack.isEmpty())
//            stack.pop();
//    }
//
//    public int top() {
//        if (!stack.isEmpty())
//            return stack.peek();
//        return -1;
//    }
//
//    public int min() {
//        if (stack.isEmpty())
//            return -1;
//        int min = top();
//        while (!stack.isEmpty()) {
//            int m = stack.pop();
//            if (min > m)
//                min = m;
//        }
//        return min;
//    }


}
