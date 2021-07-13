import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B_14595_동방프로젝트 {

	static class Data implements Comparable<Data> {
		int start, end;

		public Data(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Data o) {
			if (this.start == o.start) {
				Integer.compare(this.end, o.end);
			}
			return Integer.compare(this.start, o.start);
		}
	}

	static int N, M;
	static int[] p;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		p = new int[N + 1];

		PriorityQueue<Data> pq = new PriorityQueue<>();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			pq.offer(new Data(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		int cnt = N, end = 0;
		while (!pq.isEmpty()) {
			Data now = pq.poll();
			if (now.start > end) { // 이미 부순 벽보다 시작점이 큰 경우
				end = now.start; // 시작점부터 부숴야 됨
			}
			if(end < now.end) { // 끝이 지금 부순 숫자보다 큰 경우
				cnt -= now.end - end; // 부순 횟수만큼 cnt 빼줌
				end = now.end; // 끝을 변경
			}
		}
		System.out.println(cnt);
	}

}
