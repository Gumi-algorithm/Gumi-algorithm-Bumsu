import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_9421_소수상근수 {

	static int N;
	static boolean[] prime;
	static int[] v;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		prime = new boolean[N + 1];
		v = new int[1000001];
		getPrime();
		for (int i = 2; i <= N; i++) {
			if (!prime[i]) {
				if (dfs(i)) {
					sb.append(i + "\n");
				}
			}
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);

	}

	static boolean dfs(int num) {

		if (num == 1) {
			return true;
		}

		if (v[num] == -1) {
			return false;
		} else if (v[num] == 1) {
			return true;
		} else {
			int after = 0, prev = num;
			v[num] = -1;
			while (prev > 0) {
				after += Math.pow(prev % 10, 2);
				prev /= 10;
			}
			boolean flag = dfs(after);
			if (flag) {
				v[num] = 1;
			}
			return flag;
		}
	}

	static void getPrime() {
		for (int i = 2; i * i <= N; i++) {
			for (int j = 2 * i; j <= N; j += i) {
				prime[j] = true;
			}
		}
	}
}
