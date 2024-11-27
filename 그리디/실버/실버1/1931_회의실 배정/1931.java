import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int query = Integer.parseInt(st.nextToken());
        int[][] timeTable = new int[query][2];

        for (int i = 0; i < query; i++) {
            String[] param = br.readLine().split(" ");
            int[] meeting = Arrays.stream(param)
                    .mapToInt(Integer::parseInt)
                    .toArray();
            timeTable[i][0] = meeting[0];
            timeTable[i][1] = meeting[1];
        }

        Arrays.sort(timeTable, (a, b) -> {
            if (a[1] == b[1]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        int count = 0;
        int lastEndTime = 0;
        for (int[] eachMeeting : timeTable) {
            if (eachMeeting[0] >= lastEndTime) {
                count++;
                lastEndTime = eachMeeting[1];
            }
        }
        System.out.println(count);
    }
}
