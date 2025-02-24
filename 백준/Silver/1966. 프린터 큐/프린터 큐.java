import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCases = sc.nextInt();

        while (testCases-- > 0) {
            int n = sc.nextInt();  // 문서 개수
            int m = sc.nextInt();  // 타겟 문서의 위치

            Queue<int[]> queue = new LinkedList<>();
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());

            for (int i = 0; i < n; i++) {
                int priority = sc.nextInt();
                queue.add(new int[]{priority, i});  // {중요도, 초기 인덱스}
                priorityQueue.add(priority);
            }

            int printOrder = 0;  // 출력 순서 카운트

            while (!queue.isEmpty()) {
                int[] current = queue.poll();
                if (current[0] == priorityQueue.peek()) { // 현재 문서가 가장 높은 중요도라면
                    priorityQueue.poll();
                    printOrder++;

                    if (current[1] == m) { // 찾고자 하는 문서면 결과 출력
                        System.out.println(printOrder);
                        break;
                    }
                } else { // 중요도가 더 높은 문서가 있다면 뒤로 보냄
                    queue.add(current);
                }
            }
        }
        sc.close();
    }
}