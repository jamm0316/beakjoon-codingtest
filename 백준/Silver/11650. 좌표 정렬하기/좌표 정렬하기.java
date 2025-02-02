import java.io.*;
import java.util.*;

public class Main {
    static class Position {
        int x;
        int y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public String toString() {
            return x + " " + y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        List<Position> positions = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int curX = Integer.parseInt(st.nextToken());
            int curY = Integer.parseInt(st.nextToken());
            positions.add(new Position(curX, curY));
        }

        positions.stream()
                .sorted(Comparator.comparingInt(Position::getX).thenComparing(Position::getY))
                .forEach(p -> {
                    try {
                        bw.write(p.toString());
                        bw.newLine();
                    } catch (IOException e) {

                    }
                });
        bw.close();
        br.close();
    }
}
