import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class B_1208_부분수열의합2 {

	static int N, S;
	static int[] groupA, groupB;
	static HashMap<Long, Long> A, B;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		groupA = new int[N / 2];
		groupB = new int[N - (N / 2)];
		A = new HashMap<>();
		B = new HashMap<>();
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			if (i < N / 2) {
				groupA[i] = Integer.parseInt(st.nextToken());
			} else {
				groupB[i - (N / 2)] = Integer.parseInt(st.nextToken());
			}
		}
		dfs(groupA, true);
		dfs(groupB, false);
		long res = 0; //총 갯수가 int형을 넘어감
		for (Long i : A.keySet()) {
			if (B.containsKey(S - i)) { // S - i값이 존재하면
				res += A.get(i) * B.get(S - i); // 값을 더해줌
			}
		}
		if (S == 0) {
			res -= 1;
		}
		System.out.println(res);
	}

	static void dfs(int[] group, boolean flag) {
		for (int i = 0; i < 1 << group.length; i++) {
			long cnt = 0;
			for (int j = 0; j < group.length; j++) {
				if ((i & 1 << j) > 0) {
					cnt += group[j];
				}
			}
			if (flag) { // 모든 합을 A, B에 각각 저장
				if (A.containsKey(cnt)) {
					A.put(cnt, A.get(cnt) + 1);
				} else {
					A.put(cnt, 1L);
				}
			} else {
				if (B.containsKey(cnt)) {
					B.put(cnt, B.get(cnt) + 1);
				} else {
					B.put(cnt, 1L);
				}
			}
		}
	}
}
