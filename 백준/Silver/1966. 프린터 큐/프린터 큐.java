import java.io.*;
import java.util.*;

public class Main  {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int testCase = Integer.parseInt(st.nextToken());

        while (testCase-- > 0) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());  //문서의 개수
            int M = Integer.parseInt(st.nextToken());  //타겟 문서의 위치

            Queue<int[]> queue = new LinkedList<>();
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int priority = Integer.parseInt(st.nextToken());
                queue.add(new int[]{priority, i});  //우선순위 및 초기 인덱스
                priorityQueue.add(priority);
            }

            int printOrder = 0;  //출력순서 카운트

            while (!queue.isEmpty()) {
                int[] current = queue.poll();
                if (current[0] == priorityQueue.peek()) {  //현재문서가 가장 높은 중요도라면
                    priorityQueue.poll();
                    printOrder++;

                    if (current[1] == M) {  //찾고자 하는 문서면 결과 출력
                        bw.write(String.valueOf(printOrder));
                        bw.newLine();
                        break;
                    }
                } else {
                    queue.add(current);
                }
            }
        }
        bw.close();
        br.close();
    }
}
