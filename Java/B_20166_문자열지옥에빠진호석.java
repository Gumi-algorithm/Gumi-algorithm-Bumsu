import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class B_20166_문자열지옥에빠진호석 {

	static int N, M, K, cnt, len;
	static String[] target;
	static char[][] arr;
	static int[] dy = {-1, -1, -1, 0, 1, 1, 1, 0}, dx = {1, 0, -1, -1, -1, 0, 1, 1};
	static HashMap<String, Integer> map = new HashMap<>();
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		arr = new char[N][M];
		for(int i = 0; i < N; i++) {
			String data = br.readLine();
			for(int j = 0; j < M; j++) {
				arr[i][j] = data.charAt(j);
			}
		}
		target = new String[K];
		for(int i = 0; i < K;i ++) {
			cnt = 0;
			target[i] = br.readLine();
			len = Math.max(len, target[i].length());
		}
		for(int j = 0; j < N; j ++) {
			for(int k = 0; k < M; k++) {
				dfs(1, j, k, Character.toString(arr[j][k]));
			}
		}
		for(String s : target) {
			if(map.containsKey(s)) {
				sb.append(map.get(s) + "\n");
			}else {
				sb.append(0 + "\n");
			}
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
	
	static void dfs(int index, int y, int x, String str) {
		if(map.containsKey(str)) {
			map.put(str, map.get(str) + 1);
		}else {
			map.put(str, 1);
		}
		if(index == len) {
			return;
		}
	
		for(int i = 0; i < 8; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];
			if(ny < 0) {
				ny = N - 1;
			}
			if(nx < 0) {
				nx = M - 1;
			}
			if(ny == N) {
				ny = 0;
			}
			if(nx == M) {
				nx = 0;
			}
			dfs(index + 1, ny, nx, str + Character.toString(arr[ny][nx]));
		}
	}
	
}
