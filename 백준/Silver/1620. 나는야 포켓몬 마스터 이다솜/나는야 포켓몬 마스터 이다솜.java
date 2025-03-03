import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Map<Integer, String> map = new HashMap<>();
        Map<String, Integer> intMap = new HashMap<>();
        List<String> queries = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            String name = br.readLine();
            map.put(i, name);
            intMap.put(name, i);
        }

        for (int i = 0; i < M; i++) {
            String input = br.readLine();
            if (Character.isDigit(input.charAt(0))) {
                bw.write(map.get(Integer.parseInt(input)));
                bw.newLine();
            } else {
                bw.write(String.valueOf(intMap.get(input)));
                bw.newLine();
            }
        }
        bw.close();
        br.close();
    } 
}
