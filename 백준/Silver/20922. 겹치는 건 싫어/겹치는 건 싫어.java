import java.io.*;
import java.util.*;

class Main {
    private static int N;
    private static int K;
    private static int[] arr;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        

        Map<Integer, Integer> map = new HashMap<>();
        int left = 0;
        int right = 1;
        int maxLength = 0;

        map.put(arr[left], 1);
        while (right < N) {
            map.put(arr[right], map.getOrDefault(arr[right], 0) + 1);
            while (map.get(arr[right]) > K) {
                map.put(arr[left], map.get(arr[left]) - 1);
                left++;
                if (map.get(arr[left]) == 0) {
                    map.remove(arr[left]);
                }
            }
            maxLength = Math.max(maxLength, right - left + 1);
            right++;
        }
        System.out.println(maxLength);
    }
}
