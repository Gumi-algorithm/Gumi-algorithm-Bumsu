import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class B_10026_적록색약 {

	static int N;
	static char[][] map;
	static int[] res = new int[2]; // 결과값 저장
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new char[N][N];
		for(int i = 0; i < N; i++) {
			String data = br.readLine();
			for(int j = 0; j < N; j++) {
				map[i][j] = data.charAt(j);
			}
		}
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j] != '1' && map[i][j] != '2') { // 적록색약 아닌 경우
					bfs(i, j);
					res[0] += 1;
				}
			}
		}
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j] == '1' || map[i][j] == '2') { // 적록색약인 경우
					bfs(i, j);
					res[1] += 1;
				}
			}
		}
		System.out.print(res[0] + " " + res[1]);
	}
	
	static void bfs(int y, int x) {
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[] {y, x});
		char color = map[y][x];
		char change;
		if(color == 'R' || color == 'G') { // R, G를 1로 변환
			change = '1';
		}else if(color == 'B') { // B를 2로 변환
			change = '2';
		}else { // 그 외는 3으로 변환
			change = '3'; 
		}
		map[y][x] = change;
		while(!que.isEmpty()) {
			int[] next = que.poll();
			for(int i = 0; i < 4; i++) {
				int ny = next[0] + dy[i];
				int nx = next[1] + dx[i];
				if( 0 <= ny && ny < N && 0 <= nx && nx < N) {
					if(map[ny][nx] == color) {
						map[ny][nx] = change;
						que.offer(new int[]{ny, nx});
					}
				}
			}
		}
	}
}