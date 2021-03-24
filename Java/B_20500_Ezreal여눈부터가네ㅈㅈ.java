import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class B_20500_Ezreal여눈부터가네ㅈㅈ {

	static int N;
	static int[] num;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		BigInteger ans = new BigInteger("0");
		int sum = N + 4;
		for(int i = 1; i < N + 1; i++) { // 5가 1개일 때 부터
			if(sum % 3 == 0) {
				ans = ans.add(nCm(i - 1)); //맨 끝 5고정이기 때문에 1개 제외
				ans = ans.remainder(new BigInteger("1000000007")); // 1000000007로 나눈 값 
			}
			sum += 4;
		}
		System.out.println(ans);
	}
	
	static BigInteger nCm(int M) {
		BigInteger a = new BigInteger("1");
		BigInteger b = new BigInteger("1");
		for(int i = 0; i < M; i++) {
			a = a.multiply(new BigInteger(Integer.toString(N - 1 - i))); // 맨 끝은 5이기 때문에 N-1개
			b = b.multiply(new BigInteger(Integer.toString(M - i)));
		}
		return a.divide(b);
	}
}