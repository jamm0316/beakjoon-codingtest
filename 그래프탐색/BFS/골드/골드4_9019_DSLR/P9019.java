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
            Queue<Node> queue = new LinkedList<>();
            boolean[] visited = new boolean[10_000];
            StringTokenizer st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());
            queue.offer(new Node(start, ""));
            visited[start] = true;

            while (!queue.isEmpty()) {
                Node curNode = queue.poll();
                if (curNode.value == target) {
                    bw.write(curNode.command + "\n");
                    break;
                }

                int d = curNode.value * 2 % 10_000;
                if (!visited[d]) {
                    visited[d] = true;
                    queue.offer(new Node(d, curNode.command + "D"));
                }

                int s = curNode.value == 0 ? 9999 : curNode.value - 1;
                if (!visited[s]) {
                    visited[s] = true;
                    queue.offer(new Node(s, curNode.command + "S"));
                }

                int l = curNode.value % 1000 * 10 + curNode.value / 1000;
                if (!visited[l]) {
                    visited[l] = true;
                    queue.offer(new Node(l, curNode.command + "L"));
                }

                int r = curNode.value % 10 * 1000 + curNode.value / 10;
                if (!visited[r]) {
                    visited[r] = true;
                    queue.offer(new Node(r, curNode.command + "R"));
                }
            }
        }
        bw.close();
        br.close();
    }
}
