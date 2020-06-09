package JzOffer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

/**
 * @author 骑士逸
 * @version 1.0
 * @date 2020/6/9 9:10
 */
public class Day12 {
    private StringBuffer sb = new StringBuffer();
    private int index = -1;
    /*
    题目1：
    如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，
    那么中位数就是所有数值排序之后位于中间的数值。
    如果从数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
    我们使用Insert()方法读取数据流，使用GetMedian()方法获取当前读取数据的中位数。
     */

//    private LinkedList<Integer> list = new LinkedList<>();

    //读取数字
//    public void Insert(Integer num) {
//        list.add(num);
//    }
//
//    public Double GetMedian() {
    //排序
//        Collections.sort(list);
//        if (list.size() % 2 != 0)
//            return Double.valueOf(list.get(list.size() / 2));
//        return Double.valueOf((list.get(list.size() / 2) + list.get(list.size() / 2 + 1)) / 2);
//    }


    /*
    题目2：
    给你一根长度为n的绳子，请把绳子剪成整数长的m段（m、n都是整数，n>1并且m>1），
    每段绳子的长度记为k[0],k[1],...,k[m]。请问k[0]xk[1]x...xk[m]可能的最大乘积是多少？
    例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
     */
    public int cutRope(int target) {
        if (target == 2)
            return 1;
        if (target == 3)
            return 2;
        if (target == 4)
            return 4;
        int res = 1;
        while (target > 4) {
            res = res * 3;
            target -= 3;
        }
        return res * target;
    }

    /*
    题目3：
    给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。
    例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，那么一共存在6个滑动窗口，
    他们的最大值分别为{4,4,6,6,6,5}； 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个：
    {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}， {2,3,4,[2,6,2],5,1}， {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
     */
    public ArrayList<Integer> maxInWindows(int[] num, int size) {
        ArrayList<Integer> list = new ArrayList<>();
        if (size <= 0)
            return list;
        for (int i = 0; i <= num.length - size; ++i) {
            list.add(getMax(num, i, size));
        }
        return list;
    }

    private Integer getMax(int[] num, int i, int size) {
        int max = num[i];
        for (int j = i + 1; j <= i + size - 1; ++j) {
            if (max < num[j])
                max = num[j];
        }
        return max;
    }

    /*
   题目4：
   请实现两个函数，分别用来序列化和反序列化二叉树

   二叉树的序列化是指：把一棵二叉树按照某种遍历方式的结果以某种格式保存为字符串，
   从而使得内存中建立起来的二叉树可以持久保存。序列化可以基于先序、
   中序、后序、层序的二叉树遍历方式来进行修改，
   序列化的结果是一个字符串，序列化时通过 某种符号表示空节点（#），
   以 ！ 表示一个结点值的结束（value!）。

   二叉树的反序列化是指：根据某种遍历顺序得到的序列化字符串结果str，重构二叉树。

   例如，我们可以把一个只有根节点为1的二叉树序列化为"1,"，
   然后通过自己的函数来解析回这个二叉树
    */
    //序列化

    String Serialize(TreeNode root) {
        serialize(root);
        return sb.toString();
    }

    void serialize(TreeNode root) {
        if (root != null) {
            sb.append(root.val + ",");
            serialize(root.left);
            serialize(root.right);
        } else {
            sb.append("#,");
        }
    }

    //反序列化
    TreeNode Deserialize(String str) {
        if (str != null && !"".equals(str)) {
            String strArray[] = str.split(",");
            return deserialize(strArray);
        } else
            return null;
    }

    private TreeNode deserialize(String[] strArray) {
        ++index;
        if (!"#".equals(strArray[index])) {
            TreeNode t = new TreeNode(Integer.parseInt(strArray[index]));
            t.left = deserialize(strArray);
            t.right = deserialize(strArray);
            return t;
        } else
            return null;
    }

    public class TreeNode {
        int val;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }
    }
}
