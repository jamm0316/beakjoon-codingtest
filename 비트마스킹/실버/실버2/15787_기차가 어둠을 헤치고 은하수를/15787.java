import java.util.*;
import java.io.*;

public class Main {
    static int trains;
    static int queries;
    static int[] seat;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        trains = Integer.parseInt(st.nextToken());
        queries = Integer.parseInt(st.nextToken());
        seat = new int[trains + 1]; // 기차 번호를 1부터 시작하므로 trains + 1

        for (int i = 0; i < queries; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());
            int ofTrain = Integer.parseInt(st.nextToken());
            
            if (command == 1 || command == 2) {
                int seatNumber = Integer.parseInt(st.nextToken()) - 1;
                if (command == 1) {
                    // 좌석에 사람 태우기
                    seat[ofTrain] |= (1 << seatNumber);
                } else if (command == 2) {
                    // 좌석에서 사람 내리기
                    seat[ofTrain] &= ~(1 << seatNumber);
                }
            } else if (command == 3) {
                // 승객들이 모두 한 칸씩 뒤로 가기
                seat[ofTrain] <<= 1;
                seat[ofTrain] &= ((1 << 20) - 1); // 21번째 좌석 제거
            } else if (command == 4) {
                // 승객들이 모두 한 칸씩 앞으로 가기
                seat[ofTrain] >>= 1;
            }
        }

        // 은하수를 건널 수 있는 기차의 수 계산
        Set<Integer> goTrains = new HashSet<>();
        for (int i = 1; i <= trains; i++) {
            goTrains.add(seat[i]); // 각 기차의 최종 좌석 상태를 Set에 추가
        }
        
        System.out.println(goTrains.size());
    }
}
