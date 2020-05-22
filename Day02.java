package JzOffer;

import java.util.Stack;

/**
 * @author 骑士逸
 * @version 1.0
 * @date 2020/5/22 7:25
 */
public class Day02 {
    /*
        题目1：
     给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
     注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
     */

    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();

    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode == null)
            return null;
        if (pNode.right == null) {
            TreeLinkNode p = pNode;
            while (p.next != null) {
                TreeLinkNode parent = p.next;
                if (parent.left == p)
                    return p;
                p = p.next;
            }
        } else {
            TreeLinkNode p = pNode.right;
            while (p.left != null)
                p = p.left;
            return p;
        }
        return null;
    }

    private TreeLinkNode findNode(TreeLinkNode pNode) {
        if (pNode == null)
            return null;
        if (pNode.left != null)
            return findNode(pNode.left);
        else
            return pNode;
    }

    /*
    /*
     * 用两个栈来实现一个队列，完成队列的Push和Pop操作。
     * 队列中的元素为int类型。
     */

    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        //判断s2是否为空
        if (stack2.empty()) {
            //把所有s1中的元素出栈，入栈s2
            while (!stack1.empty()) {
                stack2.push(stack1.pop());
            }
            return stack2.pop();
        } else
            return stack2.pop();
    }

    /*
    大家都知道斐波那契数列，现在要求输入一个整数n，
    请你输出斐波那契数列的第n项（从0开始，第0项为0，第1项是1）。
    n<=39
     */
    public int Fibonacci(int n) {
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        return Fibonacci(n - 1) + Fibonacci(n - 2);
    }


    /*
    跳台阶问题
     */
    public int RectCover(int target) {
        if (target == 0)
            return 0;
        if (target == 1)
            return 1;
        if (target == 2)
            return 2;
        return RectCover(target - 1) + RectCover(target - 2);
    }

    /*
    把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。
    输入一个非递减排序的数组的一个旋转，输出旋转数组的最小元素。
    例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。
    NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
     */
    public int minNumberInRotateArray(int[] array) {
        if (array.length == 0)
            return 0;
        for (int i = 0; i <= array.length - 2; ++i) {
            if (array[i] > array[i + 1])
                return array[i + 1];
        }
        return array[0];

    }

    class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;

        TreeLinkNode(int val) {
            this.val = val;
        }
    }
}
