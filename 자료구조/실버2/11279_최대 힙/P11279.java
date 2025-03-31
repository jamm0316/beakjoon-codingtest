import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Queue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int command = Integer.parseInt(br.readLine());
            if (command == 0) {
                if (queue.isEmpty()) {
                    writeNumber(0);
                } else {
                    writeNumber(queue.poll());
                }
            } else {
                queue.offer(command);
            }
        }
        bw.close();
        br.close();
    }

    private static void writeNumber(int x) throws IOException {
        bw.write(String.valueOf(x));
        bw.newLine();
    }
}
