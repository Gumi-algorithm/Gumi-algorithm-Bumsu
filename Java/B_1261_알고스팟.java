import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B_1261_알고스팟 {
	
	static class Position implements Comparable<Position>{
		int x, y, cnt;
		
		public Position(int y, int x, int cnt) {
			this.x = x;
			this.y = y;
			this.cnt = cnt;
		}
		@Override
		public int compareTo(Position o) {
			return Integer.compare(this.cnt, o.cnt);
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N][M];
		for(int i = 0; i < N; i++) {
			String data = br.readLine();
			for(int j = 0; j < M; j++) {
				arr[i][j] = data.charAt(j) - '0';
			}
		}
		int res = 0;
		boolean[][] v = new boolean[N][M];
		int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
		PriorityQueue<Position> pq = new PriorityQueue<>();
		pq.offer(new Position(0, 0, 0));
		v[0][0] = true;
		while(!pq.isEmpty()) {
			Position now = pq.poll();
			if(now.y == N - 1 && now.x == M - 1) {
				res = now.cnt;
				break;
			}
			for(int i = 0; i < 4; i++) {
				int ny = now.y + dy[i];
				int nx = now.x + dx[i];
				if(0 <= ny && ny < N && 0 <= nx && nx < M && !v[ny][nx]) {
					v[ny][nx] = true;
					if(arr[ny][nx] == 1) {
						pq.offer(new Position(ny, nx, now.cnt + 1));
					}else {
						pq.offer(new Position(ny, nx, now.cnt));
					}
				}
			}
		}
		System.out.println(res);
	}
}
