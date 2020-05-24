package JzOffer;

import org.junit.Test;

/**
 * @author 骑士逸
 * @version 1.0
 * @date 2020/5/23 6:45
 */
public class Day03 {
    /*
    题目1：
    请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。
    路径可以从矩阵中的任意一个格子开始，每一步可以在矩阵中向左，向右，向上，向下移动一个格子。
    如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。
   矩阵中包含一条字符串"bcced"的路径，但是矩阵中不包含"abcb"路径，
   因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后，路径不能再次进入该格子。
     */

    public static void main(String[] args) {
        char[] ch = {'a', 'b', 't', 'g', 'c', 'f', 'c', 's', 'j', 'd', 'e', 'h'};
        String str = "bfce";
        System.out.println(new Day03().hasPath(ch, 3, 4, str.toCharArray()));
    }

    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        //将数组转换成二维数组
        int c = 0;
        char[][] ch = new char[rows][cols];
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                ch[i][j] = matrix[c++];
            }
        }

        int [][]visit=new int[rows][cols];

        //使用回溯法进行问题的求解
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                if (getAnswer(ch, rows, cols, i, j, str, 0,visit))
                    return true;
            }
        }
        return false;
    }

    private boolean getAnswer(char[][] ch, int rows, int cols, int r, int c, char[] str, int p,int [][]visit) {
        if (p == str.length)
            return true;
        //判断下标范围的问题
        if (r < 0 || r >= rows || c < 0 || c >= cols || str[p] != ch[r][c] || visit[r][c] == 1)
            return false;
        //标记
        visit[r][c] = 1;
        //递归（回溯）
        int[][] mark = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int m[] : mark) {
            if (getAnswer(ch, rows, cols, r + m[0], c + m[1], str, p + 1,visit))
                return true;
        }
        //不匹配则还原标记
        visit[r][c] = 0;
        return false;
    }
}
