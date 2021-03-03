import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_16236_아기상어 {

	static int N, size, time, cnt, cy, cx;
	static int[][] arr;
	static int[] dy = {-1, 0, 1, 0}, dx = {0, -1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		size = 2;
		time = 0;
		cnt = 0;
		arr = new int[N][N];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < N; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if(arr[i][j] == 9) {
					cy = i;
					cx = j;
				}
			}
		}
		
		while(isFish()) { // 먹이가 있을 때까지
			cnt += 1; // 한번 먹을 때마다 횟수 증가
			if(cnt == size) { // 크기와 횟수가 같으면 크기 증가
				cnt = 0;
				size += 1;
			}
		}
		System.out.println(time);
	}
	
	static boolean isFish() {
		boolean[][] check = new boolean[N][N];
		Queue<int[]> que = new LinkedList<>();
		que.offer(new int[]{cy, cx, 0});
		ArrayList<int[]> eatList = new ArrayList<>();
		check[cy][cx] = true;
		arr[cy][cx] = 0;
		while(!que.isEmpty()) { //BFS
			int[] cur = que.poll();
			for(int i = 0; i < 4; i++) {
				int ny = cur[0] + dy[i];
				int nx = cur[1] + dx[i];
				if(0 <= ny && ny < N && 0 <= nx && nx < N && !check[ny][nx]) {
					if(arr[ny][nx] == 0 || arr[ny][nx] == size) { //빈 공간이거나 크기가 같은 물고기가 있는 경우
						check[ny][nx] = true;
						que.offer(new int[] {ny, nx, cur[2] + 1});
					}else if(arr[ny][nx] < size) { //먹을 수 있는 경우 List에 추가
						eatList.add(new int[] {ny, nx, cur[2] + 1});
					}
				}
			}
		}
		if(eatList.size() == 0) {
			return false;
		}
		eatList.sort((o1, o2) -> { // 거리, y, x 순으로 정렬
			if(o1[2] == o2[2]) {
				if(o1[0] == o2[0]) {
					return o1[1] - o2[1];
				}
				return o1[0] - o2[0];
			}
			return o1[2] - o2[2];
		});
		int[] eat = eatList.get(0);
		arr[eat[0]][eat[1]] = 9;
		cy = eat[0];
		cx = eat[1];
		time += eat[2];
		return true;
	}
}