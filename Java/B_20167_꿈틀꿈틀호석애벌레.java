import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_20167_꿈틀꿈틀호석애벌레 {

	static int N, K, res;
	static int[] arr;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		arr = new int[N];
		for(int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		dfs(0, 0, 0);
		System.out.println(res);
	}
	
	static void dfs(int index, int sum, int ans) {
		if(index == N) {
			res = Math.max(ans, res);
			return;
		}
		
		dfs(index + 1, 0, ans); // 선택 안함
		if(arr[index] + sum >= K) { // 선택함
			dfs(index + 1, 0, ans + arr[index] + sum - K);
		}else {
			dfs(index + 1, arr[index] + sum, ans);
		}
	}
}