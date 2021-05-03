import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_2877_4와7 {

	static int K;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		K = Integer.parseInt(br.readLine());
		int len = 0;
		for (int i = 1; i < 31; i++) { // 몇 번째 자리까지 있어야 하는지 검사
			if (K < (1 << i) - 1) { // 규칙이 2^n - 1보다 작은 경우 n - 1이 길이
				len = i - 1;
				break;
			}
		}
		int start = (1 << len) - 1; // 4로만 이루어진 숫자는 2^len - 1임
		int n = K - start; // 시작점과 K의 차만큼의 값을 구해주면 됨
		for (int i = len - 1; i >= 0; i--) { //길이만큼 검사  
			if ((n & (1 << i)) == 0) { // 비트가 0이면 4로 표시
				sb.append(4);
			} else { // 비트가 1이면 7로 표시
				sb.append(7);
			}
		}
		System.out.println(sb);
	}
}
