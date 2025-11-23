import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        // 집합 S를 저장할 HashSet
        HashSet<String> set = new HashSet<>();
        
        // N개의 문자열을 집합에 추가
        for (int i = 0; i < N; i++) {
            set.add(br.readLine());
        }
        
        // M개의 문자열 중 집합에 포함된 개수 세기
        int count = 0;
        for (int i = 0; i < M; i++) {
            if (set.contains(br.readLine())) {
                count++;
            }
        }
        
        System.out.println(count);
    }
}