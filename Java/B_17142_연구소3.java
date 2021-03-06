import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class virus {
	int y, x;

	public virus(int y, int x) {
		this.y = y;
		this.x = x;
	}
}

public class B_17142_연구소3 {

	static int N, M, area, min = Integer.MAX_VALUE;
	static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
	static int[][] arr;
	static int[] list; //바이러스 위치
	static ArrayList<virus> Virus = new ArrayList<>();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		area = 0;
		arr = new int[N][N];
		list = new int[M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == 2) {
					Virus.add(new virus(i, j));
					arr[i][j] = -1;
				}else if(arr[i][j] == 0) {
					area += 1;
				}
			}
		}
		dfs(0, 0);
		if(min == Integer.MAX_VALUE) {
			min = -1;
		}
		System.out.println(min);
	}
	
	static void dfs(int index, int start) {
		if(index == M) {
			bfs();
			return;
		}
		for(int i = start; i < Virus.size(); i++) {
			list[index] = i;
			arr[Virus.get(i).y][Virus.get(i).x] = 2;
			dfs(index + 1, i + 1);
			arr[Virus.get(i).y][Virus.get(i).x] = -1;
		}
	}
	
	static void bfs() {
		int cnt = area;
		int time = 0;
		Queue<int[]> queue = new LinkedList<>();
		for(int i = 0; i < M; i++) {
			queue.offer(new int[] {Virus.get(list[i]).y, Virus.get(list[i]).x, 0});
		}
		int[][] board = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				board[i][j] = arr[i][j];
			}
		}
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			 for(int i = 0; i < 4; i++) {
				 int ny = cur[0] + dy[i];
				 int nx = cur[1] + dx[i];
				 if(0 <= ny && ny < N && 0 <= nx && nx < N) {
					 if(board[ny][nx] == 0) {
						 board[ny][nx] = 2;
						 cnt -= 1;
						 time = Math.max(cur[2] + 1, time);
						 queue.offer(new int[] {ny, nx, cur[2] + 1});
					 }else if(board[ny][nx] == -1 && cnt > 0) {
						 board[ny][nx] = 2;
						 time = Math.max(cur[2] + 1, time);
						 queue.offer(new int[] {ny, nx, cur[2] + 1});
					 }
				 }
			 }
		}
		
		if(cnt == 0) {
			min = Math.min(min, time);
		}
	}
}
