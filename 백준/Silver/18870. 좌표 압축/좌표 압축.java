import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] basePosition = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Set<Integer> uniquePositionSet = new HashSet<>();
        for (int eachPosition : basePosition) {
            uniquePositionSet.add(eachPosition);
        }

        List<Integer> sortedPositionList = new ArrayList<>(uniquePositionSet);
        sortedPositionList.sort(Comparator.naturalOrder());

        Map<Integer, Integer> compressPositionMap = new HashMap<>();
        for (int i = 0; i < sortedPositionList.size(); i++) {
            compressPositionMap.put(sortedPositionList.get(i), i);
        }

        for (int eachPosition : basePosition) {
            bw.write(compressPositionMap.get(eachPosition) + " ");
        }

        bw.close();
        br.close();
    }
}
