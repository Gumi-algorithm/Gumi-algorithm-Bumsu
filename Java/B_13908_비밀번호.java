import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_13908_비밀번호 {

	static boolean flag;
	static int n, m;
	static int[] arr = new int[10];
	static int[] v = new int[10];

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine(), " ");
		arr = new int[10];
		for (int i = 0; i < m; i++) {
			arr[Integer.parseInt(st.nextToken())] += 1;
		}
		int cnt = 0;
		for (int i = 0; i < Math.pow(10, n); i++) {
			if (check(i)) {
				cnt += 1;
			}
		}
		System.out.println(cnt);
	}

	static boolean check(int num) {
		flag = true;
		int k = 0;
		while (num > 0) {
			v[num % 10] += 1;
			num /= 10;
			k += 1;
		}
		if (k < n)
			v[0] += n - k;
		for (int i = 0; i < 10; i++) {
			if (arr[i] > v[i]) {
				flag = false;
			}
			v[i] = 0;
		}
		return flag;

	}
}
