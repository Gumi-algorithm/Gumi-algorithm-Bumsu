import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_20119_클레어와물약 {

	static class Potion {
		ArrayList<Integer> list = new ArrayList<>();
	}

	static class Recipe {
		int result, cnt;
	}
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Potion[] p = new Potion[N + 1];
		Recipe[] r = new Recipe[M];
		for (int i = 0; i < N + 1; i++) {
			p[i] = new Potion();
		}
		for (int i = 0; i < M; i++) {
			r[i] = new Recipe();
			st = new StringTokenizer(br.readLine(), " ");
			r[i].cnt = Integer.parseInt(st.nextToken());
			for (int j = 0; j < r[i].cnt; j++) {
				p[Integer.parseInt(st.nextToken())].list.add(i);
			}
			r[i].result = Integer.parseInt(st.nextToken());
		}
		int L = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		boolean[] check = new boolean[N + 1];
		Queue<Integer> que = new LinkedList<>();
		for (int i = 0; i < L; i++) {
			int num = Integer.parseInt(st.nextToken());
			check[num] = true;
			que.offer(num);
		}

		while (!que.isEmpty()) {
			int now = que.poll();
			for (int i = 0; i < p[now].list.size(); i++) {
				r[p[now].list.get(i)].cnt -= 1;
				if (r[p[now].list.get(i)].cnt == 0 && !check[r[p[now].list.get(i)].result]) {
					que.offer(r[p[now].list.get(i)].result);
					check[r[p[now].list.get(i)].result] = true;
					L += 1;
				}
			}
		}

		sb.append(L + "\n");
		for (int i = 1; i < N + 1; i++) {
			if (check[i]) {
				sb.append(i + " ");
			}
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
}
