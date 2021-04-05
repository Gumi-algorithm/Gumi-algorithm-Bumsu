import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_2141_우체국 {

	static class Position implements Comparable<Position>{
		int num, val;

		public Position(int num, int val) {
			this.num = num;
			this.val = val;
		}

		@Override
		public int compareTo(Position o) {
			return Integer.compare(this.num, o.num);
		}
	}
	
	static int N;
	static Position[] X;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		X = new Position[N];
		for(int i = 0; i < N; i++) { // 값 저장
			st = new StringTokenizer(br.readLine(), " ");
			int num = Integer.parseInt(st.nextToken());
			int val = Integer.parseInt(st.nextToken());
			X[i] = new Position(num, val);
		}
		Arrays.sort(X); // 정렬
		long[] sum = new long[N];
		sum[0] = X[0].val;
		for(int i = 1; i < N; i++) { // 누적합
			sum[i] = sum[i - 1] + X[i].val;
		}
		long mid = 0;
		if(sum[N - 1] % 2 == 0) { // 짝수면
			mid = sum[N - 1] / 2; //반이 딱 나눠짐
		}else { // 홀수면
			mid = (sum[N - 1] / 2) + 1; // 반으로 나눈 몫 + 1까지 -> 홀수인 경우는 큰수쪽에 지어야됨
		}
		int start = 0, end = N - 1, idx = 0;
		while(start <= end) { // 이분탐색
			int m = (start + end) / 2;
			if(sum[m] >= mid) {
				end = m - 1;
				idx = X[m].num;
			}else {
				start = m + 1;
			}
		}
		System.out.println(idx);
	}
}
