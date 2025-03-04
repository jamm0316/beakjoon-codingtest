import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Set<String> unheard = new HashSet<>();
        List<String> unseenUnheard = new ArrayList<>();

        // 듣도 못한 사람 명단 입력
        for (int i = 0; i < N; i++) {
            unheard.add(br.readLine());
        }

        // 보도 못한 사람 명단 입력 및 교집합 찾기
        for (int i = 0; i < M; i++) {
            String name = br.readLine();
            if (unheard.contains(name)) {
                unseenUnheard.add(name);
            }
        }

        // 사전순 정렬
        Collections.sort(unseenUnheard);

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        sb.append(unseenUnheard.size()).append("\n");
        for (String name : unseenUnheard) {
            sb.append(name).append("\n");
        }

        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}