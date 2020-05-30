package JzOffer;

import java.util.ArrayList;

/**
 * @author 骑士逸
 * @version 1.0
 * @date 2020/5/30 8:09
 */
public class Day10 {

    private ArrayList<ArrayList<Integer>> arrayLists = new ArrayList<>();
    /*
    题目1：
    输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。
    如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
     */

    public static void main(String[] args) {
        System.out.println(new Day10().VerifySquenceOfBST(new int[]{2, 4, 3, 6, 7, 9, 11, 10, 8, 5}));
    }

    public boolean VerifySquenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length == 0)
            return false;
        return getRes(sequence, 0, sequence.length - 1);
    }


    private boolean getRes(int[] sequence, int i, int j) {
        //当只有一个数的时候肯定是符合的，故返回true
        if (i >= j)
            return true;
        //根据j位置的数，找出比其小、比其大的分隔位置
        int k = i;
        for (k = i; k <= j; ++k) {
            if (sequence[k] >= sequence[j])
                break;
        }

        for (int m = k; m <= j - 1; ++m) {
            if (sequence[m] < sequence[j])
                return false;
        }
        //递归
        return getRes(sequence, i, k - 1) && getRes(sequence, k, j - 1);
    }

    /*
   题目2：
   输入一颗二叉树的根节点和一个整数，打印出二叉树中结点值的和为输入整数的所有路径。
   路径定义为从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
    */
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        ArrayList<Integer> list = new ArrayList<>();
        findPath(root, target, list);
        return arrayLists;
    }

    private void findPath(TreeNode root, int target, ArrayList<Integer> list) {
        if (root != null) {
            if (target - root.val > 0) {
                list.add(root.val);
                findPath(root.left, target - root.val, list);
                findPath(root.right, target - root.val, list);
                //向上回溯需要删除刚刚添加的点，因为还有其他节点需要遍历
                list.remove((Object) root.val);
                //list.remove(list.size()-1);
            } else if (target - root.val == 0) {
                //是叶子节点
                if (root.left == null && root.right == null) {
                    list.add(root.val);
                    arrayLists.add(new ArrayList(list));
                    //向上回溯需要删除刚刚添加的点，因为还有其他节点需要遍历
                    list.remove((Object) root.val);
                    //list.remove(list.size()-1);
                }
                //不是叶子节点
                else
                    return;
            }
        }
    }

    /*
    题目3：
    输入一棵二叉树，求该树的深度。
    从根结点到叶结点依次经过的结点（含根、叶结点）形成树的一条路径，
    最长路径的长度为树的深度。
     */
    public int treeDepth(TreeNode root) {
        if (root != null) {
            int l = treeDepth(root.left), r = treeDepth(root.right);
            return (l > r ? l : r) + 1;
        } else
            return 0;
    }

    /*
    题目4：
    输入一棵二叉树，判断该二叉树是否是平衡二叉树。
    在这里，我们只需要考虑其平衡性，不需要考虑其是不是排序二叉树
     */

    public boolean IsBalanced_Solution(TreeNode root) {
        if (root == null)
            return false;
        return isBalanceTree(root);
    }

    private boolean isBalanceTree(TreeNode root) {
        if (root != null) {
            if (Math.abs(treeDepth(root.left) - treeDepth(root.right)) > 1)
                return false;
            return isBalanceTree(root.left) && isBalanceTree(root.right);
        }
        return true;
    }

    /*
    题目5：
    请实现一个函数，用来判断一颗二叉树是不是对称的。
    注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
     */
    public boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot == null)
            return true;
        return isSymmetricalTree(pRoot.left, pRoot.right);
    }

    public boolean isSymmetricalTree(TreeNode l, TreeNode r) {
        //两个都为空就表示同时遍历到了null节点
        if (l == null && r == null)
            return true;
        //有一个为空
        if (l == null || r == null)
            return false;
        if (l.val == r.val)
            return isSymmetricalTree(l.left, r.right) && isSymmetricalTree(l.right, r.left);
        return false;
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


