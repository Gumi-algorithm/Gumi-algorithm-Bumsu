import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class B_1655_가운데를말해요 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int N = Integer.parseInt(br.readLine());
		PriorityQueue<Integer> left = new PriorityQueue<>();
		PriorityQueue<Integer> right = new PriorityQueue<>();
		for (int i = 0; i < N; i++) {
			int num = Integer.parseInt(br.readLine());
			if (left.size() == 0) { // 왼쪽이 비었을 때
				left.offer(-num);
			} else if (right.size() == 0) { // 오른쪽이 비었을 때
				if (num < -left.peek()) { // 왼쪽보다 작은 경우
					right.offer(-left.poll()); // 왼쪽껄 빼서 오른쪽에 넣기
					left.offer(-num); // 왼쪽에서 입력 숫자 넣기
				} else {
					right.offer(num); // 왼쪽보다 큰 경우 오른쪽에 넣기
				}
			} else {
				if (num > right.peek()) { // 오른쪽의 최솟값보다 큰 경우
					right.offer(num); // 오른쪽에 넣기
					if (i % 2 == 0) { // 짝수인 경우엔 하나를 왼쪽으로 이동
						left.offer(-right.poll());
					}
				} else { // 오른쪽 최솟값보다 작은 경우
					left.offer(-num); //왼쪽에서 값 넣기
					if (i % 2 == 1) { // 홀수 번째 인 경우 왼쪽을 하나 빼서 오른쪽으로 이동
						right.offer(-left.poll());
					}
				} // 홀짝수는 좌우 갯수를 맞추기 위한 것
			}
			sb.append(-left.peek() + "\n"); // 맨 왼쪽의 최댓값이 중앙값임
			// 왼쪽에 -로 넣으면 최대힙이 됨
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
}
