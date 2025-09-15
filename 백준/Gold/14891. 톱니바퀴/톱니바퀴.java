import java.io.*;
import java.util.*;

public class Main {
    static class Gear {
        Deque<Character> teeth = new ArrayDeque<>(8);

        Gear(String str) {
            for (int i = 0; i < str.length(); i++) {
                teeth.addLast(str.charAt(i));
            }
        }

        public void rotate(int direction) {
            if (direction == 1) {
                char last = teeth.pollLast();
                teeth.offerFirst(last);
            } else {
                char first = teeth.pollFirst();
                teeth.offerLast(first);
            }
        }

        public char getAt(int idx) {
            Iterator<Character> it = teeth.iterator();
            int i = 0;
            while (it.hasNext()) {
                char c = it.next();
                if (i == idx) return c;
                i++;
            }
            return '0';
        }

        @Override
        public String toString() {
            return teeth.toString();
        }
    }

    static int N_GEARS = 4;
    static int LEFT = 6;
    static int RIGHT = 2;

    static Gear[] gearList;
    static int K;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        gearList = new Gear[N_GEARS];

        for (int i = 0; i < 4; i++) {
            gearList[i] = new Gear(br.readLine());
        }

        K = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());
            int gear = Integer.parseInt(st.nextToken()) - 1;
            int direction = Integer.parseInt(st.nextToken());

            int[] dirs = new int[N_GEARS];
            dirs[gear] = direction;

            // 왼쪽으로 전파
            for (int i = gear; i > 0; i--) {
                if (gearList[i - 1].getAt(RIGHT) != gearList[i].getAt(LEFT)) {
                    dirs[i - 1] = -dirs[i];
                } else {
                    break;
                }
            }

            // 오른쪽 전파
            for (int i = gear; i < N_GEARS - 1; i++) {
                if (gearList[i].getAt(RIGHT) != gearList[i + 1].getAt(LEFT)) {
                    dirs[i + 1] = -dirs[i];
                } else {
                    break;
                }
            }

            // 전파 방향 확정 후, 일괄 회전
            for (int i = 0; i < N_GEARS; i++) {
                if (dirs[i] != 0) gearList[i].rotate(dirs[i]);
            }
        }

        int score = 0;
        for (int i = 0; i < N_GEARS; i++) {
            if (gearList[i].getAt(0) == '1') score += (1 << i);
        }
        System.out.println(score);
    }
}
