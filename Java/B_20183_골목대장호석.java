import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B_20183_골목대장호석 {

	static class Position implements Comparable<Position>{
		int to;
		long w;

		public Position(int to, long w) {
			this.to = to;
			this.w = w;
		}

		@Override
		public int compareTo(Position o) {
			return Long.compare(this.w, o.w);
		}
	}
	
	static int N, M, A, B;
	static long C, start, end, res;
	static ArrayList<Position>[] list;
	
	@SuppressWarnings("unchecked")
	public static void main(String[] args) throws IOException {
		// 이분탐색 + 다익스트라
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		C = Long.parseLong(st.nextToken());
		
		list = new ArrayList[N + 1];
		for(int i = 0; i < N + 1; i++) {
			list[i] = new ArrayList<>();
		}
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			long w = Long.parseLong(st.nextToken());
			end = Math.max(end, w);
			list[from].add(new Position(to, w));
			list[to].add(new Position(from, w));
		}
		start = 1;
		res = Long.MAX_VALUE;
		while(start <= end) { // 이분 탐색으로 가능한 간격 구하기
			long mid = (start + end) / 2;
			if(dijkstra(mid)) { // 다익스트라
				res = Math.min(res, mid); // 가능 할 때 가장 작은 값 저장
				end = mid - 1;
			}else {
				start = mid + 1;
			}
		}
		System.out.println(res == Long.MAX_VALUE ? -1 : res);
	}
	
	static boolean dijkstra(long interval) { 
		long[] value = new long[N + 1];
		Arrays.fill(value, Long.MAX_VALUE);
		PriorityQueue<Position> pq = new PriorityQueue<>();
		pq.offer(new Position(A, 0));
		
		while(!pq.isEmpty()) {
			Position now = pq.poll();
			
			if(now.w > value[now.to]) continue;
			
			if(now.to == B) break;
				
			for(int i = 0; i < list[now.to].size(); i++) {
				Position next = list[now.to].get(i);
				long cost = next.w + now.w; 
				if(next.w <= interval && value[next.to] > cost) { // 주어진 간격보다 작고, 더 작은 비용일 경우 pq에 추가
					value[next.to] = cost;
					pq.offer(new Position(next.to, cost));
				}
			}
		}
		return value[B] <= C ? true : false; // 최단 경로로 도착 했을 때 주어진 돈으로 갈 수 있으면 true, 아니면 false
	}
}
