import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class B_10830_행렬제곱 {

	static int N;
	static BigInteger B;
	static int[][] arr, basic, ans;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		B = new BigInteger(st.nextToken());
		
		basic = new int[N][N];
		arr = new int[N][N];
		ans = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				basic[i][j] = arr[i][j];
			}
			ans[i][i] = 1;
		}
		while(B.compareTo(new BigInteger("0")) > 0) {
			if(B.remainder(new BigInteger("2")).equals(new BigInteger("1"))) {
				getVal(ans, arr);
			}
			getVal(arr, arr);
			B = B.divide(new BigInteger("2"));
		}
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				sb.append(ans[i][j] + " ");
			}
			sb.append("\n");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
	
	static void getVal(int[][] a, int[][] b) {
		int[][] res = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				int val = 0;
				for(int k = 0; k < N; k++) {
					val += a[k][j] * b[i][k];
				}
				res[i][j] = val % 1000;
			}
		}
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				a[i][j] = res[i][j];
			}
		}
	}
}
