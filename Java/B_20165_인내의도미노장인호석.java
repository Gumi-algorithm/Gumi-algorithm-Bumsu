import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_20165_인내의도미노장인호석 {

	static int N, M, R, score = 0;
	static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};//상하좌우
	static int[][] arr;
	static char[][] board;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		
		arr = new int[N][M];
		board = new char[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken()) - 1;
				board[i][j] = 'S';
			}
		}
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			int y = Integer.parseInt(st.nextToken()) - 1;
			int x = Integer.parseInt(st.nextToken()) - 1;
			char d = st.nextToken().charAt(0);
			fall(y, x, d);
			st = new StringTokenizer(br.readLine());
			y = Integer.parseInt(st.nextToken()) - 1;
			x = Integer.parseInt(st.nextToken()) - 1;
			alive(y, x);
		}
		sb.append(score + "\n");
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				sb.append(board[i][j] + " ");
			}
			sb.append("\n");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
	
	static void fall(int y, int x, char d) {
		int dir = 0;
		if(d == 'N') dir = 0;
		else if(d == 'S') dir = 1;
		else if(d == 'W') dir = 2;
		else if(d == 'E') dir = 3;
		if(board[y][x] == 'S') {
			int endy = y + (dy[dir] * arr[y][x]), endx = x + (dx[dir] * arr[y][x]);
			board[y][x] = 'F';
			score += 1;
			while(y != endy || x != endx) {
				y += dy[dir];
				x += dx[dir];
				if(0 <= y && y < N && 0 <= x && x < M && board[y][x] == 'S') {
					score += 1;
					board[y][x] = 'F';
					endy = y + (dy[dir] * Math.max(Math.abs(y - endy), Math.abs(arr[y][x])));
					endx = x + (dx[dir] * Math.max(Math.abs(x - endx), Math.abs(arr[y][x])));
				}
			}
		}
	}
	
	static void alive(int y, int x) {
		board[y][x] = 'S';
	}
}
