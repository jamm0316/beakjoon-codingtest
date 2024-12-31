import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	// N - 선택지 카드수, M - 한계
	static int N, M;

	static int[] card;

	static int[] arr = new int[3];

	static int result = 0;

	static void combi(int cnt, int start) {

		if (cnt == 3) {
			int tmp = 0;
			for (int i = 0; i < 3; i++) {
				tmp += arr[i];
			}
			if (tmp <= M) {
				result = Math.max(result, tmp);
			}
			
			return;
		}
		
		for (int i = start; i < N; i++) {
			
			arr[cnt] = card[i];
			combi(cnt+1, i+1);
			
		}

	}

	// 조합 문제
	// 중복 X
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		// 입력 받기
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		card = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			card[i] = Integer.parseInt(st.nextToken());
		}

		// 함수 호출
		combi(0, 0);
		
		System.out.println(result);

	}

}