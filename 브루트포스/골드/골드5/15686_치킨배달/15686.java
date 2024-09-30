import java.util.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        // 입력 처리 부분
        Scanner sc = new Scanner(System.in);
        StringBuilder input_data = new StringBuilder();
        while (sc.hasNextLine()) {
            input_data.append(sc.nextLine()).append("\n");
        }
        sc.close();

        // 입력 데이터를 ParseData에 전달
        ParseData parseData = new ParseData(input_data.toString());
        MinOfChickenStreet converter = new MinOfChickenStreet(parseData.N, parseData.M, parseData.graph);
        System.out.println(converter.isMinStreet());
    }
}

class ParseData {
    int N, M;
    int[][] graph;

    public ParseData(String inputData) {
        String[] lines = inputData.split("\n");
        String[] firstLine = lines[0].split(" ");
        this.N = Integer.parseInt(firstLine[0]);
        this.M = Integer.parseInt(firstLine[1]);

        this.graph = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] row = lines[i + 1].split(" ");
            for (int j = 0; j < N; j++) {
                this.graph[i][j] = Integer.parseInt(row[j]);
            }
        }
    }
}

class MinOfChickenStreet {
    private int N, M;
    private int[][] graph;
    private List<int[]> houses;
    private List<int[]> chickenStores;

    public MinOfChickenStreet(int N, int M, int[][] graph) {
        this.N = N;
        this.M = M;
        this.graph = graph;
        this.houses = findHouses();
        this.chickenStores = findChickenRestaurants();
    }

    public int isMinStreet() {
        int minDistance = Integer.MAX_VALUE;
        // 치킨집 중 M개 선택한 조합 중 최소 거리를 찾음
        List<List<int[]>> chickenCombinations = combinations(chickenStores, M);
        for (List<int[]> chickenComb : chickenCombinations) {
            int totalDistance = calculateTotalDistance(chickenComb);
            minDistance = Math.min(minDistance, totalDistance);
        }
        return minDistance;
    }

    private int calculateTotalDistance(List<int[]> chickenComb) {
        int totalDistance = 0;
        // 각 집에 대해 가장 가까운 치킨집과의 거리 계산
        for (int[] house : houses) {
            int hx = house[0];
            int hy = house[1];
            int minDist = Integer.MAX_VALUE;
            for (int[] chicken : chickenComb) {
                int cx = chicken[0];
                int cy = chicken[1];
                int dist = Math.abs(hx - cx) + Math.abs(hy - cy);
                minDist = Math.min(minDist, dist);
            }
            totalDistance += minDist;
        }
        return totalDistance;
    }

    private List<int[]> findHouses() {
        List<int[]> houses = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (graph[i][j] == 1) {
                    houses.add(new int[]{i, j});
                }
            }
        }
        return houses;
    }

    private List<int[]> findChickenRestaurants() {
        List<int[]> chickenStores = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (graph[i][j] == 2) {
                    chickenStores.add(new int[]{i, j});
                }
            }
        }
        return chickenStores;
    }

    private List<List<int[]>> combinations(List<int[]> list, int M) {
        List<List<int[]>> result = new ArrayList<>();
        combineHelper(list, new ArrayList<>(), result, 0, M);
        return result;
    }

    private void combineHelper(List<int[]> list, List<int[]> temp, List<List<int[]>> result, int start, int M) {
        if (temp.size() == M) {
            result.add(new ArrayList<>(temp));
            return;
        }

        for (int i = start; i < list.size(); i++) {
            temp.add(list.get(i));
            combineHelper(list, temp, result, i + 1, M);
            temp.remove(temp.size() - 1);
        }
    }
}
