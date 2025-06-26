import java.io.*;
import java.util.*;

public class Main {
    static class Position {
        int x;
        int y;
        int type;

        Position(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }
    static List<Position> houseList = new ArrayList<>();
    static List<Position> chickenList = new ArrayList<>();
    static int N, M;
    static int result = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int token = Integer.parseInt(st.nextToken());
                if (token == 1) {
                    houseList.add(new Position(i, j, 1));
                } else if (token == 2) {
                    chickenList.add(new Position(i, j, 2));
                }
            }
        }

        combination(0, new ArrayList<>());
        System.out.println(result);
    }

    static private void combination(int start, List<Position> selected) {
        if (selected.size() == M) {
            result = Math.min(result, calculateMinDist(selected));
            return;
        }

        for (int i = start; i < chickenList.size(); i++) {
            selected.add(chickenList.get(i));
            combination(i + 1, selected);
            selected.remove(selected.size() - 1);
        }
    }

    static private int calculateMinDist(List<Position> selected) {
        int minResult = 0;
        for (Position house : houseList) {
            int result = Integer.MAX_VALUE;
            for (Position chicken : selected) {
                result = Math.min(result, Math.abs(house.x - chicken.x) + Math.abs(house.y - chicken.y));
            }
            minResult += result;
        }
        return minResult;
    }
}
