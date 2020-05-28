package JzOffer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.TreeSet;

/**
 * @author 骑士逸
 * @version 1.0
 * @date 2020/5/28 14:12
 */
public class Day08 {
    private static int count = 0;
    private ArrayList<String> list = new ArrayList<>();
    private TreeSet<String> set=new TreeSet<>();

    public static void main(String[] args) {
//        new Day08().InversePairs(new int[]{1, 2, 3, 4, 5, 6, 7, 0});
//        System.out.println("逆序对=" + count);
        System.out.println(new Day08().consMultiply(new int[]{1, 2, 3, 4, 5, 6}, 0, 5, 1));
    }

    /*
   题目1：
   在数组中的两个数字，如果前面一个数字大于后面的数字，
   则这两个数字组成一个逆序对。输入一个数组,求出这个数组中的逆序对的总数P。
   并将P对1000000007取模的结果输出。 即输出P%1000000007
    */
    public int InversePairs(int[] array) {
        //先写一个归并排序
        gbSort(array, 0, array.length - 1);
        return count;
    }

    private void gbSort(int[] array, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            gbSort(array, low, mid);
            gbSort(array, mid + 1, high);
            merge(array, low, mid, high); //合并
            for (int i : array)
                System.out.print(i + " ");
            System.out.println();
        }
    }

    private void merge(int[] array, int low, int mid, int high) {
        int i = low, j = mid + 1;
        int tepArray[] = new int[array.length]; //创建临时数组
        int c = low - 1;
        while (i <= mid && j <= high) {
            if (array[i] <= array[j])
                tepArray[++c] = array[i++];
            else {
                count += mid - i + 1;
                tepArray[++c] = array[j++];
            }
        }

        //将剩余数组全部复制进临时数组
        while (i <= mid)
            tepArray[++c] = array[i++];
        while (j <= high)
            tepArray[++c] = array[j++];
        //把临时数组全部复制给array
        for (i = low; i <= high; ++i)
            array[i] = tepArray[i];
    }

    /*
    题目2：
    统计一个数字在排序数组中出现的次数。
     */
    public int GetNumberOfK(int[] array, int k) {
        if (array == null || array.length == 0)
            return 0;
        int count = 0;
        for (int i : array)
            if (i == k)
                ++count;
        return count;
    }

    /*
    题目3：
    一个整型数组里除了两个数字之外，其他的数字都出现了两次。
    请写程序找出这两个只出现一次的数字。
     */
    public void FindNumsAppearOnce(int[] array, int num1[], int num2[]) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : array) {
            if (!map.containsKey(i)) {
                map.put(i, 1);
            } else {
                int v = map.get(i);
                map.put(i, ++v);
            }
        }
        //寻找value为1的两个数
        for (int k : map.keySet()) {
            if (map.get(k) == 1) {
                if (num1.length == 0)
                    num1[0] = k;
                else
                    num2[0] = k;
            }
        }
    }

    /*
    题目4：
    给定一个数组A[0,1,...,n-1],
    请构建一个数组B[0,1,...,n-1],
    其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。
    不能使用除法。
    （注意：规定B[0] = A[1] * A[2] * ... * A[n-1]，B[n-1] = A[0] * A[1] * ... * A[n-2];）
     */
    public int[] multiply(int[] A) {
        int b[] = new int[A.length];
        for (int i = 0; i < A.length; ++i) {
            b[i] = getRes(A, i);
        }
        return b;
    }
    /*
    题目5：
    输入一个字符串,按字典序打印出该字符串中字符的所有排列。例如输入字符串abc,
    则打印出由字符a,b,c所能排列出来的所有字符串abc,acb,bac,bca,cab和cba。
     */

    private int getRes(int[] a, int i) {
        int res = 0;
        res = consMultiply(a, 0, i - 1, 1);
        res *= consMultiply(a, i + 1, a.length - 1, 1);
        return res;
    }

    private int consMultiply(int[] a, int i, int j, int res) {
        if (i <= j)
            res = consMultiply(a, i + 1, j, res) * a[i];
        return res;
    }

    ;

    public ArrayList<String> Permutation(String str) {
        char ch[] = str.toCharArray();
        Arrays.sort(ch); //排序
        perm(ch, 0);
        for(String s:set){
            list.add(s);
        }
        return list;

    }

    private void perm(char[] ch, int i) {
        //有超过两个的字符
        if (i < ch.length - 1) {
            for (int j = i; j < ch.length; ++j) {
                //把ch[i]放在当前数组第一个位置
                char t = ch[i];
                ch[i] = ch[j];
                ch[j] = t;
                //递归处理
                perm(ch, i + 1);
                //把上面交换的位置换回来
                t = ch[i];
                ch[i] = ch[j];
                ch[j] = t;
            }
        } else
            //只有一个字符，直接把当前数组添加集合中
            set.add(String.valueOf(ch));
    }
}
