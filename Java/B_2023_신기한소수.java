import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_2023_신기한소수 {
	
	static int N;
	static int[] list;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		dfs(0, 0);
		if(sb.length() != 0) {
			sb.setLength(sb.length() - 1);
		}
		System.out.println(sb);
	}
	
	static void dfs(int index, int num) {
		if(index == N) {
			sb.append(num / 10 + "\n"); // 계속 10이 곱해진 상태로 와서 10나눔
			return ;
		}
		
		for(int i = 1; i < 10; i += 1) {
			num += i;
			if(isPrime(num)) { // 맞으면 다음수 검사
				dfs(index + 1, num * 10);
			}
			num -= i;
		}
	}
	
	static boolean isPrime(int num) {
		if(num == 1) { // 1이면 소수 아님
			return false;
		}
		if(num % 2 == 0) { // 짝수는 소수 아니고 2만 소수
			return num == 2 ? true : false;
		}
		for(int i = 3; i * i <= num; i += 2) { // 그 외는 제곱수까지만 나눠서 판별
			if(num % i == 0) {
				return false;
			}
		}
		return true;
	}
}
