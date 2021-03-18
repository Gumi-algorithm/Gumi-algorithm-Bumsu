import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_2573_빙산 {

	static int N, M, time = 0;
	static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
	static int[][] arr;
	static Queue<int[]> ice = new LinkedList<>();
	
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
				if(arr[i][j] != 0) {
					ice.offer(new int[] {i, j}); // 얼음 위치
				}
			}
		}
		while(bfs()) {
			getIce();
			time += 1;
		}
		if(ice.size() == 0) { // 얼음이 0개면 다 같이 녹은 경우
			time = 0;
		}
		System.out.println(time);
	}
	static void getIce() {
		boolean[][] v = new boolean[N][M]; // 0이 되는 얼음 방지용
		int size = ice.size();
		for(int i = 0; i < size; i++) {
			int[] cur = ice.poll();
			int val = arr[cur[0]][cur[1]];
			v[cur[0]][cur[1]] = true;
			int cnt = 0;
			for(int j = 0; j < 4; j++) { // 4방향 검색
				int ny = cur[0] + dy[j];
				int nx = cur[1] + dx[j];
				if(0 <= ny && ny < N && 0 <= nx && nx < M && !v[ny][nx]) {
					if(arr[ny][nx] == 0) {
						cnt += 1; // 0 갯수 세기
					}
				}
			}
			arr[cur[0]][cur[1]] -= Math.min(val, cnt); // 0갯수보다 얼음양이 더 작을 수 있기 때문에
			if(arr[cur[0]][cur[1]] != 0) { // 0이 아닌 경우 ice 위치 추가
				ice.offer(new int[] {cur[0], cur[1]});
			}
		}
	}
	
	static boolean bfs() {
		if(ice.size() == 0) return false; // 얼음 0개면 false
		Queue<int[]> queue = new LinkedList<>();
		boolean[][] v = new boolean[N][M];
		int cnt = 0;
		queue.offer(ice.peek()); // 임의의 얼음
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			for(int i = 0; i < 4; i++) {
				int ny = cur[0] + dy[i];
				int nx = cur[1] + dx[i];
				if(0 <= ny && ny < N && 0 <= nx && nx < M && !v[ny][nx]) {
					if(arr[ny][nx] != 0) {
						v[ny][nx] = true;
						queue.offer(new int[] {ny, nx});
						cnt += 1;
					}
				}
			}
		} 
		if(ice.size() == cnt) { // bfs결과가 총 얼음 갯수가 같으면 한 묶음
			return true;
		}return false; // 아니면 쪼개진 거
	}
}
