package greedy;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int person = Integer.parseInt(st.nextToken());
        int totalTime = 0;
        int prefixSum = 0;
        List<Integer> personList = new ArrayList<Integer>();
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < person; i++) {
            personList.add(Integer.parseInt(st.nextToken()));
        }

        personList.sort(Comparator.naturalOrder());
      
        for (Integer time : personList) {
            prefixSum += time;
            totalTime += prefixSum;
        }

        System.out.println(totalTime);

    }
}
