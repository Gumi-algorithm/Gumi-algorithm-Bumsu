import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_17406_배열돌리기4 {

	static int N, M, R, min = 10000;
	static int[][] cmd, board;
	static int[] dy = {0, 1, 0, -1};
	static int[] dx = {1, 0, -1, 0};
	static int[] result;
	static boolean[] check;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		board = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		cmd = new int[R][3];
		result = new int[R];
		check = new boolean[R];
		for(int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < 3; j++) {
				cmd[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0);
		System.out.println(min);
	}
	static void dfs(int index) {
		if(index == R) {
			int[][] arr = new int[N][M];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					arr[i][j] = board[i][j];
				}
			}
			for(int i = 0; i < R; i++) {
				int j = result[i];
				moveArr(cmd[j][0] -1, cmd[j][1] - 1, cmd[j][2], arr);
			}
			getResult(arr);
			return;
		}
		for(int i = 0; i < R; i++) {
			if(!check[i]) {
				check[i] = true;
				result[index] = i;
				dfs(index + 1);
				check[i] = false;
			}
		}
	}
	static void moveArr(int r, int c, int s, int[][] arr) {
		while(s >= 0){
			int y = r - s, x = c - s;
			int trade = arr[y][x];
			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < 2 * s; j++) {
					y += dy[i];
					x += dx[i];
					int temp = arr[y][x];
					arr[y][x] = trade;
					trade = temp;
				}
			}
			s -= 1;
		}
	}
	static void getResult(int[][] arr) {
		for(int i = 0; i < N; i++) {
			int sum = 0;
			for(int j = 0; j < M; j++) {
				sum += arr[i][j];
			}
			min = Math.min(min, sum);
		}
	}
}