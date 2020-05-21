package JzOffer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 骑士逸
 * @version 1.0
 * @date 2020/5/21 8:01
 */
public class Day01 {

    /*
     * 在一个长度为n的数组里的所有数字都在0到n-1的范围内。
     * 数组中某些数字是重复的，但不知道有几个数字是重复的。
     * 也不知道每个数字重复几次。
     * 请找出数组中任意一个重复的数字。
     * 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，那么对应的输出是第一个重复的数字2。
     */
    public boolean duplicate(int numbers[], int length, int[] duplication) {
        if (numbers == null || numbers.length == 0) {
            duplication[0] = -1;
            return false;
        }
        List<Integer> list = new ArrayList<>();
        for (int i : numbers) {
            if (!list.contains(i))
                list.add(i);
            else {
                duplication[0] = i;
                return true;
            }
        }
        return false;
    }


    /*
    请实现一个函数，将一个字符串中的每个空格替换成“%20”。
    例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
     */

    /*
    思路：创建一个List集合，遍历数组，若List中无此整数，则添加进List；
    否则，即为第一个重复元素，输出。
     */


    /*
    在一个二维数组中（每个一维数组的长度相同），每一行都按照从左到右递增的顺序排序，
    每一列都按照从上到下递增的顺序排序。
    请完成一个函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     */
    public boolean Find(int target, int[][] array) {
        //从左下角开始查找，其上元素比其小，其右边元素比其大
        int m = array.length - 1, n = 0;
        while (m >= 0 && n <= array[0].length) {
            if (array[m][n] == target)
                return true;
            else if (array[m][n] < target)
                ++n;
            else
                --m;
        }
        return false;
    }


    /*
    输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
    假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
    例如输入前序遍历序列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}，
    则重建二叉树并返回。
     */

    public String replaceSpace(StringBuffer str) {
        String s = str.toString();
        s = s.replaceAll(" ", "%20");
        System.out.println(s);
        return s;
    }

    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ListNode p = listNode;
        ArrayList<Integer> arrayList = new ArrayList<>();
        while (p != null) {
            arrayList.add(p.val);
            p = p.next;
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = arrayList.size() - 1; i >= 0; --i)
            list.add(arrayList.get(i));
        return list;
    }

    /*
    输入一个链表，按链表从尾到头的顺序返回一个ArrayList。
     */
    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        return createTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }

    public TreeNode createTree(int pre[], int l1, int r1, int in[], int l2, int r2) {
        if (l1 > r1)
            return null;
        TreeNode root = null;
        for (int i = l2; i <= r2; ++i) {
            if (in[i] == pre[l1]) {
                //创建根结点
                root = new TreeNode(pre[l1]);
                //左子树
                root.left = createTree(pre, l1 + 1, l1 + i - l2, in, l2, i - 1);
                //右子树
                root.right = createTree(pre, l1 + i - l2 + 1, r1, in, i + 1, r2);
            }
        }
        return root;
    }

    class ListNode {
        int val;
        ListNode next;

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }
}
