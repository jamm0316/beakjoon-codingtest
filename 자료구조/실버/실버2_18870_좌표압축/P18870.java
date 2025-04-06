import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        //input 초기화
        int N = Integer.parseInt(br.readLine());
        int[] positions = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        //Set 초기화
        Set<Integer> uniqueSet = Arrays.stream(positions).boxed().collect(Collectors.toSet());
        
        //List 초기화 및 정렬
        List<Integer> sortedList = new ArrayList<>(uniqueSet);
        sortedList.sort(Comparator.naturalOrder());

        //Map 초기화
        Map<Integer, Integer> compressMap = new HashMap<>();
        for (int i = 0; i < sortedList.size(); i++) {
            compressMap.put(sortedList.get(i), i);
        }

        //결과값 출력
        for (int eachPosition : positions) {
            sb.append(compressMap.get(eachPosition) + " ");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
