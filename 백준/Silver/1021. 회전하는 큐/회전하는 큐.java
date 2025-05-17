import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();  // 큐의 크기
        int m = sc.nextInt();  // 뽑아낼 숫자의 개수

        LinkedList<Integer> deque = new LinkedList<>();
        for (int i = 1; i <= n; i++) {
            deque.offer(i); // 1부터 n까지 초기화
        }

        int[] targets = new int[m];
        for (int i = 0; i < m; i++) {
            targets[i] = sc.nextInt();
        }

        int totalOperations = 0;

        for (int target : targets) {
            int index = deque.indexOf(target);
            int half = deque.size() / 2;

            // 회전 방향 결정
            if (index <= half) {
                // 왼쪽으로 회전
                for (int i = 0; i < index; i++) {
                    deque.offerLast(deque.pollFirst());
                    totalOperations++;
                }
            } else {
                // 오른쪽으로 회전
                for (int i = 0; i < deque.size() - index; i++) {
                    deque.offerFirst(deque.pollLast());
                    totalOperations++;
                }
            }

            // 맨 앞 요소 제거
            deque.pollFirst();
        }

        System.out.println(totalOperations);
    }
}