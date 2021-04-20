import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_15791_세진이의미팅 {
	//페르마 소정리 사용
	static final long MOD = 1000000007;  
	static int N, M;
	static long[] fac = new long[3];
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st= new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		getFac(N);
		System.out.println((fac[2] * Pow((fac[0] * fac[1])% MOD, MOD - 2)) % MOD);
		
	}
	static long Pow(long x, long y) {
		if(y == 0) return 1;
		if(y == 1) return x;
		if(y % 2 == 0) {
			long tmp = Pow(x, y / 2) % MOD;
			return (tmp * tmp) % MOD;
		}else {
			long tmp = Pow(x, y - 1) % MOD;
			return (tmp * x) % MOD;
		}
	}
	
	static void getFac(int n) {
		long res = 1;
		fac[0] = fac[1] = fac[2] = 1;
		for(int i = 1; i < n + 1; i++) {
			res = (res * i) % MOD;
			if(i == N - M) fac[0] = res;
			if(i == M) fac[1] = res;
			if(i == N) fac[2] = res;
		}
	}
}

