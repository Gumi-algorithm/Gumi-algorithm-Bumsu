import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_2668_숫자고르기 {
	
	static int N, R, count;
	static int[][] board;
	static boolean[] result;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		N = Integer.parseInt(br.readLine());
		result = new boolean[N + 1];
		board = new int[2][N];
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			board[0][i] = i + 1;
			board[1][i] = Integer.parseInt(br.readLine());
		}
		for(int i = 1; i < N + 1; i++) {
			Queue<Integer> queue = new LinkedList<>();
			boolean[] check = new boolean[N + 1];
			check[i] = true;
			queue.offer(board[1][i - 1]);
			while(!queue.isEmpty()) {
				int next = queue.poll();
				if(next == i) {
					check[0] = true;
					break;
				}
				if(!check[next]) {
					check[next] = true;
					queue.offer(board[1][next - 1]);
				}
			}
			if(check[0]) {
				for(int j = 1; j < N + 1; j++) {
					if(check[j] && !result[j]) {
						cnt += 1;
						result[j] = true;
					}
				}
			}else {
				check[0] = false;
			}
		}
		sb.append(cnt);
		for(int i = 1; i < N + 1; i++) {
			if(result[i]) {
				sb.append("\n" + i);
			}
		}
		System.out.println(sb);
	}
}