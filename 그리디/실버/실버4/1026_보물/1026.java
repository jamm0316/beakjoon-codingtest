import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        Integer[] A = new Integer[N];
        Integer[] B = new Integer[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            B[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(A);

        Integer[] BIndex = new Integer[B.length];
        for (int i = 0; i < B.length; i++) {
            BIndex[i] = i;
        }
        Arrays.sort(BIndex, (a, b) -> B[b] - B[a]);

        int result = 0;
        for (int i = 0; i < N; i++) {
            result += A[i] * B[BIndex[i]];
        }

        System.out.println(result);
    }
}
