import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class blank {
	int x, y;

	public blank(int y, int x) {
		this.y = y;
		this.x = x;
	}
}

class virus {
	int x, y;

	public virus(int y, int x) {
		this.y = y;
		this.x = x;
	}
}
public class B_14502_연구소 {

	static int N, M, area = -3, max = Integer.MIN_VALUE;
	static int[][] arr;
	static int[] list = new int[3];
	static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
	static ArrayList<blank> Blank = new ArrayList<>();
	static ArrayList<virus> Virus = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == 0) {
					area += 1;
					Blank.add(new blank(i, j));
				}else if(arr[i][j] == 2) {
					Virus.add(new virus(i, j));
				}
			}
		}
		dfs(0, 0);
		System.out.println(max);
	}
	
	static void dfs(int index, int start) {
		if(index == 3) {
			bfs();
			return;
		}
		for(int i = start; i < Blank.size(); i++) {
			arr[Blank.get(i).y][Blank.get(i).x] = 1;
			dfs(index + 1, i + 1);
			arr[Blank.get(i).y][Blank.get(i).x] = 0;
		}
	}
	
	static void bfs() {
		int cnt = area;
		int[][] board = new int[N][M];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				board[i][j] = arr[i][j];
			}
		}
		Queue<int[]> que = new LinkedList<>();
		for(int i = 0; i < Virus.size(); i++) {
			que.offer(new int[] {Virus.get(i).y, Virus.get(i).x});
		}
		while(!que.isEmpty()) {
			int[] cur = que.poll();
			for(int i = 0; i < 4; i++) {
				int ny = cur[0] + dy[i];
				int nx = cur[1] + dx[i];
				if(0 <= ny && ny < N && 0 <= nx && nx < M && board[ny][nx] == 0) {
					board[ny][nx] = 2;
					cnt -= 1;
					que.offer(new int[] {ny, nx});
				}
			}
		}
		max = Math.max(max, cnt);
	}
}
