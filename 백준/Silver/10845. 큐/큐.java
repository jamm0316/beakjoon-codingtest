import java.io.*;
import java.util.*;

public class Main {
    static class PersonalQueue {
        static Deque<Integer> dequeue = new ArrayDeque<>();

        public void push(int x) {
            dequeue.offer(x);
        }

        public int pop() {
            if (dequeue.size() == 0) {
                return -1;
            } else {
                return dequeue.poll();
            }
        }

        public int size() {
            return dequeue.size();
        }

        public int empty() {
            if (dequeue.isEmpty()) {
                return 1;
            } else {
                return 0;
            }
        }

        public int front() {
            if (dequeue.size() == 0) {
                return -1;
            } else {
                return dequeue.getFirst();
            }
        }

        public int back() {
            if (dequeue.size() == 0) {
                return -1;
            } else {
                return dequeue.getLast();
            }
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        PersonalQueue deque = new PersonalQueue();
        int N = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String method = st.nextToken();
            if (method.equals("push")) {
                int x = Integer.parseInt(st.nextToken());
                deque.push(x);
            } else if (method.equals("pop")) {
                bw.write(parseStr(deque.pop()));
                bw.newLine();
            } else if (method.equals("size")) {
                bw.write(parseStr(deque.size()));
                bw.newLine();
            } else if (method.equals("empty")) {
                bw.write(parseStr(deque.empty()));
                bw.newLine();
            } else if (method.equals("front")) {
                bw.write(parseStr(deque.front()));
                bw.newLine();
            } else if (method.equals("back")) {
                bw.write(parseStr(deque.back()));
                bw.newLine();
            }
        }
        bw.close();
        br.close();
    }

    static private String parseStr(int x) {
        return String.valueOf(x);
    }
}
