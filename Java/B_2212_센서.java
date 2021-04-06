import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B_2212_센서 {

	static int N, K;
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		K = Integer.parseInt(br.readLine());
		arr = new int[N];
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr); // 정렬
		for(int i = 1; i < N; i++) {
			pq.offer(-(arr[i] - arr[i - 1])); // 두 좌표사이 거리
		}
		int res = 0;
		while(!pq.isEmpty()) {
			if(K > 1) { // K - 1번 가장 큰 값은 제외(거리가 먼 것은 그 자리에 센서 설치)
				pq.poll();
			}else { // 나머지 더함
				res += -pq.poll();
			}
			K--;
		}
		System.out.println(res);
	}
}
