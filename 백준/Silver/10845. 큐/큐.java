import java.util.*;
import java.io.*;

public class Main {
    static Queue<Integer> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String cmd = st.nextToken();
            int inputInt = st.hasMoreTokens() ? Integer.parseInt(st.nextToken()) : -1;
            Integer result = runQueueMethod(cmd, inputInt);
            if (result != null) {
                bw.write(String.valueOf(result));
                bw.newLine();
            }
        }
        bw.close();
        br.close();
    }

    private static Integer runQueueMethod(String cmd, int inputInt) {
        switch (cmd) {
            case "push":
                queue.add(inputInt);
                return null;
            case "pop":
                return queue.isEmpty() ? -1 : queue.poll();
            case "size":
                return queue.size();
            case "empty":
                return queue.isEmpty() ? 1 : 0;
            case "front":
                return queue.isEmpty() ? -1 : queue.peek();
            case "back":
                return queue.isEmpty() ? -1 : ((LinkedList<Integer>) queue).peekLast();
            default:
                return null;
        }
    }
}
