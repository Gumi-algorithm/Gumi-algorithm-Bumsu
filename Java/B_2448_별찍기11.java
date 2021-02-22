import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_2448_별찍기11 {

	static char[][] arr;
	static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		arr = new char[N][(2 * N) - 1];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < (2 * N) - 1; j++) {
				arr[i][j] = ' ';
			}
		}
		dfs(N, 0, N - 1);
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < arr[i].length; j++) {
				sb.append(arr[i][j]);
			}
			sb.append("\n");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
	
	static void dfs(int n, int y, int x) {
		if(n == 3) {
			arr[y][x] = '*';
			arr[y + 1][x - 1] = arr[y + 1][x + 1] = '*';
			arr[y + 2][x - 2] = arr[y + 2][x - 1] = arr[y + 2][x] =
					arr[y + 2][x + 1] = arr[y + 2][x + 2] = '*';
		}
		else {
			int ny = n / 2;
			dfs(ny, y, x);
			dfs(ny, y + ny, x - ny);
			dfs(ny, y + ny, x + ny);
		}
	}
}