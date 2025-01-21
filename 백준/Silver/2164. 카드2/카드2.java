import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        Deque<Integer> cards = new LinkedList<>();

        for (int i = 1; i <= N; i++) {
            cards.add(i);
        }

        while (cards.size() > 1) {
            cards.poll();
            Integer topCard = cards.poll();
            cards.add(topCard);
        }
        bw.write(String.valueOf(cards.getFirst()));
        bw.close();
        br.close();
    }
}
