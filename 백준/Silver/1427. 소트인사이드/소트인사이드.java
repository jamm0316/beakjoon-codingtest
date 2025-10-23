import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String str = br.readLine();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < str.length(); i++) {
            pq.offer(Integer.parseInt(String.valueOf(str.charAt(i))));
        }
        while (!pq.isEmpty()) {
            sb.append(pq.poll());
        }
        System.out.println(sb);
    }
}
