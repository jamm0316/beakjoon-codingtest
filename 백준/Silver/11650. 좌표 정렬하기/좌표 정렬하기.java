import java.util.*;
import java.io.*;

public class Main {
    static class Position {
        public int xPosition;
        public int yPosition;

        public Position(int x, int y) {
            this.xPosition = x;
            this.yPosition = y;
        }

        public int getXPosition() {
            return xPosition;
        }

        public int getYPosition() {
            return yPosition;
        }

        @Override
        public String toString() {
            return xPosition + " " + yPosition;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        List<Position> array = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            array.add(new Position(x, y));
        }

        array.stream().sorted(Comparator.comparingInt(Position::getXPosition)
                        .thenComparingInt(Position::getYPosition))
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
