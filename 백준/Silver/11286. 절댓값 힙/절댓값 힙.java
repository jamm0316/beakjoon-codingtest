import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        Queue<Integer> queue = new PriorityQueue<>((a, b)->
            Math.abs(a) == Math.abs(b) ? a - b : Math.abs(a) - Math.abs(b)
        );

        for (int i = 0; i < N; i++) {
            int command = Integer.parseInt(br.readLine());
            if (command != 0) {
                queue.add(command);
            } else {
                sb.append(queue.isEmpty() ? 0 : queue.poll()).append('\n');
            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
