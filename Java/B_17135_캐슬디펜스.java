import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B_17135_캐슬디펜스 {

	static int N, M, D, cnt = 0, max = 0;
	static int[][] map;
	static int[][] copyArr;
	static int[] archer = new int[3];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		map = new int[N + 1][M];
		copyArr = new int[N + 1][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(0, 0);
		System.out.println(max);
	}
	static void dfs(int index, int start) {
		if(index == 3) {
			cnt = 0;
			copy();
			for(int i = 0; i < N; i++) {
				attack();
				move();
			}
			max = Math.max(cnt, max);
			return;
		}
		for(int i = start; i < M; i++) {
			archer[index] = i;
			dfs(index + 1, i + 1);
		}
	}
	
	static void attack() {
		ArrayList<int[]> remove = new ArrayList<>();
		for(int i = 0; i < 3; i++) {
			int y = N, x = 0, min = Integer.MAX_VALUE;
			int ax = archer[i];
			for(int j = 0; j < M; j++) {
				for(int k = N - 1; k >= 0; k--) {
					if(copyArr[k][j] == 1) {
						int d = N - k + Math.abs(ax - j);
						if(d <= D) {
							if(min > d) {
								y = k;
								x = j;
								min = d;
							}
						}
					}
				}
			}
			if(min != Integer.MAX_VALUE) {
				remove.add(new int[] {y, x});
			}
		}
		for(int i = 0; i < remove.size(); i++) {
			int ny = remove.get(i)[0];
			int nx = remove.get(i)[1];
			if(copyArr[ny][nx] == 1) {
				cnt += 1;
				copyArr[ny][nx] = 0;
			}
		}
	}
	static void copy() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				copyArr[i][j] = map[i][j];
			}
		}
	}
	static void move() {
		for(int j = 0; j < M; j++) {
			copyArr[N - 1][j] = 0;
		}
		for(int i = N - 2; i >= 0; i--) {
			for(int j = 0; j < M; j++) {
				copyArr[i + 1][j] = copyArr[i][j];
			}
		}
		for(int j = 0; j < M; j++) {
			copyArr[0][j] = 0;
		}
	}
}
