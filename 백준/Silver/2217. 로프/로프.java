import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int totalRopes = Integer.parseInt(br.readLine());
        List<Integer> ropes = new ArrayList<>();
        int maxWeight = Integer.MIN_VALUE;

        for (int i = 0; i < totalRopes; i++) {
            ropes.add(Integer.parseInt(br.readLine()));
        }

        ropes.sort(Comparator.naturalOrder());

        for (int i = 0; i < totalRopes; i++) {
            int currWeight = ropes.get(i) * (totalRopes - i);
            if (maxWeight < currWeight) {
                maxWeight = Math.max(maxWeight, currWeight);
            }
        }
        System.out.println(maxWeight);
    }
}
