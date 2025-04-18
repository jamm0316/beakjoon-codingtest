import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 100001;

    static class Node {
        int position;
        int time;
        Node(int position, int time) {
            this.position = position;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        if (start >= end) {
            System.out.println(start - end);
            return;
        }

        boolean[] visited = new boolean[MAX];
        Deque<Node> deque = new ArrayDeque<>();
        deque.offer(new Node(start, 0));

        while (!deque.isEmpty()) {
            Node cur = deque.poll();
            int pos = cur.position;

            if (visited[pos]) continue;
            visited[pos] = true;

            if (pos == end) {
                System.out.println(cur.time);
                return;
            }

            if (pos * 2 < MAX) {
                deque.offerFirst(new Node(pos * 2, cur.time));
            }

            if (pos + 1 < MAX) {
                deque.offerLast(new Node(pos + 1, cur.time + 1));
            }

            if (pos - 1 >= 0) {
                deque.offerLast(new Node(pos - 1, cur.time + 1));
            }
        }
    }
}