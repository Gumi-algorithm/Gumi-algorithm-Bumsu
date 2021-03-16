import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Mirror{
	int size = 0;
	int[] v = new int[4];
}

public class B_3709_레이저빔은어디로 {

	static int n, r, d, y, x;
	static int[] start = new int[2];
	static int[] dy = {-1, 0, 1, 0}, dx = {0, 1, 0, -1}; //북 동 남 서
	static int[][] arr;
	static Mirror[] mirror;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++) {
			
			st = new StringTokenizer(br.readLine(), " ");
			n = Integer.parseInt(st.nextToken());
			r = Integer.parseInt(st.nextToken());
			arr = new int[n + 2][n + 2];
			mirror = new Mirror[r];
			for(int i = 0; i < r; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				arr[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = i + 2;
				mirror[i] = new Mirror();
			}
			st = new StringTokenizer(br.readLine(), " ");
			y = Integer.parseInt(st.nextToken());
			x = Integer.parseInt(st.nextToken());
//			0 ~ n + 1까지 존재
			if(x == 0 && y != n + 1) {
				d = 1;
			}else if(x == n + 1 && y != 0) {
				d = 3;
			}else if(x != 0 && y == n + 1) {
				d = 0;
			}else if(x != n + 1 && y == 0) {
				d = 2;
			}
			bfs();
			
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
	
	static void bfs() {
		
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {y, x});
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			int ny = cur[0] + dy[d];
			int nx = cur[1] + dx[d];
			if(0 < ny && ny < n + 1 && 0 < nx && nx < n + 1) {
				if(arr[ny][nx] == 0) {
					queue.offer(new int[] {ny, nx});
				}else if(arr[ny][nx] >= 2) {
					int index = arr[ny][nx] - 2;
					if(mirror[index].size != 0) { // 예외 처리
						for(int i = 0; i < mirror[index].size; i++) {
							if(mirror[index].v[i] == d) { // 같은게 있으면 사이클이라 도달 못함
								sb.append("0 0\n");
								return;
							}
						}
					} // 해당 거울에 이전에 왔던 방향으로 똑같이 진입하면 사이클을 그림
					mirror[index].v[mirror[index].size] = d; // 다른 방향인 경우 추가
					d = (d + 1) % 4; // 오른쪽 회전
					mirror[index].size += 1;
					queue.offer(new int[] {ny, nx});
				}
			}else {
				sb.append(ny + " " + nx + "\n");
				return ;
			}
		}
	}
}
