import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_20207_달력 {

	static int N;
	static int[] arr = new int[367];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int S = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			for (int j = S; j < E + 1; j++) {
				arr[j] += 1; // 시작 ~ 끝 모두 1증가
			}
		}
		int h = 0, len = 0, res = 0;
		for (int i = 1; i < 367; i++) {
			if (arr[i] == 0) { // 끝이 나오면
				res += len * h; // 직사각형 넓이 값 더해주고
				len = 0; // 길이와 높이 초기화
				h = 0;
			} else {
				len += 1; // 길이 1 증가
				h = Math.max(arr[i], h); // 가장 많이 겹치는 부분
			}
		}
		System.out.println(res);
	}
}
