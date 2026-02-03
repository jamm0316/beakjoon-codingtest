import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line = br.readLine();
        Set<String> strSet = new HashSet<>();
        int size = line.length();
        for (int i = 0; i < size; i++) {
            String cur = line.substring(i, size);
            strSet.add(cur);
        }

        List<String> strList = new ArrayList<>(strSet);
        strList.sort(Comparator.naturalOrder());
        
        StringBuilder sb = new StringBuilder();
        for (String each : strList) {
            sb.append(each).append('\n');
        }
        System.out.println(sb.toString());
    }
}