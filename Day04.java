package JzOffer;

/**
 * @author 骑士逸
 * @version 1.0
 * @date 2020/5/24 7:22
 */
public class Day04 {

    private int r;
    private int c;
    private int sum = 0;

    /*
    题目1：
    地上有一个m行和n列的方格。一个机器人从坐标0,0的格子开始移动，
    每一次只能向左，右，上，下四个方向移动一格，
    但是不能进入行坐标和列坐标的数位之和大于k的格子。
    例如，当k为18时，机器人能够进入方格（35,37），因为3+5+3+7 = 18。
    但是，它不能进入方格（35,38），因为3+5+3+8 = 19。
    请问该机器人能够达到多少个格子？
     */
    public int movingCount(int threshold, int rows, int cols) {
        this.r = rows;
        this.c = cols;
        int visit[][] = new int[rows][cols];
        dfs(threshold, 0, 0, visit);
        return sum;
    }

    private void dfs(int t, int i, int j, int v[][]) {
        if (i < 0 || i >= r || j < 0 || j >= c || v[i][j] == 1 || pass(t, i, j) == false)
            return;
        v[i][j] = 1;
        sum++;
        int[][] next = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int n[] : next) {
            dfs(t, i + n[0], j + n[1], v);
        }
    }

    private boolean pass(int threshold, int rows, int cols) {
        if (add(rows, cols) > threshold)
            return false;
        return true;
    }

    private int add(int i, int j) {
        char ch1[] = String.valueOf(i).toCharArray();
        char ch2[] = String.valueOf(j).toCharArray();
        int sum = 0;
        for (int k = 0; k < ch1.length; ++k)
            sum += Integer.parseInt(String.valueOf(ch1[k]));
        for (int k = 0; k < ch2.length; ++k)
            sum += Integer.parseInt(String.valueOf(ch2[k]));
        return sum;
    }

    /*
    题目2：
    把一根绳子剪成多段，并且使得每段的长度乘积最大。
    思路：尽可能多的把绳子剪成3一小段；
     */
    public int integerBreak(int n) {
        if (n == 1)
            return 0;
        if (n == 2)
            return 1;
        if (n == 3)
            return 2;
        if (n == 4)
            return 4;
        int a = 1;
        while (n >= 5) {
            n = n - 3;
            a = 3 * a;
        }
        return a * n;
    }

    /*
    题目3：
    输入一个整数，输出该数二进制表示中 1 的个数。
     */
    public int numberOf1(int n) {
        //循环把十进制转换成二进制
        char[] ch = Integer.toBinaryString(n).toCharArray();
        int count = 0;
        for (char c : ch) {
            if (Integer.parseInt(String.valueOf(c)) == 1)
                ++count;
        }
        return count;
    }

    //方法2:n&(n-1)表示去除二进制中的最低位，当n大于0时，即可循环统计1的个数
    public int NumberOf1(int n) {
        int cnt = 0;
        while (n != 0) {
            cnt++;
            n &= (n - 1);
        }
        return cnt;
    }

    /*
    题目4：
    给定一个 double 类型的浮点数 base 和 int 类型的整数 exponent，
    求 base 的 exponent 次方。
     */
    public double Power(double base, int exponent) {
        return Math.pow(base, exponent);
    }

    /*
    题目5：
    输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。
    比如输入 3，则打印出 1、2、3 一直到最大的 3 位数即 999。
     */
    public void printNum(int n) {
        int i = 1;
        while (String.valueOf(i).length() <= n)
            System.out.println(i++);
    }

    /*
    题目6：
    输入一个整数数组，实现一个函数来调整该数组中数字的顺序，
    使得所有的奇数位于数组的前半部分，所有的偶数位于数组的后半部分，
    并保证奇数和奇数，偶数和偶数之间的相对位置不变。
     */
//    public void reOrderArray(int[] array) {
//        int i = 0;
//        //让i指向第一个偶数
//        for (i = 0; i < array.length; ++i) {
//            if (array[i] % 2 == 0)
//                break;
//        }
//        for (int j = i+1; j < array.length; ++j) {
//            if (array[j] % 2 != 0 && i < j &&i<array.length) {
//                //交换
//                int t = array[i];
//                array[i] = array[j];
//                array[j] = t;
//                ++i;
//            }
//        }
//        for(int k:array)
//            System.out.print(k+" ");
//    }

}
