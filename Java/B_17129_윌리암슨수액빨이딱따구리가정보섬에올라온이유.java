import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_17129_윌리암슨수액빨이딱따구리가정보섬에올라온이유 {

	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] arr = new int[N][M];
		int[][] v = new int[N][M];
		Queue<int[]> que = new LinkedList<>();
		for(int i = 0; i < N; i++) {
			String data = br.readLine();
			for(int j = 0; j < M; j++) {
				arr[i][j] = data.charAt(j) - '0';
				if(arr[i][j] == 2) {
					v[i][j] = 1; // 시작 위치는 1로 표시
					que.offer(new int[] {i, j}); // 시작 위치 que저장
				}
			}
		}
		int y = -1, x = -1;
		int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
		while(!que.isEmpty()) {
			int[] cur = que.poll();
			if(arr[cur[0]][cur[1]] > 2) { // 음식나오면 break
				y = cur[0];
				x = cur[1];
				break;
			}
			for(int i = 0; i < 4; i++) {
				int ny = cur[0] + dy[i];
				int nx = cur[1] + dx[i];
				if(0 <= ny && ny < N && 0 <= nx && nx < M && v[ny][nx] == 0) {
					if(arr[ny][nx] != 1) { // 벽이 아니면
						v[ny][nx] = v[cur[0]][cur[1]] + 1; // 간 거리
						que.offer(new int[] {ny, nx}); 
					}
				}
			}
		}
		if(y == -1 && x == -1) {
			System.out.println("NIE");
		}else {
			System.out.println("TAK");
			System.out.println(v[y][x] - 1);
		}
	}
}
