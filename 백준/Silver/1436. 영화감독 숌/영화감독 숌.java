import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int count = 1;
        int series = 666;

        while (true) {
            if (String.valueOf(series).contains("666")) {
                if (count == N) {
                    System.out.println(series);
                    return;
                }
                count++;
            }
            series++;
        }
    }
}