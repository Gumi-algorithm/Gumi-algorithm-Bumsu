import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_16926_배열돌리기 {

	static int N, M, R;
	static int[][] arr;
	static int[] dy = {1, 0, -1, 0};
	static int[] dx = {0, 1, 0, -1};
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		for(int i = 0; i < R; i++) {
			moveArr();
		}
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				sb.append(arr[i][j] + " ");
			}
			sb.setLength(sb.length() - 1);
			sb.append("\n");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
	static void moveArr() {
		int cnt = 1;
		while(cnt <= Math.min(N, M) / 2) {
			int start = cnt - 1;
			int y = start, x = start;
			int trade = arr[start][start];
			int[] pattern = {N - 2 * start - 1, M - 2*start - 1,
							N - 2 * start - 1, M - 2*start - 1};
			for(int i = 0; i < 4; i++) {
				for(int j = 0; j < pattern[i]; j++) {
					y += dy[i];
					x += dx[i];
					int temp = arr[y][x];
					arr[y][x] = trade;
					trade = temp;
				}
			}
			cnt++;
		}
	}
}
