import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            int command = Integer.parseInt(br.readLine());
            if (command == 0) {
                if (queue.isEmpty()) {
                    bw.write(String.valueOf(0));
                    bw.newLine();
                } else {
                    bw.write(String.valueOf(queue.poll()));
                    bw.newLine();
                }
            } else {
                queue.offer(command);
            }
        }
        bw.close();
        br.close();
    }
}
