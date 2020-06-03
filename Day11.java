package JzOffer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * @author 骑士逸
 * @version 1.0
 * @date 2020/6/3 8:01
 */
public class Day11 {

    private int c = 0;
    private TreeNode reP = null;
    private ArrayList<TreeNode> list = new ArrayList<>();
    /*
    题目1：
    给定一棵二叉搜索树，请找出其中的第k小的结点。
    例如， （5，3，7，2，4，6，8）    中，按结点数值大小顺序第三小结点的值为4。

    思路：中序遍历到的第k个节点即为所求点
     */

    public TreeNode KthNode(TreeNode pRoot, int k) {
        if (pRoot == null)
            return null;
        int c = 0;
        inOrder(pRoot, k);
        return reP;
    }

    //中序遍历
    public void inOrder(TreeNode p, int k) {
        if (p != null) {
            inOrder(p.left, k);
            if ((++c) == k) {
                reP = p;
                return;
            }
            inOrder(p.right, k);
        }
    }

    /*
    题目2：
    从上到下按层打印二叉树，同一层结点从左至右输出。每一层输出一行。
   */

    public ArrayList<ArrayList<Integer>> Print01(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> reList = new ArrayList<>();
        if (pRoot == null)
            return reList;
        getHeadNode(pRoot);
        //定义队列
        TreeNode[] queue = new TreeNode[100];
        int rear = -1, front = -1;
        //头结点入队
        rear = (rear + 1) % 100;
        queue[rear] = pRoot;
        int i = 0; //标记层数
        ArrayList<Integer> l = new ArrayList<>();
        while (rear != front) {
            //出队
            front = (front + 1) % 100;
            TreeNode t = queue[front];
            l.add(t.val);
            if (t == list.get(i)) {
                //一层结束
                reList.add(l);
                //把l清空
                l = new ArrayList<>();
                ++i;
            }
            //把t的子节点入队
            if (t.left != null) {
                rear = (rear + 1) % 100;
                queue[rear] = t.left;
            }
            if (t.right != null) {
                rear = (rear + 1) % 100;
                queue[rear] = t.right;
            }
        }
        return reList;
    }

    //获取每一层的结束节点
    private void getHeadNode(TreeNode p) {
        if (p != null) {
            list.add(p);
            if (p.right != null)
                getHeadNode(p.right);
            else if (p.left != null)
                getHeadNode(p.left);
            else
                return;
        }
    }

    /*
    题目3：
    请实现一个函数按照之字形打印二叉树，即第一行按照从左到右的顺序打印，
    第二层按照从右至左的顺序打印，第三行按照从左到右的顺序打印，其他行以此类推。
     */
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> reList = new ArrayList<>();
        if (pRoot == null)
            return reList;

        ArrayList<Integer> list = new ArrayList<Integer>();
        LinkedList<TreeNode> queue = new LinkedList<>(); //用作队列
        queue.add(null); //添加层分隔符
        //根结点入队
        queue.add(pRoot);
        //循环队列
        boolean flag = true; //记录顺序
        while (queue.size() != 1) {
            //出队
            TreeNode t = queue.remove();
            //遇到分隔符
            if (t == null) {
                Iterator<TreeNode> iterator = null;
                if (flag == true) {
                    //从左到右遍历
                    iterator = queue.iterator();
                    flag = false;
                } else {
                    iterator = queue.descendingIterator();
                    flag = true;
                }
                //遍历
                while (iterator.hasNext()) {
                    list.add(iterator.next().val);
                }
                reList.add(list);
                list = new ArrayList<Integer>();

                //在队列最后添加分隔符
                queue.add(null);
            } else {
                if (t.left != null)
                    queue.add(t.left);
                if (t.right != null)
                    queue.add(t.right);
            }
        }

        return reList;
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
