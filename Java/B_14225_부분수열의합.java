import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_14225_부분수열의합 {

	static int N;
	static int[] arr;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		arr = new int[N];
		int total = 0;
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			total += arr[i];
		}
		boolean[] flag = new boolean[total + 2];
		for (int i = 1; i < (1 << N); i++) { // 될 수 있는 모든 부분 수열
			int val = 0;
			for (int j = 0; j < N; j++) {
				if ((i & (1 << j)) != 0) { // 1이면 
					val += arr[j]; // 값을 더함
				}
			}
			flag[val] = true; // 될 수 있는 부분수열의 값은 true
		}
		int res = 0;
		for (int i = 1; i < total + 2; i++) { // 1부터 검사해서 false이면 값 저장
			if (!flag[i]) { 
				res = i;
				break;
			}
		}
		System.out.println(res);
	}
}
