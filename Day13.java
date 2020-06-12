package JzOffer;

import java.util.Arrays;

/**
 * @author 骑士逸
 * @version 1.0
 * @date 2020/6/12 18:11
 */
public class Day13 {
    /*
    题目1：
    请实现一个函数用来找出字符流中第一个只出现一次的字符。
    例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。
    当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
    如果当前字符流没有存在出现一次的字符，返回#字符。
     */
    private int[] array = new int[300];
    private int c = 0;

    public static void main(String[] args) {

    }


    //Insert one char from stringstream
    public void Insert(char ch) {
        if (array[ch] == 0)
            array[ch] = ++c;
        else
            array[ch] = -1; //重复
    }

    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce() {
        int min = Integer.MAX_VALUE;
        int j = -1;
        //找到最小的正数
        for (int i = 0; i < 300; ++i) {
            if (array[i] > 0)
                if (array[i] < min) {
                    min = array[i];
                    j = i; //标记目标字符
                }
        }
        if (j != -1)
            return (char) j;
        return '#';
    }

    /*
    题目2：
    写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
    思路：模拟十进制的加法运算方式
     */
    public int Add(int num1, int num2) {
        int noJw = 0;
        while (num1 != 0) {
            //运算不进位的结果
            noJw = num1 ^ num2;
            //运算进位
            num1 = (num1 & num2) << 1; //需要左移1位
            num2 = noJw;
        }
        return num2;
    }

    /*
    题目4：
    每年六一儿童节,牛客都会准备一些小礼物去看望孤儿院的小朋友,今年亦是如此。
    HF作为牛客的资深元老,自然也准备了一些小游戏。其中,有个游戏是这样的:
    首先,让小朋友们围成一个大圈。然后,他随机指定一个数m,
    让编号为0的小朋友开始报数。每次喊到m-1的那个小朋友要出列唱首歌,
    然后可以在礼品箱中任意的挑选礼物,并且不再回到圈中,
    从他的下一个小朋友开始,继续0...m-1报数....这样下去....
    直到剩下最后一个小朋友,
    可以不用表演,并且拿到牛客名贵的“名侦探柯南”典藏版(名额有限哦!!^_^)。
    请你试着想下,哪个小朋友会得到这份礼品呢？(注：小朋友的编号是从0到n-1)
    如果没有小朋友，请返回-1
     */

    /*
    题目3：
    求1+2+3+...+n，要求不能使用乘除法、
    for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
    思路：1、递归(递归停止的条件是n<=0时)
         2、能用的有：+,-,++,--,位运算符、逻辑运算符等运算符
         3、利用短路与，
     */
    public int Sum_Solution(int n) {
        int sum = n;
        //利用短路与，如果n<=0时，那么后面的递归就不再执行
        boolean flag = (n > 0) && ((sum += Sum_Solution(n - 1)) > 0);
        return sum;
    }

    /*
    题目5：
    LL今天心情特别好,因为他去买了一副扑克牌,发现里面居然有2个大王,2个小王(一副牌原本是54张^_^)...
    他随机从中抽出了5张牌,想测测自己的手气,看看能不能抽到顺子,如果抽到的话,
    他决定去买体育彩票,嘿嘿！！“红心A,黑桃3,小王,大王,方片5”,“Oh My God!”不是顺子.....
    LL不高兴了,他想了想,决定大\小 王可以看成任何数字,并且A看作1,J为11,Q为12,K为13。
    上面的5张牌就可以变成“1,2,3,4,5”(大小王分别看作2和4),“So Lucky!”。LL决定去买体育彩票啦。
    现在,要求你使用这幅牌模拟上面的过程,然后告诉我们LL的运气如何，
    如果牌能组成顺子就输出true，否则就输出false。为了方便起见,你可以认为大小王是0。
     */

    public int LastRemaining_Solution(int n, int m) {
        if (n == 0)
            return -1;
        if (n == 1)
            return 1;
        int size = n; //记录剩余的小朋友
        int num[] = new int[n];
        for (int i = 0; i < n; ++i)
            num[i] = i;
        int j = -1;
        while (size > 1) {
            for (int i = (j + 1) % n, c = -1; ; i = (i + 1) % n) {
                if (num[i] != -1) {
                    ++c;
                }
                if (c == m - 1) {
                    num[i] = -1; //标记这个小朋友退出
                    --size; //小朋友少一个
                    j = i;
                    break;
                }
            }
        }
        for (int i = 0; i < n; ++i)
            if (num[i] != -1)
                return i;
        return -1;
    }

    public boolean isContinuous(int[] numbers) {
        if (numbers.length != 5)
            return false;
        //排序
        Arrays.sort(numbers);
        int c = 0;
        for (int i = 0; i < numbers.length; ++i) {
            if (numbers[i] == 0)
                ++c;
        }
        for (int i = c; i < numbers.length - 1; ++i) {
            if (numbers[i] + 1 == numbers[i + 1])
                continue;
            if (c > 0 && numbers[i] + 1 != numbers[i + 1]) {
                --c;
                numbers[i]++;
                --i;
            } else if (c == 0)
                return false;
        }
        return true;
    }

    /*
   题目6：
   牛客最近来了一个新员工Fish，每天早晨总是会拿着一本英文杂志，写些句子在本子上。
   同事Cat对Fish写的内容颇感兴趣，有一天他向Fish借来翻看，但却读不懂它的意思。
   例如，“student. a am I”。后来才意识到，这家伙原来把句子单词的顺序翻转了，
   正确的句子应该是“I am a student.”。Cat对一一的翻转这些单词顺序可不在行，你能帮助他么？
    */
    public String ReverseSentence(String str) {
        if (str == null)
            return null;
        if (str.trim().equals(""))
            return str;
        String[] strArray = str.split(" ");
        StringBuffer sb = new StringBuffer();
        for (int i = strArray.length - 1; i >= 0; --i)
            sb.append(strArray[i] + " ");
        return sb.toString().substring(0, sb.length() - 1);
    }

}
