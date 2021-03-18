import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class B_2407_조합 {

	static int N, M;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		BigInteger a = new BigInteger("1");
		BigInteger b = new BigInteger("1");
		for(int i = 0; i < M; i++) {
			a = a.multiply(new BigInteger(Integer.toString(N - i)));
			b = b.multiply(new BigInteger(Integer.toString(M - i)));
		}
		System.out.println(a.divide(b));
	}
}