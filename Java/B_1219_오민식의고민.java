import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_1219_오민식의고민 {
	//https://steady-coding.tistory.com/93
	static class Position {
		int start, end, value;

		public Position(int start, int end, int value) {
			this.start = start;
			this.end = end;
			this.value = value;
		}
	}

	static int N, S, E, M;
	static long[] city, res;
	static Position[] trans;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		S = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		// 최대를 뽑기 때문에 최소로 저장
		res = new long[N];
		Arrays.fill(res, Long.MIN_VALUE);

		// 모든 교통 수단 저장
		trans = new Position[M];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			trans[i] = new Position(s, e, v);
		}
		// 도시에 도착할 때마다 버는 값 저장
		st = new StringTokenizer(br.readLine(), " ");
		city = new long[N];
		for (int i = 0; i < N; i++) {
			city[i] = Integer.parseInt(st.nextToken());
		}
		bellmanFord();
		// 도착을 하지 못함
		if (res[E] == Long.MIN_VALUE) {
			sb.append("gg");
			// 도착하고 사이클이 존재
		} else if (res[E] == Long.MAX_VALUE) {
			sb.append("Gee");
			// 도착하고 사이클이 없음
		} else {
			sb.append(res[E]);
		}
		System.out.println(sb);
	}

	public static void bellmanFord() {
		// 출발지에 출발지 돈 저장
		res[S] = city[S];

		// 벨만포드가 N - 1번으로 최단거리 도출하는 알고리즘인데
		// N번 돌려서 N - 1번 이후에도 값이 갱신이 되면 사이클이 존재하는 것
		// Relaxation
		for (int i = 0; i <= N + 100; i++) {
			for (int j = 0; j < M; j++) {

				int s = trans[j].start;
				int e = trans[j].end;
				int v = trans[j].value;

				// 아직 가보지 못한 도착지인 경우
				if (res[s] == Long.MIN_VALUE) {
					continue;
					// 이미 사이클로 계속 늘어나는 곳이면 도착지도 무제한
				} else if (res[s] == Long.MAX_VALUE) {
					res[e] = Long.MAX_VALUE;
					// 현재 돈보다 더 많은 돈을 벌 수 있으면 갱신
				} else if (res[e] < res[s] - v + city[e]) {
					res[e] = res[s] - v + city[e];

					// N - 1번으로 종료되어야 하는데 N - 1보다 많은 경우는 무제한으로 갱신
					if (i >= N - 1) {
						res[e] = Long.MAX_VALUE;
					}
				}
			}
		}
	}
}
