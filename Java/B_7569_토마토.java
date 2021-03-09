import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Position {
	int y, x, h, t;

	public Position(int y, int x, int h, int t) {
		this.y = y;
		this.x = x;
		this.h = h;
		this.t = t;
	}
}

public class B_7569_토마토 {

	static int M, N, H, cnt = 0, time = 0;
	static int[] dy, dx;
	static int[][] arr;
	static Queue<Position> queue = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		arr = new int[N * H][M];
		dy = new int[] {-1, 1, 0, 0, -N, N};
		dx = new int[] {0, 0, -1, 1, 0, 0};
		
		for(int i = 0; i < N * H; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == 0) { // 익지 않은 토마토
					cnt += 1;
				}else if(arr[i][j] == 1) { // 익은 토마토
					queue.offer(new Position(i, j, i / N, 0));
				}
			}
		}
		bfs();
		System.out.println(time);
	}
	
	static void bfs() {
		
		while(!queue.isEmpty()) {
			Position cur = queue.poll();
			for(int i = 0; i < 6; i++) {
				boolean flag = false;
				int ny = cur.y + dy[i];
				int nx = cur.x + dx[i];
				int h = cur.h;
				if(i < 4) { // 4방 탐색
					if(h * N <= ny && ny < (h + 1) * N && 0 <= nx && nx < M) {
						flag = true;
					}
				}else { // 층 수변환
					if(0 <= ny && ny < N * H && 0 <= nx && nx < M) { 
						flag = true;
					}
				}
				if(flag && arr[ny][nx] == 0) {
					flag = true;
					time = Math.max(time, cur.t + 1);
					queue.offer(new Position(ny, nx, ny / N, cur.t + 1));
					arr[ny][nx] = 1;
					cnt -= 1;
				}
			}
		}
		if(cnt != 0) {
			time = -1;
		}
	}
}