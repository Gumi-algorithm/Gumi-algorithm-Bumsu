import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_12869_뮤탈리스크 {
	
	static int N, res;
	static int[] HP;
	static int[][][] arr = new int[61][61][61];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		HP = new int[3];
		for(int i = 0; i < N; i++) {
			HP[i] = Integer.parseInt(st.nextToken());
		}
		res = Integer.MAX_VALUE;
		dfs(HP[0], HP[1], HP[2], 0);
		System.out.println(res);
	}
	
	static void dfs(int scv1, int scv2, int scv3, int cnt) {
		scv1 = scv1 > 0 ? scv1 : 0;
		scv2 = scv2 > 0 ? scv2 : 0;
		scv3 = scv3 > 0 ? scv3 : 0;

		// 모든 hp를 오름차순으로 정렬
		int[] hp = new int[]{scv1, scv2, scv3};
		Arrays.sort(hp);
		scv1 = hp[0]; scv2 = hp[1]; scv3 = hp[2];
		// 모든 hp가 0이면
		if(scv1 == 0 && scv2 == 0 && scv3 == 0) {
			res = Math.min(res, cnt);
			return ;
		}
		// 이미 hp조합으로 해봤고, 예전에 횟수보다 많으면 재귀하지 않음
		if(arr[scv1][scv2][scv3] != 0 && arr[scv1][scv2][scv3] <= cnt) return;
		
		arr[scv1][scv2][scv3] = cnt;
		// 모든 경우의 수
		dfs(scv1 - 9, scv2 - 1, scv3 - 3, cnt + 1);
		dfs(scv1 - 9, scv2 - 3, scv3 - 1, cnt + 1);
		dfs(scv1 - 3, scv2 - 1, scv3 - 9, cnt + 1);
		dfs(scv1 - 3, scv2 - 9, scv3 - 1, cnt + 1);
		dfs(scv1 - 1, scv2 - 3, scv3 - 9, cnt + 1);
		dfs(scv1 - 1, scv2 - 9, scv3 - 3, cnt + 1);
	}
}
