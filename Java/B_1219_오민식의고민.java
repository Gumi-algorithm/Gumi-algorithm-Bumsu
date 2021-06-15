import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_1219_���ν��ǰ�� {
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

		// �ִ븦 �̱� ������ �ּҷ� ����
		res = new long[N];
		Arrays.fill(res, Long.MIN_VALUE);

		// ��� ���� ���� ����
		trans = new Position[M];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			trans[i] = new Position(s, e, v);
		}
		// ���ÿ� ������ ������ ���� �� ����
		st = new StringTokenizer(br.readLine(), " ");
		city = new long[N];
		for (int i = 0; i < N; i++) {
			city[i] = Integer.parseInt(st.nextToken());
		}
		bellmanFord();
		// ������ ���� ����
		if (res[E] == Long.MIN_VALUE) {
			sb.append("gg");
			// �����ϰ� ����Ŭ�� ����
		} else if (res[E] == Long.MAX_VALUE) {
			sb.append("Gee");
			// �����ϰ� ����Ŭ�� ����
		} else {
			sb.append(res[E]);
		}
		System.out.println(sb);
	}

	public static void bellmanFord() {
		// ������� ����� �� ����
		res[S] = city[S];

		// �������尡 N - 1������ �ִܰŸ� �����ϴ� �˰����ε�
		// N�� ������ N - 1�� ���Ŀ��� ���� ������ �Ǹ� ����Ŭ�� �����ϴ� ��
		// Relaxation
		for (int i = 0; i <= N + 100; i++) {
			for (int j = 0; j < M; j++) {

				int s = trans[j].start;
				int e = trans[j].end;
				int v = trans[j].value;

				// ���� ������ ���� �������� ���
				if (res[s] == Long.MIN_VALUE) {
					continue;
					// �̹� ����Ŭ�� ��� �þ�� ���̸� �������� ������
				} else if (res[s] == Long.MAX_VALUE) {
					res[e] = Long.MAX_VALUE;
					// ���� ������ �� ���� ���� �� �� ������ ����
				} else if (res[e] < res[s] - v + city[e]) {
					res[e] = res[s] - v + city[e];

					// N - 1������ ����Ǿ�� �ϴµ� N - 1���� ���� ���� ���������� ����
					if (i >= N - 1) {
						res[e] = Long.MAX_VALUE;
					}
				}
			}
		}
	}
}
