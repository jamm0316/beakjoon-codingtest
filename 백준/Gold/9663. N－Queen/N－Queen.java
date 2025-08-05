import java.io.*;
import java.util.*;

public class Main {
    static int N, count;
    static int[] board;
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        board = new int[N];
        dfs(0);
        System.out.println(count);
    }

    private static void dfs(int row) {
        if (row == N) {
            count++;
            return;
        }

        for (int col = 0; col < N; col++) {
            if (isPossible(row, col)) {
                board[row] = col;
                dfs(row + 1);
            }
        }
    }

    private static boolean isPossible(int row, int col) {
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
