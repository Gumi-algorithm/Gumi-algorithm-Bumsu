import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_7490_0만들기 {

	static int N;
	static int[] arr;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N];
			for (int i = 0; i < N; i++) {
				arr[i] = i + 1;
			}
			dfs(1, 0, 1, "1", 1);
			sb.append("\n");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
	// 차례대로 횟수, 이전이 (+, -) 검사용, 계산한 값, 표현식, 이 전의 계산 한 값
	static void dfs(int index, int pre, int result, String expr, int val) { 
		if (index == N) {
			if (result == 0) { // 합이 0 인 경우
				sb.append(expr + "\n");
			}
			return;
		}
		// 빈 공간으로 숫자를 이어 붙힌 경우의 값
		int num = val * 10 + arr[index];
		// 이전 값의 한 번은 더해져있으니 9번 더하고 새로운 값을 1번 더해야 됨
		val *= 9; 
		val += arr[index];
		// 빈 공간의 경우
		if (pre == 0) { // 빈 공간인데 맨 앞이 + 인 경우 
			dfs(index + 1, 0, result + val, expr + " " + arr[index], num);
		} else {// 빈 공간인데 맨 앞이 - 인 경우 
			dfs(index + 1, 1, result - val, expr + " " + arr[index], num);
		}
		dfs(index + 1, 0, result + arr[index], expr + "+" + arr[index], arr[index]); // 더하기
		dfs(index + 1, 1, result - arr[index], expr + "-" + arr[index], arr[index]); // 빼기
	}
}
