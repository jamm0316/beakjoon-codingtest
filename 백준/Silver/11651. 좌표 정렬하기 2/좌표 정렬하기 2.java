import java.util.*;
import java.io.*;

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

        @Override
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
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            positions.add(new Position(x, y));
        }

        positions.stream().sorted(Comparator.comparingInt(Position::getY)
                        .thenComparingInt(Position::getX))
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
