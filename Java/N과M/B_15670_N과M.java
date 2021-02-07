import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_15670_N과M {

	static int N, M;
	static int[] number;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		number = new int[M];
		dfs(0, 1);
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
		
	}
	static void dfs(int index, int j) {
		if(index == M) {
			for(int i = 0; i < M; i++) {
				sb.append(number[i] + " ");
			}
			sb.setLength(sb.length() - 1);
			sb.append("\n");
			return ;
		}
		for(int i = j; i < N + 1; i++) {
			number[index] = i;
			dfs(index + 1, i + 1);
		}
	
	}
}