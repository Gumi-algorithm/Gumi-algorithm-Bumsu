import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_2262_토너먼트만들기 {

	static int N;
	static int[] arr;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		arr = new int[N + 2];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 1; i < N + 1; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int max = N, res = 0;
		for (int i = 0; i < N - 1; i++) {
			int index = findMax(max);
			int right = index + 1;
			int left = index - 1;
			while (right < N + 1 && arr[right] == -1) {
				right++;
			}
			while (left > 0 && arr[left] == -1) {
				left--;
			}
			arr[index] = -1;
			if (arr[right] > arr[left]) {
				res += max - arr[right];
			} else {
				res += max - arr[left];
			}
			max -= 1;

		}
		System.out.println(res);
	}

	static int findMax(int max) {
		for (int i = 1; i < N + 1; i++) {
			if (arr[i] == max) {
				return i;
			}
		}
		return -1;
	}
}
