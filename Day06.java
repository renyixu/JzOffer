package JzOffer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author 骑士逸
 * @version 1.0
 * @date 2020/5/26 6:50
 */
public class Day06 {
    private TreeNode pre = null;
    private TreeNode tn = null;

    /*
   题目1：
   输入两个单调递增的链表，输出两个链表合成后的链表，
   当然我们需要合成后的链表满足单调不减规则。
    */
    public ListNode Merge(ListNode list1, ListNode list2) {
        if (list1 == null && list2 != null)
            return list2;
        if (list1 != null && list2 == null)
            return list1;
        if (list1 == null && list2 == null)
            return null;
        ListNode list = new ListNode(-1);
        list.next = null;
        ListNode r = list;
        ListNode p = list1, q = list2;
        while (p != null && q != null) {
            if (p.val < q.val) {
                r.next = p;
                r = p;
                p = p.next;
            } else {
                r.next = q;
                r = q;
                q = q.next;
            }
        }
        r.next = null;
        if (p != null)
            r.next = p;
        if (q != null)
            r.next = q;
        return list.next;
    }

    /*
    题目2：
    输入一个复杂链表（每个节点中有节点值，以及两个指针，
    一个指向下一个节点，另一个特殊指针random指向一个随机节点），
    请对此链表进行深拷贝，并返回拷贝后的头结点。
    （注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空）
     */
    public RandomListNode Clone(RandomListNode pHead) {
        if (pHead == null)
            return null;
        HashMap<Integer, Integer> map = new HashMap<>();
        RandomListNode rl = new RandomListNode(-1);
        rl.next = null;
        rl.random = null;
        RandomListNode r = rl;
        RandomListNode p = pHead;
        while (p != null) {
            //创建新结点
            RandomListNode node = new RandomListNode(p.label);
            node.next = node.random = null;
            //尾插法
            r.next = node;
            r = node;
            if (p.random != null)
                map.put(p.label, p.random.label);
            p = p.next;
        }
        r.next = null;
        //处理random指针
        p = rl.next;
        while (p != null) {
            if (map.containsKey(p.label)) {
                p.random = findRandomNode(rl.next, (int) map.get(p.label));
            }
            p = p.next;
        }
        return rl.next;
    }

    private RandomListNode findRandomNode(RandomListNode list, int i) {
        RandomListNode p = list;
        while (p != null) {
            if (p.label == i)
                return p;
            p = p.next;
        }
        return p;
    }

    /*
   题目3：
   输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。
   要求不能创建任何新的结点，只能调整树中结点指针的指向。
    */

    public TreeNode Convert(TreeNode pRootOfTree) {
        inOrder(pRootOfTree);
        return tn;
    }

    //中序遍历
    private void inOrder(TreeNode p) {
        if (p == null)
            return;
        inOrder(p.left);
        //pre是pRootOfTree中序遍历的前驱结点
        p.left = pre;
        //如果pre不为空
        if (pre != null)
            pre.right = p;
        //访问中序遍历的第一个结点时，tn肯定是null
        //只需要把中序遍历访问到的第一个结点赋给tn即可
        //以后tn就不再是null
        if (tn == null)
            tn = p;

        //把当前访问节点p赋给pre，因为即将访问右子树
        pre = p;
        inOrder(p.right);
    }


    /*
    题目4：
    输入两个链表，找出它们的第一个公共结点。
    （注意因为传入数据是链表，所以错误测试数据的提示是用其他方式显示的，
    保证传入数据是正确的）
    思路：假设两个链表的公共部分长度是c，链表1前段部分长度为a，
    链表2前段部分长度是b，a+c+b=b+c+a，让一个指针遍历完链表1
    以后再去从头遍历链表2，与让一个指针遍历完链表2以后再去从头遍历链表1
    这是两者会同时抵达第一个公共点。
     */

    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        if (pHead1 == null || pHead2 == null)
            return null;
        ListNode p1 = pHead1, p2 = pHead2;
        boolean f1 = false, f2 = false; //标记p1和p2有无访问另一个链表
        while (p1 != null && p2 != null) {
            if (p1.next == null && f1 == false) {
                f1 = true;
                p1 = pHead2; //让p1继续从头访问链表2
                continue;
            }
            if (p2.next == null && f2 == false) {
                f2 = true;
                p2 = pHead1; //让p2继续从头访问链表1
                continue;
            }
            if (f1 == true && f2 == true && p1.val == p2.val)
                return p1;
            p1 = p1.next;
            p2 = p2.next;
        }
        return null;

    }

    /*
    题目5：
    给一个链表，若其中包含环，请找出该链表的环的入口结点，否则，输出null。
     */
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        if (pHead == null)
            return null;
        List<ListNode> list = new ArrayList<>();
        ListNode p = pHead;
        while (p != null) {
            if (list.contains(p))
                return p;
            list.add(p);
            p = p.next;
        }
        return null;
    }

    class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }

    class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }
}
