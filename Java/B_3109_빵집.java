import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_3109_빵집 {

	static int R, C, cnt = 0;
	static boolean p;
	static char[][] map;
	static boolean[][] v;
	static int[] dy = {-1, 0, 1};
	static int[] dx = {1, 1, 1};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		v = new boolean[R][C];
		
		for(int i = 0; i < R; i++) {
			String data = br.readLine();
			for(int j = 0; j < C; j++) {
				map[i][j] = data.charAt(j);
			}
		}
		for(int i = 0; i < R; i++) {
			p = false;
			dfs(i, 0);
		}
		System.out.println(cnt);
	}
	
	static void dfs(int y, int x) {
		if(x == C - 1) {
			cnt += 1;
			p = true;
			return;
		}
		for(int i = 0; i < 3; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if(p) {
				return ;
			}else {
				if(0 <= ny && ny < R && 0 <= nx && nx < C && !v[ny][nx]) {
					if(map[ny][nx] == '.') {
						v[ny][nx] = true;
						dfs(ny, nx);
					}
				}
			}
		}
	}
}