import java.io.*;
import java.util.*;

public class Main {
    public static int getDistanceSquared(int x1, int y1, int x2, int y2) {
        int dx = x1 - x2;
        int dy = y1 - y2;
        return dx * dx + dy * dy;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int n = Integer.parseInt(br.readLine());
            int count = 0;

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int cx = Integer.parseInt(st.nextToken());
                int cy = Integer.parseInt(st.nextToken());
                int r = Integer.parseInt(st.nextToken());

                int rSquared = r * r;

                boolean startInside = getDistanceSquared(x1, y1, cx, cy) < rSquared;
                boolean endInside = getDistanceSquared(x2, y2, cx, cy) < rSquared;

                if (startInside ^ endInside) { // XOR: 한 쪽만 안에 있으면 true
                    count++;
                }
            }

            System.out.println(count);
        }
    }
}