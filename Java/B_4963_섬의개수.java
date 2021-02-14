import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class location{
	int y;
	int x;
	public location(int y, int x) {
		this.y = y;
		this.x = x;
	}
}

public class B_4963_섬의개수 {
	
	static int h, w;
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dx = {0, -1, -1, -1, 0, 1, 1, 1};
	static int[][] map;
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		while(true) {
			st = new StringTokenizer(br.readLine(), " ");
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			if(w == 0 && h == 0) {
				break;
			}
			map = new int[h][w];
			for(int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				for(int j = 0; j < w; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int count = 0;
			for(int i = 0; i < h; i++) {
				for(int j = 0; j < w; j++) {
					if(map[i][j] == 1) {
						bfs(new location(i, j));
						count += 1;
					}
				}
			}
			sb.append(count + "\n");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
	static void bfs(location start) {
		Queue<location> queue = new LinkedList<>();
		queue.offer(start);
		map[start.y][start.x]= 0; 
		while(!queue.isEmpty()) {
			location next = queue.poll();
			for(int i = 0; i < 8; i++) {
				int ny = next.y + dy[i];
				int nx = next.x + dx[i]; 
				if(0 <= ny && ny < h && 0 <= nx && nx < w){
					if(map[ny][nx] == 1) {
						map[ny][nx] = 0;
						queue.offer(new location(ny, nx));
					}
				}
			}
		}
	}
}
