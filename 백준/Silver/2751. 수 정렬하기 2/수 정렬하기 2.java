import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        List<Integer> array = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            array.add(Integer.parseInt(st.nextToken()));
        }

        array.sort(Comparator.naturalOrder());
        array.stream().forEach(i -> sb.append(i).append("\n"));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
