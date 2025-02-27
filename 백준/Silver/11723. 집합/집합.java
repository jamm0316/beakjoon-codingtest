import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int S = 0;
        int allSet = (1 << 21) - 1;  // 1부터 20까지의 모든 수를 포함하는 비트마스크
        int M = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < M; i++) {
            String[] command = br.readLine().split(" ");
            String op = command[0];
            
            if (op.equals("add")) {
                int x = Integer.parseInt(command[1]);
                S |= (1 << x);
            } else if (op.equals("remove")) {
                int x = Integer.parseInt(command[1]);
                S &= ~(1 << x);
            } else if (op.equals("check")) {
                int x = Integer.parseInt(command[1]);
                sb.append((S & (1 << x)) != 0 ? "1\n" : "0\n");
            } else if (op.equals("toggle")) {
                int x = Integer.parseInt(command[1]);
                S ^= (1 << x);
            } else if (op.equals("all")) {
                S = allSet;
            } else if (op.equals("empty")) {
                S = 0;
            }
        }
        
        // 결과 출력
        System.out.print(sb);
    }
}