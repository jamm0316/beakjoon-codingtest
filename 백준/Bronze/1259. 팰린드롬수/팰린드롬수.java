import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int isRunning = 1;
        while (isRunning != 0) {
            String number = st.nextToken();
            boolean isTrue = true;
            if (Integer.parseInt(number) != 0) {
                for (int i = 0; i < number.length() / 2; i++) {
                    char c = number.charAt(i);
                    char d = number.charAt(number.length() - i - 1);
                    if (c != d) {
                        isTrue = false;
                    }
                }

                if (isTrue) {
                    sb.append("yes").append("\n");
                    st = new StringTokenizer(br.readLine());
                } else {
                    sb.append("no").append("\n");
                    st = new StringTokenizer(br.readLine());
                }
            } else if (Integer.parseInt(number) == 0) {
                isRunning = 0;
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
