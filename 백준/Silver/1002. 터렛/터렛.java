import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int r1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());
            
            int dx = x2 - x1;
            int dy = y2 - y1;
            int distanceSquared = dx * dx + dy * dy;
            int sumR = r1 + r2;
            int diffR = Math.abs(r1 - r2);
            
            if (distanceSquared == 0) {
                if (r1 == r2) {
                    sb.append(-1).append("\n"); // 무한대
                } else {
                    sb.append(0).append("\n"); // 중심 같지만 반지름 다름
                }
            } else if (distanceSquared > sumR * sumR) {
                sb.append(0).append("\n"); // 서로 떨어짐
            } else if (distanceSquared < diffR * diffR) {
                sb.append(0).append("\n"); // 한 원이 다른 원 안에 있음
            } else if (distanceSquared == sumR * sumR || distanceSquared == diffR * diffR) {
                sb.append(1).append("\n"); // 외접 or 내접
            } else {
                sb.append(2).append("\n"); // 두 점에서 만남
            }
        }

        System.out.print(sb);
    }
}