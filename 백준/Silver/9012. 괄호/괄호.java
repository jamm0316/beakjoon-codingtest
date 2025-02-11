import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            Stack<Character> stack = new Stack<>();
            for (char c : str.toCharArray()) {
                if (c == '(' ) {
                    stack.push('(');
                } else if (!stack.isEmpty() && c == ')' && stack.peek() == '(') {
                    stack.pop();
                } else {
                    stack.push(')');
                }
            }
            if (stack.isEmpty()) {
                bw.write("YES");
                bw.newLine();
            } else {
                bw.write("NO");
                bw.newLine();
            }
        }
        bw.close();
        br.close();
    }
}
