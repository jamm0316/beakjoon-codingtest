import java.util.*;

public class Main {

    public static List<Integer> findOriginDwarfs(List<Integer> dwarfsHeight) {
        int allDwarfsHeight = 100;
        int numberOfDwarfs = dwarfsHeight.size();
        int totalSum = 0;
        
        for (int height : dwarfsHeight) {
            totalSum += height;
        }
        
        for (int i = 0; i < numberOfDwarfs; i++) {
            for (int j = i + 1; j < numberOfDwarfs; j++) {
                if (totalSum - dwarfsHeight.get(i) - dwarfsHeight.get(j) == allDwarfsHeight) {
                    List<Integer> originDwarfs = new ArrayList<>();
                    for (int k = 0; k < numberOfDwarfs; k++) {
                        if (k != i && k != j) {
                            originDwarfs.add(dwarfsHeight.get(k));
                        }
                    }
                    Collections.sort(originDwarfs);
                    return originDwarfs;
                }
            }
        }
        return null; // 찾지 못했을 경우
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> dwarfsHeight = new ArrayList<>();
        
        for (int i = 0; i < 9; i++) {
            dwarfsHeight.add(scanner.nextInt());
        }
        
        List<Integer> originDwarfs = findOriginDwarfs(dwarfsHeight);
        
        for (int dwarf : originDwarfs) {
            System.out.println(dwarf);
        }
        
        scanner.close();
    }
}
