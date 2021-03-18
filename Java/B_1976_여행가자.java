import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_1976_여행가자 {

	static int N, M;
	static int[] head, plan;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		
		head = new int[N + 1];
		for(int i = 1; i < N + 1; i++) {
			head[i] = i;
		}
		
		for(int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 1; j < N + 1; j++) {
				if(Integer.parseInt(st.nextToken()) == 1){
					union(i, j);
				}
			}
		}
		st = new StringTokenizer(br.readLine(), " ");
		plan = new int[M];
		
		for(int i = 0; i < M; i++) {
			plan[i] = Integer.parseInt(st.nextToken());
		}
		
		for(int i = 0; i < M - 1; i++) {
			if(find(plan[i]) != find(plan[i + 1])) {
				System.out.println("NO");
				break;
			}
			if(i == M - 2) {
				System.out.println("YES");
			}
		}
	}
	static int find(int x) {
		if(head[x] == x) return x;
		return head[x] = find(head[x]);
	}
	
	static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		if(a == b) return false;
		head[a] = head[b];
		return true;
	}
}
