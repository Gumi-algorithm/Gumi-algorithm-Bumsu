import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_15649_N과M {
	
	static int N, M;
	static int[] number;
	static boolean[] check;
	static StringBuilder sb;
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		number = new int[M];
		check = new boolean[N];
		dfs(0);
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
	
	static void dfs(int index) {
		
		if(index == M) {
			for(int i = 0; i < M; i++) {
				sb.append(number[i] + " ");
			}
			sb.setLength(sb.length() - 1);
			sb.append("\n");
			return;
		}
		for(int i = 1; i < N + 1; i++) {
			if(!check[i - 1]) {
				number[index] = i;
				check[i - 1] = true;
				dfs(index + 1);
				check[i - 1] = false;
			}
		}
	}
}