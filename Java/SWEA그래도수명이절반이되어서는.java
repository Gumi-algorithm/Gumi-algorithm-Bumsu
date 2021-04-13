import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA그래도수명이절반이되어서는 {

	static int N, K;
	static int[] arr, S;

	public static void main(String[] args) throws IOException {
//		https://swexpertacademy.com/main/learn/course/lectureProblemViewer.do
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t < T + 1; t++) {
			st = new StringTokenizer(br.readLine(), " ");
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine(), " ");
			arr = new int[N];
			int end = 0;
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				end = Math.max(end, arr[i]);
			}
			st = new StringTokenizer(br.readLine(), " ");
			S = new int[K];
			for (int i = 0; i < K; i++) {
				S[i] = Integer.parseInt(st.nextToken());
			}
			int start = 0, res = end;
			while (start <= end) { // 이분탐색
				int mid = (start + end) / 2;
				int cnt = 0, idx = 0;
				boolean[] v = new boolean[K];
				for (int i = 0; i < N; i++) { // 정한 최대 높이수가 되는 것만 카운터
					if (arr[i] <= mid) {
						cnt += 1;
					} else {
						cnt = 0;
					}
					if (idx < K && cnt == S[idx]) { // S배열을 만족하면 다음 index로 넘어감
						cnt = 0;
						v[idx] = true;
						idx += 1;
					}
				}
				if (v[K - 1]) { // 배열의 맨끝이 성공하면
					res = Math.min(res, mid);
					end = mid - 1;
				} else { // 실패한 경우
					start = mid + 1;
				}

			}
			sb.append("#" + t + " " + res + "\n");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
}
