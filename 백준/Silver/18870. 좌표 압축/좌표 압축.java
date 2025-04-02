import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] positions = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        Set<Integer> uniqueSet = Arrays.stream(positions).boxed().collect(Collectors.toSet());

        List<Integer> sortedList = new ArrayList<>(uniqueSet);
        sortedList.sort(Comparator.naturalOrder());

        Map<Integer, Integer> compressMap = new HashMap<>();
        for (int i = 0; i < sortedList.size(); i++) {
            compressMap.put(sortedList.get(i), i);
        }

        for (int eachPosition : positions) {
            sb.append(compressMap.get(eachPosition) + " ");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
