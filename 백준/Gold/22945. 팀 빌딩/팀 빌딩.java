import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        int[] arr = new int[n];
        
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        
        int left = 0;
        int right = n - 1;
        int maxPower = 0;
        
        while (left < right) {
            int gap = right - left - 1;  // 사이의 개발자 수
            int minAbility = Math.min(arr[left], arr[right]);
            int power = gap * minAbility;
            
            maxPower = Math.max(maxPower, power);
            
            if (arr[left] < arr[right]) {
                left++;
            } else {
                right--;
            }
        }
        
        System.out.println(maxPower);
    }
}