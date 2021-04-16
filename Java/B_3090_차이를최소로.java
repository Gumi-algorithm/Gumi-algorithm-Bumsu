import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_3090_차이를최소로 {

	static int N, T;
	static long[] arr, map;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		arr = new long[N];
		map = new long[N];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		
		int start = 0, end = 1000000000; // 이분탐색
		while (start < end) {
			int mid = (start + end) / 2;
			if (isCheck(mid)) {
				end = mid;
			} else {
				start = mid + 1;
			}
		}
		isCheck(end);
		for (int i = 0; i < N; i++) {
			sb.append(map[i] + " ");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}

	static boolean isCheck(long mid) {
		for (int i = 0; i < N; i++) {
			map[i] = arr[i];
		}
		long cnt = 0; // 이놈 때문에(횟수는 최대로 21억이 넘을 수도 있음)
		for (int i = 0; i < N - 1; i++) { // 양쪽검사
			if (map[i + 1] - map[i] > mid) {
				cnt += map[i + 1] - (map[i] + mid);
				map[i + 1] = map[i] + mid;
			}
		}
		for (int i = N - 1; i > 0; i--) {
			if (map[i - 1] - map[i] > mid) {
				cnt += map[i - 1] - (map[i] + mid);
				map[i - 1] = map[i] + mid;
			}
		}
		if (cnt <= T) return true;
		else return false;
	}
}
