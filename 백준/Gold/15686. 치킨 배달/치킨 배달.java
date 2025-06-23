import java.io.*;
import java.util.*;

public class Main {
    static class Position {
        final int x;
        final int y;
        final int type;  // 1: 가정집, 2: 치킨집

        Position(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }

    }

    static int result = Integer.MAX_VALUE;
    static int N, M;
    static boolean[][] visited;
    static List<Position> houseList = new ArrayList<>();
    static List<Position> chickenList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        //map 초기화
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

        //백트래킹
        combination(0, new ArrayList<>());
        System.out.println(result);
    }

    private static void combination(int start, List<Position> selected) {
        if (selected.size() == M) {
            result = Math.min(result, calculateCityChickenDistance(selected));
            return;
        }

        for (int i = start; i < chickenList.size(); i++) {
            selected.add(chickenList.get(i));
            combination(i + 1, selected);
            selected.remove(selected.size() - 1);
        }
    }

    private static int calculateCityChickenDistance(List<Position> selectedChickens) {
        int totalDistance = 0;

        for (Position house : houseList) {
            int minDistance = Integer.MAX_VALUE;

            for (Position chicken : selectedChickens) {
                int distance = Math.abs(house.x - chicken.x) + Math.abs(house.y - chicken.y);
                minDistance = Math.min(minDistance, distance);
            }

            totalDistance += minDistance;
        }
        return totalDistance;
    }
}
