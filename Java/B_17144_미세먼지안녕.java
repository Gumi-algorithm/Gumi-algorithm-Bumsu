import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class  Position{
	int y, x;

	public Position(int y, int x) {
		this.y = y;
		this.x = x;
	}
	
}

public class B_17144_미세먼지안녕 { // 너무 개 억지로 품(아마 좋은 방법이 있을 꺼임..)
	
	static int R, C, T;
	static int[] dy = {-1, 0, 1, 0}, dx = {0, 1, 0, -1};
	static Position[] clean = new Position[2];
	static int[][] arr, pattern;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		arr = new int[R][C];
		pattern = new int[2][];
		
		int index = 0;
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < C; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == -1) {
					clean[index] = new Position(i, j);
					pattern[index] = new int[] {i , C - 1, i, C - 1};
					index += 1;
				}
			}
		}
		pattern[1] = new int[] {R - 2 - pattern[0][0] , C - 1, R -2 - pattern[0][2] , C - 1};
		for(int i = 0; i < 2; i++) {
			pattern[i][0] -= 1;
			pattern[i][3] -= 1;
		}
		for(int i = 0; i < T; i++) {
			arr = spread();
			move();
		}
		int res = 2;
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				res += arr[i][j];
			}
		}
		System.out.println(res);
	}
	
	static int[][] spread() {
		int[][] board = new int[R][C];
		Queue<Position> queue = new LinkedList<>();
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				board[i][j] = arr[i][j];
				if(arr[i][j] != -1 && arr[i][j] != 0) {
					queue.offer(new Position(i, j));
				}
			}
		}
		int size = queue.size();
		for(int i = 0; i < size; i++) {
			Position cur = queue.poll();
			int cnt = arr[cur.y][cur.x] / 5;
			for(int j = 0; j < 4; j++) {
				int ny = cur.y + dy[j];
				int nx = cur.x + dx[j];
				if(0 <= ny && ny < R && 0 <= nx && nx < C) {
					if(arr[ny][nx] != -1) {
						board[cur.y][cur.x] -= cnt;
						board[ny][nx] += cnt;
					}
				}
			}
		}
		return board;
	}
	static void move() {
		// 위	: 북 동 남 서
		// 아래	: 남 동 북 서
		int[] list = new int[] {0, 1, 2, 3};
		for(int k = 0; k < 2; k++) {
			if(k == 1) {
				list = new int[] {2, 1, 0, 3};
			}
			Position cur = clean[k];
			int ny = cur.y + dy[list[0]], nx = cur.x + dx[list[0]];
			arr[ny][nx] = 0;
			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < pattern[k][i]; j++) {
					ny += dy[list[i]];
					nx += dx[list[i]];
					arr[ny - dy[list[i]]][nx - dx[list[i]]] = arr[ny][nx]; 
				}
			}
			arr[ny][nx] = 0;
		}
			
	}
}
