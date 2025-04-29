import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int count = 0;
    static int[] board;  //board[row] = col
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        N = input.nextInt();
        board = new int[N];
        dfs(0);
        System.out.println(count);
    }

    static void dfs(int row) {
        if (row == N) {
            count++;
            return;
        }

        for (int col = 0; col < N; col++) {
            if (isPossible(row, col)) {
                board[row] = col;
                dfs(row + 1);
                //백트레킹: 따로 치울 필요 없음 (board[row]를 덮어쓰기 떄문)
            }
        }
    }

    static boolean isPossible(int row, int col) {
        for (int i = 0; i < row; i++) {
            if (board[i] == col) {
                return false;
            }
            if (Math.abs(row - i) == Math.abs(col - board[i])) {
                return false;
            }
        }
        return true;
    }
}
