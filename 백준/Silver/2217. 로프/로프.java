import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int totalRopes = input.nextInt();
        List<Integer> ropes = new ArrayList<>();
        int maxWeight = Integer.MIN_VALUE;

        for (int i = 0; i < totalRopes; i++) {
            ropes.add(input.nextInt());
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
