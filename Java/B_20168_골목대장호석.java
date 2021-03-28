import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B_20168_골목대장호석기능성 {
	
	static class Position{
		int to, w;

		public Position(int to, int w) {
			this.to = to;
			this.w = w;
		}
		
	}
	static int N, M, A, B, C, res;
	static boolean[] v;
	static ArrayList<Position>[] list;
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		v = new boolean[N + 1];
		list = new ArrayList[N + 1];
		for(int i = 0; i < N + 1; i++) {
			list[i] = new ArrayList<>();
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			list[from].add(new Position(to, w));
			list[to].add(new Position(from, w));
		}
		v[A] = true;
		res = 1001;
		dfs(A, 0, 0);
		System.out.println(res == 1001 ? -1 : res);
	}
	static void dfs(int index, int min, int val) {
		if(index == B) {
			res = Math.min(res, min);
			return;
		}
		for(int i = 0; i < list[index].size(); i++) {
			Position next = list[index].get(i);
			if(!v[next.to] && C >= next.w + val) {
				v[next.to] = true;
				dfs(next.to, Math.max(next.w, min), next.w + val);
				v[next.to] = false;
			}
		}
	}
}
