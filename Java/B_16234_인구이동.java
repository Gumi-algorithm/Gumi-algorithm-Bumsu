import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_16234_인구이동{
	static int N, L, R;
	static int[][] arr;
	static int[] dy = {-1, 1, 0, 0};
	static int[] dx = {0, 0, -1, 1};
	static boolean[][] check;
	static LinkedList<int[]> union = new LinkedList<>();
	
	public static void main(String[] args) throws IOException {		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		arr = new int[N][N];
		int count = 0;
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		while(true) {
			int possible = 0;
			check = new boolean[N][N];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(!check[i][j]) {
						possible += checkBorderline(i, j);
					}
				}
			}
			if(possible != 0) {
				count += 1;
			}else {
				break;
			}
		}
		System.out.println(count);
	}
	static void movePeople(int people) {
		while(!union.isEmpty()) {
			int[] cur = union.pop();
			arr[cur[0]][cur[1]] = people;
		}
	}
	
	static int checkBorderline(int y, int x) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] {y,x});
		int people = arr[y][x], count = 1, population = 0;
		union.add(new int[] {y, x});
		check[y][x] = true;
		while(!queue.isEmpty()) {
			int[] cur = queue.poll();
			y = cur[0];
			x = cur[1];
			for(int i = 0; i < 4; i++) {
				int ny = y + dy[i];
				int nx = x + dx[i];
				if(0 <= ny && ny < N && 0 <= nx && nx < N && !check[ny][nx]) {
					population = Math.abs(arr[y][x] - arr[ny][nx]);
					if(L <= population && population <= R) {
						check[ny][nx] = true;
						union.add(new int[] {ny, nx});
						people += arr[ny][nx];
						count += 1;
						queue.offer(new int[]{ny, nx});
					}
				}
			}
		}
		if(count == 1) {
			union.pop();
			return 0;
		}else {
			movePeople(people / count);
			return 1;
		}
	}

}
