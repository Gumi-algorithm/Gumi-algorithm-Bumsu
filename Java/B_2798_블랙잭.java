import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_2798_블랙잭 {
	
	static int N, M, max = 0;
	static int[] number;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken()); 
		number = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			number[i] = Integer.parseInt(st.nextToken());
		}
		dfs(0, 0, 0);
		System.out.println(max);
	}
	static void dfs(int index,  int sum, int next) {
		if(index == 3) {
			if(sum <= M) {
				max = Math.max(max, sum);
			}
			return ;
		}
		for(int i = next; i < N; i++) {
			if(sum + number[i] > M) {
				continue;
			}else if(index == 2 && sum + number[i] == M) {
				max = M;
				return;
			}else {
				dfs(index + 1, sum + number[i], i + 1);
				
			}
		}
	}

}
