import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder inputData = new StringBuilder();
        
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if(line.isEmpty()) break;
            inputData.append(line).append("\n");
        }
        
        int[] result = readData(inputData.toString());
        int N = result[0];
        List<Integer> numbers = new ArrayList<>();
        for (int i = 1; i < result.length; i++) {
            numbers.add(result[i]);
        }
        
        int maxValue = printMaxValue(N, numbers);
        System.out.println(maxValue);
        
        scanner.close();
    }

    private static int[] readData(String inputData) {
        String[] lines = inputData.split("\n");
        String[] firstLine = lines[0].split(" ");
        int N = Integer.parseInt(firstLine[0]);
        String[] secondLine = lines[1].split(" ");
        
        int[] numbers = new int[secondLine.length + 1];
        numbers[0] = N;
        for (int i = 0; i < secondLine.length; i++) {
            numbers[i + 1] = Integer.parseInt(secondLine[i]);
        }
        return numbers;
    }

    private static int printMaxValue(int N, List<Integer> numbers) {
        List<List<Integer>> permutationsList = new ArrayList<>();
        makePermutations(numbers, 0, permutationsList);
        return calculatePermutation(N, permutationsList);
    }

    private static void makePermutations(List<Integer> numbers, int index, List<List<Integer>> permutationsList) {
        if (index == numbers.size() - 1) {
            permutationsList.add(new ArrayList<>(numbers));
            return;
        }
        
        for (int i = index; i < numbers.size(); i++) {
            Collections.swap(numbers, i, index);
            makePermutations(numbers, index + 1, permutationsList);
            Collections.swap(numbers, i, index); // swap back
        }
    }

    private static int calculatePermutation(int N, List<List<Integer>> permutationsList) {
        int maxValue = 0;
        
        for (List<Integer> perm : permutationsList) {
            int total = 0;
            for (int i = 0; i < N - 1; i++) {
                total += Math.abs(perm.get(i) - perm.get(i + 1));
            }
            maxValue = Math.max(maxValue, total);
        }
        
        return maxValue;
    }
}
