import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        List<Integer> list = new ArrayList<>();

        for (int num : A) {
            int pos = Collections.binarySearch(list, num);  //이진 탐색으로 위치 찾기
            if (pos < 0) {
                pos = -(pos + 1);  //삽입할 위치 변환
            }

            if (pos == list.size()) {
                list.add(num);
            } else {
                list.set(pos, num);
            }
        }
        System.out.println(list.size());
    }
}
