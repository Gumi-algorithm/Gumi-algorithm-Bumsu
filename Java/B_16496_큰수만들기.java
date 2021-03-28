import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_16496_큰수만들기 {
	
	static class Number implements Comparable<Number>{
		String val;

		public Number(String val) {
			this.val = val;
		}

		@Override
		public int compareTo(Number o) {
			int idx = 0;
			// 303, 30이 있으면 303303, 303030을 비교(같은 숫자를 여러번 붙힌 것을 비교)
			if(this.val.equals(o.val)) return 0; // 같으면 정렬 안함
			while(true) { // 자리 숫자 비교
				char A = this.val.charAt(idx % this.val.length());   
				char B = o.val.charAt(idx % o.val.length());
				idx += 1;
				if(A != B) { // 다른 경우
					return Integer.compare(B, A); // 내림차순으로 정렬
				}
			}
		}

		@Override
		public String toString() {
			return this.val + " ";
		}
	}
	
	static int N;
	static Number[] arr;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		arr = new Number[N];
		for(int i = 0; i < N; i++) {
			arr[i] = new Number(st.nextToken());
		}
		Arrays.sort(arr); // 정렬
		for(int i = 0; i < N; i++) { // 정렬된 값들을 붙힘
			sb.append(arr[i].val);
		}
		System.out.println(sb.charAt(0) == '0' ? 0 : sb); // 붙힌 문자열중 가장 맨 앞이 0이면 값은 0
	}
}
