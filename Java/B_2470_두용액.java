import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_2470_두용액 {

	static int N, min = Integer.MAX_VALUE;
	static int[] arr, result = new int[2];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(arr);
		for (int i = 0; i < N; i++) {
			binarySearch(i);
		}
		System.out.println(result[0] + " " + result[1]);
	}

	static void binarySearch(int idx) {
		int res = Integer.MAX_VALUE;
		int start = idx + 1, end = N - 1, index = 0;
		while (start <= end) {
			int mid = (start + end) / 2;
			int val = arr[idx] + arr[mid];
			if (val < 0) {
				if (res > Math.abs(val)) {
					index = mid;
					res = Math.abs(val);
				}
				start = mid + 1;
			} else if (val > 0) {
				if (res > Math.abs(val)) {
					index = mid;
					res = Math.abs(val);
				}
				end = mid - 1;
			} else {
				res = Math.abs(val);
				index = mid;
				break;
			}
		}
		if (res < min) {
			min = res;
			result[0] = arr[idx];
			result[1] = arr[index];
		}
	}
}
