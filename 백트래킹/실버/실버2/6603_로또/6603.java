import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<List<Integer>> queries = readInput(sc);

        for (int idx = 0; idx < queries.size(); idx++) {
            List<Integer> query = queries.get(idx);
            if (query.get(0) == 0) {
                return;
            }

            List<Integer> lottoList = query.subList(1, query.size());
            printCombinations(lottoList);

            if (idx < queries.size() - 2) {
                System.out.println();
            }
        }

        sc.close();
    }

    public static void printCombinations(List<Integer> lottoList) {
        int sequenceLength = 6;
        backtrack(sequenceLength, new ArrayList<>(), lottoList, 0);
    }

    public static void backtrack(int sequenceLength, List<Integer> sequence, List<Integer> lottoList, int start) {
        if (sequence.size() == sequenceLength) {
            System.out.println(String.join(" ", sequence.toString().replaceAll("[\\[\\],]", "")));
            return;
        }

        for (int i = start; i < lottoList.size(); i++) {
            sequence.add(lottoList.get(i));
            backtrack(sequenceLength, sequence, lottoList, i + 1);
            sequence.remove(sequence.size() - 1);
        }
    }

    public static List<List<Integer>> readInput(Scanner sc) {
        List<List<Integer>> queries = new ArrayList<>();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.trim().isEmpty()) {
                break;
            }

            String[] tokens = line.split(" ");
            List<Integer> query = new ArrayList<>();
            for (String token : tokens) {
                query.add(Integer.parseInt(token));
            }
            queries.add(query);
        }
        return queries;
    }
}
