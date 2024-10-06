import java.util.*;

public class CCTVSurveillance {
    static int N, M;
    static int[][] office;
    static List<int[]> cctvs = new ArrayList<>();
    static int minBehindArea;
    
    static int[][] directions = {
        {-1, 0}, // 상
        {0, 1},  // 우
        {1, 0},  // 하
        {0, -1}  // 좌
    };

    static Map<Integer, int[][]> cctvsDirections = new HashMap<>() {{
        put(1, new int[][]{{0}, {1}, {2}, {3}});              // 상, 우, 하, 좌
        put(2, new int[][]{{0, 2}, {1, 3}});                  // 상하, 우좌
        put(3, new int[][]{{0, 1}, {1, 2}, {2, 3}, {3, 0}});  // 상우, 우하, 하좌, 좌상
        put(4, new int[][]{{0, 1, 2}, {1, 2, 3}, {2, 3, 0}, {3, 0, 1}}); // 상우하, 우하좌, 하좌상, 좌상우
        put(5, new int[][]{{0, 1, 2, 3}});                    // 상하좌우
    }};
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        office = new int[N][M];
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                office[i][j] = sc.nextInt();
                if (1 <= office[i][j] && office[i][j] <= 5) {
                    cctvs.add(new int[]{i, j, office[i][j]});
                }
            }
        }
        
        minBehindArea = N * M;
        backtrack(0, office);
        System.out.println(minBehindArea);
    }

    static void watchArea(int x, int y, int[] directionSet, int[][] tempOffice) {
        for (int d : directionSet) {
            int nx = x, ny = y;
            while (true) {
                nx += directions[d][0];
                ny += directions[d][1];

                if (nx >= 0 && nx < N && ny >= 0 && ny < M && tempOffice[nx][ny] != 6) {
                    if (tempOffice[nx][ny] == 0) {
                        tempOffice[nx][ny] = -1;  // '#' 대신 -1로 표시
                    }
                } else {
                    break;
                }
            }
        }
    }

    static void backtrack(int depth, int[][] office) {
        if (depth == cctvs.size()) {
            int behindArea = 0;
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (office[i][j] == 0) {
                        behindArea++;
                    }
                }
            }
            minBehindArea = Math.min(minBehindArea, behindArea);
            return;
        }
        
        int[] cctv = cctvs.get(depth);
        int x = cctv[0], y = cctv[1], cctvType = cctv[2];
        
        for (int[] directionSet : cctvsDirections.get(cctvType)) {
            int[][] tempOffice = copyOffice(office);  // 사무실 복사
            watchArea(x, y, directionSet, tempOffice);
            backtrack(depth + 1, tempOffice);
        }
    }

    static int[][] copyOffice(int[][] office) {
        int[][] newOffice = new int[N][M];
        for (int i = 0; i < N; i++) {
            System.arraycopy(office[i], 0, newOffice[i], 0, M);
        }
        return newOffice;
    }
}
