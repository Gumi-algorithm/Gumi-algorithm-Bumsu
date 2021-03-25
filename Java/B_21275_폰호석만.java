import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_21275_폰호석만 {

	static int minA = 2, minB = 2;
	static String X, A, B;
	static long[] XA = new long[37], XB = new long[37];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		A = st.nextToken();
		B = st.nextToken();
		for(int i = 0; i < A.length(); i++) {
			if(A.charAt(i) < 96) {
				minA = Math.max(minA, (A.charAt(i) - '0') + 1);
			}else {
				minA = Math.max(minA, (A.charAt(i) - 87) + 1);
			}
		}
		for(int i = 0; i < B.length(); i++) {
			if(B.charAt(i) < 96) {
				minB = Math.max(minB, (B.charAt(i) - '0') + 1);
			}else {
				minB = Math.max(minB, (B.charAt(i) - 87) + 1);
			}
		}
//		'a' = 97  '1' = 49
		getVal();
		int cnt = 0;
		long x = 0;
		int a = 0 ,b = 0;
		for(int i = minA; i < 37; i++) {
			for(int j = minB; j < 37; j++) {
				if(XA[i] == XB[j] && i != j) {
					cnt += 1;
					a = i;
					b = j;
					x = XA[i];
				}
			}
		}
		if(cnt > 1) {
			System.out.println("Multiple");
		}else if(cnt == 0) {
			System.out.println("Impossible");
		}else {
			System.out.println(x + " " + a + " " + b);
		}
	}
	
	static void getVal() {
		for(int i = minA; i < 37; i++) {
			int x = 1;
			for(int j = A.length() - 1; j >= 0; j--) {
				if(A.charAt(j) < 96) {
					XA[i] += (A.charAt(j) - '0') * x;
				}else {
					XA[i] += (A.charAt(j) - 87) * x;
				}
				x *= i;
			}
		}
		for(int i = minB; i < 37; i++) {
			int x = 1;
			for(int j = B.length() - 1; j >= 0; j--) {
				if(B.charAt(j) < 96) {
					XB[i] += (B.charAt(j) - '0') * x;
				}else {
					XB[i] += (B.charAt(j) - 87) * x;
				}
				x *= i;
			}
		}
	}
}