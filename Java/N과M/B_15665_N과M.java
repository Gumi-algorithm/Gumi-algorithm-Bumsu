import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_15665_N과M {
	
	static int N, M;
	static int[] number;
	static int[] result;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		number = new int[N];
		result = new int[M];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			number[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(number);
		dfs(0);
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
	static void dfs(int index) {
		if(index == M) {
			for(int i = 0; i < M; i++) {
				sb.append(result[i] + " ");
			}
			sb.setLength(sb.length() - 1);
			sb.append("\n");
			return ;
		}
		for(int i = 0; i < N; i++) {
			for(int k = index + 1; k < M; k++) {
				result[k] = 0;
			}
			if(result[index] !=number[i]) {
				result[index] = number[i];
				dfs(index + 1);
			}
		}
	}
}