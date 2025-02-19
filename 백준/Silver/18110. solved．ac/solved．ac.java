import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = (int) Math.round((double) N * 0.15);
        List<Integer> list = new ArrayList<>();
        Deque<Integer> deque = new LinkedList<>();


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            list.add(Integer.parseInt(st.nextToken()));
        }

        list.sort(Comparator.naturalOrder());
        list.stream().forEach(i -> deque.offer(i));

        //앞, 뒤 버리기
        for (int i = 0; i < K; i++) {
            deque.pollFirst();
            deque.pollLast();
        }

        int sum = deque.stream().mapToInt(i -> i).sum();
        int level = (int) Math.round((double) sum / deque.size());
        System.out.println(level);
    }
}
