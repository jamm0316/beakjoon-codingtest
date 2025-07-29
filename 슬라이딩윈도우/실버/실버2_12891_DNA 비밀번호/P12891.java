import java.io.*;
import java.util.*;

public class P12691 {
    static int N, M, count;
    static int[] dnaCondition = new int[4];
    static int[] testDnaCondition = new int[4];  // A C G T
    static String DNA;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        DNA = br.readLine();
        dnaCondition = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < M; i++) {
            addChar(DNA.charAt(i));
        }
        if (checkCondition()) {
            count++;
        }

        for (int i = M; i < N; i++) {
            removeChar(DNA.charAt(i - M));
            addChar(DNA.charAt(i));
            if (checkCondition()) {
                count++;
            }
        }
        System.out.println(count);
    }

    private static void addChar(char c) {
        if (c == 'A') {
            testDnaCondition[0]++;
        } else if (c == 'C') {
            testDnaCondition[1]++;
        } else if (c == 'G') {
            testDnaCondition[2]++;
        } else if (c == 'T') {
            testDnaCondition[3]++;
        }
    }

    private static void removeChar(char c) {
        if (c == 'A') {
            testDnaCondition[0]--;
        } else if (c == 'C') {
            testDnaCondition[1]--;
        } else if (c == 'G') {
            testDnaCondition[2]--;
        } else if (c == 'T') {
            testDnaCondition[3]--;
        }
    }
    private static boolean checkCondition() {
        for (int i = 0; i < 4; i++) {  //A C G T
            if (dnaCondition[i] > testDnaCondition[i]) {
                return false;
            }
        }
        return true;
    }
}
