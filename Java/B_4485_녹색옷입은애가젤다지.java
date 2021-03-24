import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B_4485_녹색옷입은애가젤다지 {
	
	static class Position implements Comparable<Position>{
		int y, x, w;

		public Position(int y, int x, int w) {
			this.y = y;
			this.x = x;
			this.w = w;
		}
		@Override
		public int compareTo(Position o) {
			return Integer.compare(this.w, o.w);
		}
	}
	static int N;
	static int[][] arr;
	static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int cnt = 0;
		while(true) {
			N = Integer.parseInt(br.readLine());
			if(N == 0) break;
			arr = new int[N][N];
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			sb.append("Problem " + (++cnt) + ": " + dijkstra(0, 0, arr[0][0]) + "\n");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
		
	}
	
	static int dijkstra(int y, int x, int w) {
		PriorityQueue<Position> pq = new PriorityQueue<>();
		pq.offer(new Position(y, x, w));
		int[][] val = new int[N][N];
		for(int i = 0; i < N; i++) {
			Arrays.fill(val[i], Integer.MAX_VALUE);
		}
		val[y][x] = w;
		while(!pq.isEmpty()) {
			Position now = pq.poll();
			
			if(now.y == N - 1 && now.w == N - 1) {
				break;
			}
			if(val[now.y][now.x] < now.w) continue;
			
			for(int i = 0; i < 4; i++) {
				int ny = now.y + dy[i];
				int nx = now.x + dx[i];
				if(0 <= ny && ny < N && 0 <= nx && nx < N) {
					int cost = arr[ny][nx] + now.w;
					if(val[ny][nx] > cost) {
						val[ny][nx] = cost;
						pq.offer(new Position(ny, nx, cost));
					}
				}
			}
		}
		return val[N - 1][N - 1];
	}
}
