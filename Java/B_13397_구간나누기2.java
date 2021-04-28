import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_13397_구간나누기2 {

	static int N, M;
	static int[] arr;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine(), " ");
		arr = new int[N];
		int start = 0, end = 0;
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			end = Math.max(end, arr[i]);
		}
		int res = 0;
		while (start <= end) { // 이분탐색
			int mid = (start + end) / 2;
			if (isTrue(mid)) { // 가능하면 숫자를 줄여봄
				res = mid; 
				end = mid - 1;
			} else { // 불가능하면 숫자를 늘림
				start = mid + 1; 
			}
		}
		System.out.println(res);
	}

	static boolean isTrue(int mid) {
		int cnt = 1, min = Integer.MAX_VALUE, max = 0;
		for (int i = 0; i < N; i++) {
			min = Math.min(min, arr[i]); // 구간 최솟값
			max = Math.max(max, arr[i]); // 구간 최댓값
			if (max - min > mid) { // 주어진 간격을 넘으면
				cnt += 1; // 구간  1추가
				min = arr[i]; // min 값을 arr[i]로 설정
				max = arr[i]; // max 값을 arr[i]로 설정
			}
		}
		return cnt <= M; // 구간이 M개 이하면 true 아니면 false
	}
}
