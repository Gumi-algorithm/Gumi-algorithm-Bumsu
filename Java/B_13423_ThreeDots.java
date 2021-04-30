import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_13423_ThreeDots {

	static int N;
	static int[] arr;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N];
			st = new StringTokenizer(br.readLine(), " ");
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(arr);
			int cnt = 0;
			for (int i = 0; i < N - 2; i++) { // 시작점
				for (int j = i + 2; j < N; j++) { // 끝점
					if ((arr[i] + arr[j]) % 2 == 0) { // 두개의 평균이 짝수인 경우
						int mid = (arr[i] + arr[j]) / 2;
						if (BinarySearch(i, j, mid)) { // 해당 값을 찾으러 감
							cnt += 1;
						}
					} else { // 홀수면 자연수로 중간값이 없음
						continue;
					}
				}
			}
			sb.append(cnt + "\n");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}

	static boolean BinarySearch(int start, int end, int val) {
		while (start <= end) {
			int mid = (start + end) / 2;
			if (val == arr[mid]) { // 일치하는 값이 있으면
				return true; 
			} else if (val < arr[mid]) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		return false; // 해당 val이 없는 경우
	}
}
