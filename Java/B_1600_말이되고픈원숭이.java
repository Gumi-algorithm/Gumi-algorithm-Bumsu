import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_1600_말이되고픈원숭이 {

	static class Position{
		int y, x, k, cnt;

		public Position(int y, int x, int k, int cnt) {
			this.y = y;
			this.x = x;
			this.k = k;
			this.cnt = cnt;
		}
	}
	
	static int K, W, H;
	static int[][] horse = {{-2, -1}, {-2, 1}, {2, -1}, {2, 1},
							{-1, -2}, {1, -2}, {-1, 2}, {1, 2},
							{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
	static int[][] arr;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		K = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		arr = new int[H][W];
		int[][] v = new int[H][W];
		for(int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < W; j++){
				arr[i][j] = Integer.parseInt(st.nextToken());
				v[i][j] = -K;
			}
		}
		Queue<Position> que = new LinkedList<>();
		que.offer(new Position(0, 0, K, 0));
		int res = -1;
		while(!que.isEmpty()) {
			Position now = que.poll();
			if(now.y == H - 1 && now.x == W - 1) {
				res = now.cnt;
				break;
			}
			int start = 8;
			if(now.k > 0) start = 0;
			for(int i = start; i < 12; i++) {
				int ny = now.y + horse[i][0];
				int nx = now.x + horse[i][1];
				if(0 <= ny && ny < H && 0 <= nx && nx < W && arr[ny][nx] == 0) {
					if(i < 8 && v[ny][nx] < now.k - 1) { // 나이트 점프(이미 누가 지나간 자리면 안감)
						v[ny][nx] = now.k - 1;  
						que.offer(new Position(ny, nx, now.k - 1, now.cnt + 1));
					}else if(i >= 8 && v[ny][nx] < now.k) { // 그냥 점프, 이미 걸어서 누가갔으면 안감
						v[ny][nx] = now.k;
						que.offer(new Position(ny, nx, now.k, now.cnt + 1));
					}
				}
			}
		}
		System.out.println(res);
	}
}
