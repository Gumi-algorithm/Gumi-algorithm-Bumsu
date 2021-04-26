import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B_2075_N번째큰수 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int num = Integer.parseInt(st.nextToken());
				if (i == 0) { // 처음에 숫자 N개를 넣음
					pq.offer(num);
				} else {
					if (pq.peek() < num) { // pq의 최솟값보다 큰 경우
						pq.offer(num); // 값을 추가
						pq.poll(); // 최솟값 제거 
					}
				}
			}
		}
		System.out.println(pq.peek()); // N번째로 고정이므로 가장 작은 수 출력
	}
}