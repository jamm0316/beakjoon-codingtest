import java.util.*;
import java.io.*;

public class Main {
    static int sequenceNum;
    static int session;
    static int[] sequence;
    static int[][] queries;

    public static void main(String[] args) throws IOException {
        initData();
        int[] prefixSum = makePrefixSum(sequenceNum, sequence);
        for (int[] query : queries) {
            int start = query[0];
            int end = query[1];

            int eachSum = prefixSum[end] - prefixSum[start - 1];
            System.out.println(eachSum);
        }
    }

    private static int[] makePrefixSum(int sequenceNum, int[] sequence) {
        int[] prefixSum = new int[sequenceNum];
        for (int i = 1; i < sequenceNum; i++) {
            prefixSum[i] = prefixSum[i - 1] + sequence[i];
        }
        return prefixSum;
    }

    private static void initData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        sequenceNum = Integer.parseInt(st.nextToken()) + 1;
        session = Integer.parseInt(st.nextToken());
        sequence = new int[sequenceNum];
        queries = new int[session][2];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < sequenceNum; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < session; i++) {
            st = new StringTokenizer(br.readLine());
            queries[i][0] = Integer.parseInt(st.nextToken());
            queries[i][1] = Integer.parseInt(st.nextToken());
        }
    }
}
