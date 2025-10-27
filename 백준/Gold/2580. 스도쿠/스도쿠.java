import java.io.*;
import java.util.*;

public class Main {
    static int[][] board = new int[9][9];
    static boolean[][] rowUsed = new boolean[9][10];
    static boolean[][] colUsed = new boolean[9][10];
    static boolean[][] boxUsed = new boolean[9][10];
    static List<int[]> blanks = new ArrayList<>();
    static StringBuilder out = new StringBuilder();
    static boolean solved = false;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int r = 0; r < 9; r++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c = 0; c < 9; c++) {
                int v = Integer.parseInt(st.nextToken());
                board[r][c] = v;
                if (v == 0) {
                    blanks.add(new int[]{r, c});
                } else {
                    rowUsed[r][v] = true;
                    colUsed[c][v] = true;
                    boxUsed[boxIdx(r, c)][v] = true;
                }
            }
        }
        dfs(0);

        // 출력
        for (int r = 0; r < 9; r++) {
            for (int c = 0; c < 9; c++) {
                out.append(board[r][c]).append(' ');
            }
            out.append('\n');
        }
        System.out.print(out);
    }

    static int boxIdx(int r, int c) { return (r / 3) * 3 + (c / 3); }

    static void dfs(int idx) {
        if (idx == blanks.size()) {
            solved = true;
            return;
        }
        // 간단한 휴리스틱: 후보가 적어 보이는 순서로 탐색하려면 여기서 선택 정렬도 가능
        int r = blanks.get(idx)[0];
        int c = blanks.get(idx)[1];
        int b = boxIdx(r, c);

        for (int v = 1; v <= 9; v++) {
            if (rowUsed[r][v] || colUsed[c][v] || boxUsed[b][v]) continue;
            // 선택
            board[r][c] = v;
            rowUsed[r][v] = colUsed[c][v] = boxUsed[b][v] = true;

            dfs(idx + 1);
            if (solved) return;

            // 되돌리기
            board[r][c] = 0;
            rowUsed[r][v] = colUsed[c][v] = boxUsed[b][v] = false;
        }
    }
}