import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_9205_맥주마시면서걸어가기 {

	static class Position{
		int y, x;

		public Position(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
	static int N;
	static int[][] dp;
	static Position[] arr;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			arr = new Position[N + 2];
			dp = new int[N + 2][N + 2];
			for(int i = 0; i < N + 2; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				int y = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				arr[i] = new Position(y, x);
			}
			for(int i = 0; i < N + 2; i++) { // 모든 좌표 거리 구하기
				for(int j = 0; j < N + 2; j++) {
					dp[i][j] = Math.abs(arr[i].y - arr[j].y) + Math.abs(arr[i].x - arr[j].x);
				}
			}
			for(int i = 0; i < N + 2; i ++) { // 플로이드 -와샬
				for(int j = 0; j < N + 2; j++) {
					for(int k = 0; k < N + 2; k++) {
						dp[j][k] = Math.min(dp[j][i] + dp[i][k], dp[j][k]);
					}
				}
			}
			boolean[] v = new boolean[N + 2];
			Queue<Integer> que = new LinkedList<>();
			v[0] = true;
			que.offer(0);
			while(!que.isEmpty()) { // BFS 시작
				int now = que.poll();
				
				if(now == N + 1) { // 맨 끝에 도착하면 종료
					break;
				}
				for(int i = 0; i < N + 2; i++) {
					if(!v[i] && dp[now][i] <= 1000) { // 거리가 1000 이하인 곳만 방문
						v[i] = true;
						que.offer(i);
					}
				}
			}
			if(v[N + 1]) { // 맨 끝이 true이면 도착
				sb.append("happy\n");
			}else { // 아니면 도착 못함
				sb.append("sad\n");
			}
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
}
