import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] parent, travelList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        parent = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i; // 초기화
        }

        // 그래프 입력 및 Union 처리
        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int connected = Integer.parseInt(st.nextToken());
                if (connected == 1) {
                    union(i, j);
                }
            }
        }

        travelList = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 여행 경로 도시들이 모두 같은 그룹인지 확인
        boolean possible = true;
        int root = find(travelList[0]);
        for (int i = 1; i < M; i++) {
            if (find(travelList[i]) != root) {
                possible = false;
                break;
            }
        }

        System.out.println(possible ? "YES" : "NO");
    }

    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]); // 경로 압축
    }

    static void union(int a, int b) {
        int rootA = find(a);
        int rootB = find(b);
        if (rootA != rootB) {
            parent[rootB] = rootA;
        }
    }
}