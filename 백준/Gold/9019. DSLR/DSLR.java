import java.util.*;
import java.io.*;

public class Main {
    static class Node {
        int value;
        String command;

        Node(int value, String command) {
            this.value = value;
            this.command = command;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());

            boolean[] visited = new boolean[10_000];
            Queue<Node> queue = new LinkedList<>();
            queue.add(new Node(start, ""));
            visited[start] = true;

            while (!queue.isEmpty()) {
                Node cur = queue.poll();

                if (cur.value == target) {
                    bw.write(cur.command + '\n');
                    break;
                }

                int d = (cur.value * 2) % 10_000;
                if (!visited[d]) {
                    visited[d] = true;
                    queue.add(new Node(d, cur.command + "D"));
                }

                int s = cur.value == 0 ? 9999 : cur.value - 1;
                if (!visited[s]) {
                    visited[s] = true;
                    queue.add(new Node(s, cur.command + "S"));
                }

                int l = (cur.value % 1_000) * 10 + cur.value / 1_000;
                if (!visited[l]) {
                    visited[l] = true;
                    queue.add(new Node(l, cur.command + "L"));
                }

                int r = (cur.value % 10) * 1000 + cur.value / 10;
                if (!visited[r]) {
                    visited[r] = true;
                    queue.add(new Node(r, cur.command + "R"));
                }
            }
        }
        bw.close();
        br.close();
    }
}
