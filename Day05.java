package JzOffer;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 骑士逸
 * @version 1.0
 * @date 2020/5/25 6:34
 */
public class Day05 {

    public static void main(String[] args) {
        System.out.println(new Day05().isNumeric(new char[]{'1', '0', '0'}));
    }

    /*
       题目1：
       输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
       使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，
       并保证奇数和奇数，偶数和偶数之间的相对位置不变。
        */
    public void reOrderArray(int[] array) {
        int i = 0, j = 0;
        //让i指向第一个偶数
        i = getFirstOuShu(array);
        //从i开始找第一个奇数
        j = getFirstJiShuStartI(array, i);
        while (i != -1 && j != -1) {
            int t = array[j];
            //把i~j-1后移
            for (int k = j - 1; k >= i; --k) {
                array[k + 1] = array[k];
            }
            //把t赋给array[i]
            array[i] = t;
            i = getFirstOuShu(array);
            j = getFirstJiShuStartI(array, i);
        }
    }

    private int getFirstJiShuStartI(int[] array, int i) {
        for (int j = i + 1; j < array.length; ++j) {
            if (array[j] % 2 != 0)
                return j;
        }
        return -1;
    }

    private int getFirstOuShu(int[] array) {
        for (int i = 0; i < array.length; ++i) {
            if (array[i] % 2 == 0)
                return i;
        }
        return -1;
    }

    /*
    题目2：
    在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，
    重复的结点不保留，返回链表头指针。
     例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
     */
    public ListNode deleteDuplication(ListNode pHead) {
        if (pHead == null)
            return null;
        ListNode pre = pHead;
        ListNode p = pre.next;
        TreeSet<Integer> set = new TreeSet<>();
        while (p != null) {
            if (p.val == pre.val) {
                //pre不动,p后移
                p = p.next;
                set.add(pre.val);
            } else {
                pre.next = p;
                pre = p;
                p = p.next;
            }
        }
        //根据list删除节点
        pre = pHead;
        p = pre.next;
        while (p != null) {
            if (set.contains(p.val)) {
                pre.next = p.next;
                p = p.next;
            } else {
                pre = p;
                p = p.next;
            }
        }
        //再判断头结点是需要删除
        if (set.contains(pHead.val)) {
            pHead = pHead.next;
        }
        return pHead;
    }

    /*
    题目3：
   请实现一个函数用来匹配包括'.'和'*'的正则表达式。
   模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。 在本题中，匹配是指字符串的所有字符匹配整个模式。
   例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
     */
    public boolean match(char[] str, char[] pattern) {
        String s = String.valueOf(str);
        String p = String.valueOf(pattern);
        Pattern pa = Pattern.compile(p);
        Matcher matcher = pa.matcher(s);
        return matcher.matches();
    }

    /*
    题目4：
    请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
    例如，字符串"+100","5e2","-123","3.1416"和"-1E-16"都表示数值。
     但是"12e","1a3.14","1.2.3","+-5"和"12e+4.3"都不是。
     */
    public boolean isNumeric(char[] str) {
        String pattern = "[+-]?\\d*(\\.\\d*)?([eE][+-]?\\d+)?";

        Pattern p1 = Pattern.compile(pattern);
        Matcher m1 = p1.matcher(String.valueOf(str));
        return m1.matches();
    }

    /*
    题目5:
    输入一个链表，输出该链表中倒数第k个结点。
     */
    public ListNode FindKthToTail(ListNode head, int k) {
        if (head == null)
            return null;
        int len = getListLen(head);
        int c = 0;
        ListNode p = head;
        while (p != null) {
            ++c;
            if (c == (len - k + 1))
                return p;
            p = p.next;
        }
        return null;
    }

    private int getListLen(ListNode head) {
        ListNode p = head;
        int c = 0;
        while (p != null) {
            ++c;
            p = p.next;
        }
        return c;
    }

    /*
    题目6：
    输入一个链表，反转链表后，输出新链表的表头。
     */
    public ListNode ReverseList(ListNode head) {
        if (head == null)
            return null;
        ListNode last = head;
        ListNode p = head.next;
        while (p != null) {
            last.next = p.next;
            p.next = head;
            head = p;
            p = last.next;
        }
        return head;
    }

    /*
    题目7：
    输入两个单调递增的链表，输出两个链表合成后的链表，
    当然我们需要合成后的链表满足单调不减规则。
     */
//    public ListNode Merge(ListNode list1, ListNode list2) {
//        if (list1 == null || list2 == null)
//            return null;
//        ListNode list = null;
//        ListNode r = list;
//        ListNode p = list1, q = list2;
//        while (p != null && q != null) {
//            if (p.val < q.val) {
//                if (list == null) {
//                    list = p;
//
//                } else {
//                    r.next = p;
//                }
//                r = p;
//                r.next = null;
//                p = p.next;
//            } else {
//                if (list == null) {
//                    list = q;
//
//                } else {
//                    r.next = q;
//                }
//                r = q;
//                r.next = null;
//                q = q.next;
//            }
//        }
//        if (p != null)
//            r.next = p;
//        if (q != null)
//            r.next = q;
//        return list;
//    }

    class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }
}
