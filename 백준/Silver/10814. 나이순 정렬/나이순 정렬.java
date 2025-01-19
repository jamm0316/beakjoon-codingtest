import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        List<List<Object>> array = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            List<Object> row = new ArrayList<>();
            row.add(Integer.parseInt(st.nextToken()));
            row.add(st.nextToken());
            array.add(row);
        }

        array.sort(Comparator.comparingInt(o -> (Integer) o.get(0)));
        array.stream().forEach(o -> sb.append(o.get(0)).append(" ").append(o.get(1)).append("\n"));
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
