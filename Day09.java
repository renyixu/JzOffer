package JzOffer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 骑士逸
 * @version 1.0
 * @date 2020/5/29 17:06
 */
public class Day09 {

    public static void main(String[] args) {
        System.out.println(new Day09().StrToInt("-2147483649"));
    }

    /*
    题目1：
    汇编语言中有一种移位指令叫做循环左移（ROL），现在有个简单的任务，
    就是用字符串模拟这个指令的运算结果。对于一个给定的字符序列S，
    请你把其循环左移K位后的序列输出。例如，字符序列S=”abcXYZdef”,
    要求输出循环左移3位后的结果，即“XYZdefabc”。是不是很简单？OK，搞定它！
     */
    public String LeftRotateString(String str, int n) {
        if (str == null)
            return null;
        if (str.length() == 0)
            return "";
        return str.substring(n) + str.substring(0, n);
    }

    /*
    题目2：
    将一个字符串转换成一个整数，要求不能使用字符串转换整数的库函数。
    数值为0或者字符串不是一个合法的数值则返回0
     */
    public int StrToInt(String str) {
        //正则表达式
        Pattern pattern = Pattern.compile("[+-]?\\d+");
        Matcher matcher = pattern.matcher(str);
        if (!matcher.matches())
            return 0;
        char[] ch;
        boolean flag = false;
        if (str.startsWith("+"))
            ch = str.substring(1).toCharArray();
        else if (str.startsWith("-")) {
            flag = true;
            ch = str.substring(1).toCharArray();
        } else
            ch = str.toCharArray();
        long res = 0;
        for (int i = ch.length - 1; i >= 0; --i) {
            if (flag == true)
                res += -(ch[i] - '0') * Math.pow(10, ch.length - 1 - i);
            else
                res += (ch[i] - '0') * Math.pow(10, ch.length - 1 - i);
        }
        return (int) res;
    }

    /*
    题目3：
    输入两棵二叉树A，B，判断B是不是A的子结构。
    （ps：我们约定空树不是任意一个树的子结构）
     */
    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        if (root1 == null || root2 == null)
            return false;
        return isSonTree(root1, root2) || HasSubtree(root1.left, root2) || HasSubtree(root1.right, root2);
    }


    //r2是“子树”
    public boolean isSonTree(TreeNode r1, TreeNode r2) {
        if (r2 == null)
            //第二棵树已经遍历完毕（无论第一棵树有无遍历完毕），表示都能对上
            return true;
        if (r1 == null)
            //第一棵树已经遍历完了但第二棵树还没遍历完，表示r2不是r1的子树
            return false;
        if (r1.val != r2.val)
            return false;
        else
            //如果两个节点的当前节点相等，则要继续比较其左子树与右子树
            return isSonTree(r1.left, r2.left) && isSonTree(r1.right, r2.right);
    }


    /*
    题目4：
    操作给定的二叉树，将其变换为源二叉树的镜像。
     */

    public void Mirror(TreeNode root) {
        if (root != null) {
            Mirror(root.left);
            Mirror(root.right);
            //交换左节点和右节点
            TreeNode t = root.left;
            root.left = root.right;
            root.right = t;
        }
    }

    /*
    题目5：
    从上往下打印出二叉树的每个节点，同层节点从左至右打印。
     */

    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        if (root == null)
            return null;
        ArrayList<Integer> list = new ArrayList<>();
        //创建一个队列
        TreeNode[] queue = new TreeNode[10000];
        int rear = -1, front = -1;
        //根结点入队
        queue[(++rear) % 10000] = root;
        //循环队列
        while (rear != front) {
            //出队
            TreeNode t = queue[(++front) % 10000];
            list.add(t.val);
            //把当前节点的左节点和右节点依次入队
            if (t.left != null)
                queue[(++rear) % 10000] = t.left;
            if (t.right != null)
                queue[(++rear) % 10000] = t.right;
        }
        return list;
    }

    class TreeNode {
        int val;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }

    }
}
