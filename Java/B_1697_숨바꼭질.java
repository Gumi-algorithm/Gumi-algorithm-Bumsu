import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_1697_숨바꼭질 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		if(N >= K) {
			System.out.println(N - K); //같거나 작으면 뒤로가는건 한경우라 빼면 답
		}
		else {
			Queue<Integer> queue = new LinkedList<>(); // bfs풀기 위한 queue
			queue.offer(N);
			int[] map = new int[K + 2];
			map[N] = 1; 
			// 안하면 0 3 같은 경우 2배를 곱해도 값이 같으니 변화없음
			int max = map.length;
			while(!queue.isEmpty()) {
				N = queue.poll(); // queue값 pop
				if(N - 1 > -1 && map[N - 1] == 0) { //걸어서 - 1
					map[N - 1] = map[N] + 1;
					queue.offer(N - 1);
				}
				if(N * 2 < max && map[2 * N] == 0) { // 순간이동
					map[2 * N] = map[N] + 1;
					queue.offer(2 * N);
				}
				if(N + 1 < max && map[N + 1] == 0) { // 걸어서 + 1
					map[N + 1] = map[N] + 1;
					queue.offer(N + 1);
				}
				if(map[K] != 0) {
					System.out.println(map[K] - 1);
					break;
				}
			}
		}
	}
}
