import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static int K;
    private static int[] arr;
    public static void main(String[] args) throws IOException {
        inputWork();
        
        int left = 0;
        int right = 1;
        Map<Integer, Integer> numberMap = new HashMap<>();
        numberMap.put(arr[left], 1);
        int maxLength = 0;
        while(right < N) {
            numberMap.put(arr[right], numberMap.getOrDefault(arr[right], 0) + 1);

            while (numberMap.get(arr[right]) > K) {
                numberMap.put(arr[left], numberMap.get(arr[left]) - 1);
                if (numberMap.get(arr[left]) == 0) {
                    numberMap.remove(arr[left]);
                }
                left++;
            }
            maxLength = Math.max(maxLength, right - left + 1);
            right++;
        }
        System.out.println(maxLength);
    }
    
    private static void inputWork() throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }
}