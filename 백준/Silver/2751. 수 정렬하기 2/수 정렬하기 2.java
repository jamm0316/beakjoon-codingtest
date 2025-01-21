import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        List<Integer> sequence = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            sequence.add(Integer.parseInt(st.nextToken()));
        }

        sequence.stream().sorted(Comparator.naturalOrder())
                .forEach(i -> {
                    try {
                        bw.write(String.valueOf(i));
                        bw.newLine();
                    } catch (IOException e) {

                    }
                });

        bw.close();
        br.close();
    }
}
