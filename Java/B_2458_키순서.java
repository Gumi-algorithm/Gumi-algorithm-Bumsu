import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_2458_키순서 {

	static int N, M;
	static boolean[][] arr, arr1;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		arr = new boolean[N + 1][N + 1]; // 나보다 키 큰 사람
		arr1 = new boolean[N + 1][N + 1]; // 나보다 키 작은사람
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			arr1[B][A] = true;
			arr[A][B] = true;
		}
		for (int i = 1; i < N + 1; i++) {
			for (int j = 1; j < N + 1; j++) {
				for (int k = 1; k < N + 1; k++) {
					arr[j][k] = (arr[j][k] || (arr[j][i] && arr[i][k])); // 자신보다 키 큰 사람연결 확인 
					arr1[j][k] = (arr1[j][k] || (arr1[j][i] && arr1[i][k])); // 자신보다 작은 사람 연결 확인
				}
			}
		}
		int res = 0;
		for (int i = 1; i < N + 1; i++) {
			int cnt = 0;
			for (int j = 1; j < N + 1; j++) {
				if (arr[i][j] || arr1[i][j]) { // 작던 크던 연결이 되있어야 등수 알 수 있음.
					cnt += 1;
				}
			}
			if (cnt == N - 1) { // 자기 자신을 뺀 모두랑 연결
				res += 1; //최종 값 더해줌
			}
		}
		System.out.println(res);

	}
}
