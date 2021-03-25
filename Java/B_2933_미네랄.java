import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_2933_미네랄 {
	static class Position{
		int y, x;
		public Position(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	static int R, C, N;
	static int[] bar, dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
	static boolean[][] v;
	static char[][] arr;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new char[R][C];
		
		for(int i = 0; i < R; i++) {
			String data = br.readLine();
			for(int j = 0; j < C; j++) {
				arr[i][j] = data.charAt(j);
			}
		}
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		for(int i = 0; i < N; i++) {
			throwBar(i, Integer.parseInt(st.nextToken()));  // 막대 던지기
			checkMineral(); // 미네랄 확인
		}
		print();
		
	}
	static void print() { // 출력
		sb = new StringBuilder();
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				sb.append(arr[i][j]);
			}
			sb.append("\n");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
	
	static void throwBar(int idx, int y) { // 막대 던지기
		int x = 0, dir = 3;
		y = R - y;
		if(idx % 2 == 1) { // idx가 홀수면 오른쪽에서 왼쪽으로 던지기
			x = C - 1;
			dir = 2;
		}
		while(0 <= x && x < C) {
			if(arr[y][x] == 'x') { // 맞으면 미네랄 삭제
				arr[y][x] = '.';
				break;
			}
			x += dx[dir]; 
		}
	}
	static int getLen(int[] h, boolean[][] c) { // 미네랄이 떨어져야할 거리 구하기
		int len = R;
		for(int i = 0; i < C; i++) {
			if(h[i] != -1) {
				h[i] = R - 1 - h[i];
				int temp = 0;
				for(int j = R - 1; j >= 0; j--) {
					if(!c[j][i]) {
						temp += 1;
						if(arr[j][i] == 'x') {
							h[i] -= temp;
							temp = 0;
						}
					}else {
						break;
					}
				}
				len = Math.min(len, h[i]);
			}
		}
		return len;
	}
	
	static void moveMineral(int y, int x) { // 미네랄 이동
		int[] height = new int[C];
		Arrays.fill(height, -1);
		Queue<Position> que = new LinkedList<>();	
		boolean[][] check = new boolean[R][C];
		que.offer(new Position(y, x));
		while(!que.isEmpty()) { // 분리된 미네랄들 bfs로 확인(check 배열에 true로 저장)
			Position now = que.poll();
			height[now.x] = Math.max(height[now.x], now.y);
			check[now.y][now.x] = true;
			for(int i = 0; i < 4; i++) {
				int ny = now.y + dy[i];
				int nx = now.x + dx[i];
				if(0 <= ny && ny < R && 0 <= nx && nx < C && !check[ny][nx]) {
					if(arr[ny][nx] == 'x') {
						check[ny][nx] = true;
						que.offer(new Position (ny, nx));
					}
				}
			}
		}
		int len = getLen(height, check); // 내려가야할 길이
		for(int i = 0; i < C; i++) { // x축
			for(int j = R - 1; j >= 0; j--) { // y 축
				if(check[j][i]) { // 떨어져야할 미네랄이면
					arr[j][i] = '.'; // 있던 자리 '.'으로 교체
					arr[j + len][i] = 'x'; // len만큼 내려서 미네랄로 표시
					v[j + len][i] = true; // 내려온 미네랄은 bfs로 탐색 불가능하게 함
				}
			}
		}
	}
	static boolean bfs(int y, int x) { // bfs로 떨어졌는지 안 떨어졌는지 검사
		boolean flag = true;
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] {y, x});
		while(!que.isEmpty()) {
			int[] cur = que.poll();
			if(cur[0] == R - 1) { // 맨 바닥과 안 붙어 있으면 떨어진 미네랄
				flag =  false;
			}
			for(int j = 0; j < 4; j++) {
				int ny = cur[0] + dy[j];
				int nx = cur[1] + dx[j];
				if(0<= ny && ny < R && 0 <= nx && nx < C && !v[ny][nx]) {
					if(arr[ny][nx] == 'x') {
						v[ny][nx] = true;
						que.offer(new int[] {ny, nx});
					}
				}
			}
		}
		return flag;
	}
	static void checkMineral() { // 떨어진 미네랄이 있는지 검사
		v = new boolean[R][C];
		for(int i = 0; i < R; i++) {
			for(int j = 0; j < C; j++) {
				if(arr[i][j] == 'x' && !v[i][j]) {
					if(bfs(i, j)) { // 동떨어진 미네랄이라면
						moveMineral(i, j); // 미네랄 이동
					}
				}
			}
		}
	}
}
