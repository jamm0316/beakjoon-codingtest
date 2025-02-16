import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        Stack<Integer> stack = new Stack<>();
        int N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String method = st.nextToken();
            if (method.equals("push")) {
                Integer num = Integer.parseInt(st.nextToken());
                stack.push(num);
            } else {
                if (method.equals("pop")) {
                    if (stack.isEmpty()) {
                        bw.write("-1");
                    } else {
                        bw.write(String.valueOf(stack.pop()));
                    }
                } else if (method.equals("size")) {
                    bw.write(String.valueOf(stack.size()));
                } else if (method.equals("empty")) {
                    if (stack.isEmpty()) {
                        bw.write("1");
                    } else {
                        bw.write("0");
                    }
                } else if (method.equals("top")) {
                    if (stack.isEmpty()) {
                        bw.write("-1");
                    } else {
                        bw.write(String.valueOf(stack.peek()));
                    }
                }
                bw.newLine();
            }
        }
        bw.close();
        br.close();
    }
}
