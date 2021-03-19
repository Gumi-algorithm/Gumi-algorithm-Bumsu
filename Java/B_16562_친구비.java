import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_16562_친구비 {

	static int N, M, K;
	static int[] money, head;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		head = new int[N];
		for(int i = 0; i < N; i++) {
			head[i] = i;
		}
		money = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			money[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int a = Integer.parseInt(st.nextToken()) - 1;
			int b = Integer.parseInt(st.nextToken()) - 1;
			union(a, b);
		}
		int[] res = new int[N];
		boolean[] v = new boolean[N];
		for(int i = 0; i < N; i++) {
			int idx = find(i);
			if(v[idx]) {
				res[idx] = Math.min(res[idx], money[i]);
			}else {
				res[idx] = money[i];
				v[idx] = true;
			}
		}
		int sum = 0;
		for(int i = 0; i < N; i++) {
			sum += res[i];
		}
		if(K < sum) {
			System.out.println("Oh no");
		}else {
			System.out.println(sum);
		}
	}
	
	static int find(int x) {
		if(head[x] == x) return x;
		return head[x] = find(head[x]);
	}
	
	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		if(x == y) {
			return ;
		}
		head[x] = head[y];
	}
}
