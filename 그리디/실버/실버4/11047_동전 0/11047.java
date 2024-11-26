import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);

        int query = input.nextInt();
        int targetMoney = input.nextInt();
        List<Integer> coins = new ArrayList<>();

        for (int i = 0; i < query; i++) {
            coins.add(input.nextInt());
        }

        coins.sort(Comparator.reverseOrder());

        int count = 0;
        for (int i = 0; i < query; i++) {
            if (coins.get(i) <= targetMoney) {
                count += targetMoney / coins.get(i);
                targetMoney %= coins.get(i);
            }
        }
        System.out.println(count);
    }
}
