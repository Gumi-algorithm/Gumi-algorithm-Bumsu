import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_18116_로봇조립 {

	static int N;
	static int[] parent, count;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		N = Integer.parseInt(br.readLine());
		parent = new int[1000001];
		count = new int[1000001];
		for(int i = 0; i < 1000001; i++) {
			parent[i] = i;
			count[i] = 1;
		}
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			char cmd = st.nextToken().charAt(0);
			if(cmd == 'I') {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				union(a, b);
			}else {
				int c = Integer.parseInt(st.nextToken());
				sb.append(count[find(c)] + "\n");
			}
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
	static int find(int x) {
		if(parent[x] == x) return x;
		return parent[x] = find(parent[x]);
	}
	static void union(int x, int y) {
		x = find(x);
		y = find(y);
		if(x == y) return ;
		parent[x] = parent[y];
		count[y] += count[x];
	}
}
