import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int k = Integer.parseInt(br.readLine());

            PriorityQueue<Integer> minHeap = new PriorityQueue<>();
            PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
            Map<Integer, Integer> countMap = new HashMap<>();

            for (int i = 0; i < k; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                char cmd = st.nextToken().charAt(0);
                int num = Integer.parseInt(st.nextToken());

                if (cmd == 'I') {
                    minHeap.offer(num);
                    maxHeap.offer(num);
                    countMap.put(num, countMap.getOrDefault(num, 0) + 1);
                } else {
                    if (num == 1) {
                        removeValid(maxHeap, countMap);
                    } else {
                        removeValid(minHeap, countMap);
                    }
                }
            }

            cleanHeap(minHeap, countMap);
            cleanHeap(maxHeap, countMap);

            if (countMap.isEmpty()) {
                System.out.println("EMPTY");
            } else {
                int max = maxHeap.peek();
                int min = minHeap.peek();
                System.out.println(max + " " + min);
            }
        }
    }

    // 힙에서 유효하지 않은 값 제거
    private static void removeValid(PriorityQueue<Integer> heap, Map<Integer, Integer> countMap) {
        while (!heap.isEmpty()) {
            int val = heap.poll();
            if (countMap.containsKey(val)) {
                int cnt = countMap.get(val);
                if (cnt == 1) {
                    countMap.remove(val);
                } else {
                    countMap.put(val, cnt - 1);
                }
                break;
            }
        }
    }

    // 힙 위에 있는 유효하지 않은 값들 정리
    private static void cleanHeap(PriorityQueue<Integer> heap, Map<Integer, Integer> countMap) {
        while (!heap.isEmpty() && !countMap.containsKey(heap.peek())) {
            heap.poll();
        }
    }
}