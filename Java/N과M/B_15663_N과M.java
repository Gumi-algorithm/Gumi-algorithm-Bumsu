import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
//다시 볼 것
public class B_15663_N과M {
	
	static int N, M;
	static int[] number;
	static int[] result;
	static boolean[] check;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		number = new int[N];
		result = new int[M];
		check = new boolean[N];
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
			for(int j = index + 1; j < M; j++) { // 새로운 값을 넣을 때마다 넣는 자리 뒷자리를 전부 리셋
				result[j] = 0;
			}
			if(!check[i]) {
				if(number[i] != result[index]) {
					result[index] = number[i];
					check[i] = true;
					dfs(index + 1);
					check[i] = false;
				}
			}
		}
	}
	
}