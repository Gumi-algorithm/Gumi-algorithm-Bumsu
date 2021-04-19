import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class B_7662_이중우선순위큐 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int N = Integer.parseInt(br.readLine());
			PriorityQueue<Long> minpq = new PriorityQueue<>(); // 최소 힙
			PriorityQueue<Long> maxpq = new PriorityQueue<>(); // 최대 힙
			HashMap<Long, Integer> map = new HashMap<>(); 
			int size = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine(), " ");
				if (st.nextToken().charAt(0) == 'I') {
					long num = Long.parseLong(st.nextToken());
					if (!map.containsKey(num)) //값의 생존 여부
						map.put(num, 1);
					else
						map.put(num, map.get(num) + 1);
					minpq.offer(num);
					maxpq.offer(-num);
					size += 1;
				} else {
					if (size != 0) {
						size -= 1;
						if (Integer.parseInt(st.nextToken()) == -1) {
							long num = minpq.peek();
							while (map.get(num) == 0) { // 죽은 애들은 다 뺌
								minpq.poll();
								num = minpq.peek();
							}
							map.put(num, map.get(num) - 1); //하나를 빼면 map에 - 1
							minpq.poll();

						} else {
							long num = -maxpq.peek();
							while (map.get(num) == 0) { // 죽은 애들은 다 뺌
								maxpq.poll();
								num = -maxpq.peek();
							}
							map.put(num, map.get(num) - 1); //하나를 빼면 map에 - 1
							maxpq.poll();
						}
					}
				}
			}
			long min = Long.MAX_VALUE, max = Long.MIN_VALUE;
			if (size == 0) {
				sb.append("EMPTY");
			} else {
				long num = minpq.peek();
				while (map.get(num) == 0) { // 죽은 애들은 다 뺌
					minpq.poll();
					num = minpq.peek();
				}
				num = -maxpq.peek();
				while (map.get(num) == 0) { // 죽은 애들은 다 뺌
					maxpq.poll();
					num = -maxpq.peek();
				}
				min = minpq.peek();
				max = -maxpq.peek();
				sb.append(max + " " + min);
			}
			sb.append("\n");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
}
