import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class B_14500_테트로미노 {

	static int N, M, max = Integer.MIN_VALUE;
	static int[] dy = {-1, 1, 0, 0}, dx = {0, 0, -1, 1};
	static ArrayList<int[]> list = new ArrayList<>();
	static int[][] arr;
	
	public static void main(String[] args) throws IOException {
		// 그냥 모든 모양 다 해버렸음
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		list.add(new int[] {3, 1, 2}); //사각형
		list.add(new int[] {3, 3, 3}); //일자오른쪽
		list.add(new int[] {1, 1, 1}); //일자 아래로
		// ㄴ모양
		list.add(new int[] {0, 0, 2});
		list.add(new int[] {0, 0, 3});
		list.add(new int[] {1, 1, 2});
		list.add(new int[] {1, 1, 3});
		list.add(new int[] {2, 2, 0});
		list.add(new int[] {2, 2, 1});
		list.add(new int[] {3, 3, 0});
		list.add(new int[] {3, 3, 1});
		// 2모양
		list.add(new int[] {0, 2, 0});
		list.add(new int[] {0, 3, 0});
		list.add(new int[] {1, 2, 1});
		list.add(new int[] {1, 3, 1});
		list.add(new int[] {2, 0, 2});
		list.add(new int[] {2, 1, 2});
		list.add(new int[] {3, 0, 3});
		list.add(new int[] {3, 1, 3});
		//ㅗ모양
		list.add(new int[] {0, 1, 2});
		list.add(new int[] {0, 1, 3});
		list.add(new int[] {0, 2, 3});
		list.add(new int[] {1, 2, 3});
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
					getMax(i, j);
			}
		}
		System.out.println(max);
	}
	static void getMax(int y, int x) {
		for(int i = 0; i < list.size() - 4; i++) {
			int cnt = 0;
			int area = arr[y][x];
			int ny = y;
			int nx = x;
			for(int j = 0; j < 3; j++) {
				ny += dy[list.get(i)[j]];
				nx += dx[list.get(i)[j]];
				if(0 <= ny && ny < N && 0 <= nx && nx < M) {
					area += arr[ny][nx];
					cnt += 1;
				}else {
					break;
				}
			}
			if(cnt == 3) {
				max = Math.max(area, max);
			}
		}
		for(int i = list.size() - 4; i < list.size(); i++) {
			int cnt = 0;
			int area = arr[y][x];
			for(int j = 0; j < 3; j++) {
				int ny = y + dy[list.get(i)[j]];
				int nx = x + dx[list.get(i)[j]];
				if(0 <= ny && ny < N && 0 <= nx && nx < M) {
					area += arr[ny][nx];
					cnt += 1;
				}else {
					break;
				}
			}
			if(cnt == 3) {
				max = Math.max(area, max);
			}
		}
	}
}