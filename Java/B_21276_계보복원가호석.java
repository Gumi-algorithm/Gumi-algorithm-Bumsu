import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class B_21276_계보복원가호석 {

	static int N, K;
	static int[] parents, child; // 부모 수, 자식 수
	static boolean[][] arr;
	static String[] names; // 이름 정렬용
	static HashMap<Integer, ArrayList<Integer>> list; // 각각 자식 index번호
	static HashMap<String, Integer> index; // 이름 : 인덱스
	static HashMap<Integer, String> name; // 인덱스 : 이름

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		parents = new int[N];
		child = new int[N];
		arr = new boolean[N][N];
		names = new String[N];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			names[i] = st.nextToken();
		}
		Arrays.sort(names);
		list = new HashMap<>();
		index = new HashMap<>();
		name = new HashMap<>();
		for (int i = 0; i < N; i++) {
			list.put(i, new ArrayList<>());
			index.put(names[i], i);
			name.put(i, names[i]);
		}
		K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			String child = st.nextToken();
			String parent = st.nextToken();
			parents[index.get(child)] += 1;
			arr[index.get(child)][index.get(parent)] = true;
		}

		int root = 0;
		Queue<Integer> que = new LinkedList<>();
		for (int i = 0; i < N; i++) {
			if (parents[i] == 0) {
				root += 1;
				que.offer(i);
			}
		}
		sb.append(root + "\n");
		// 위상정렬
		while (!que.isEmpty()) {
			int current = que.poll();
			if (root > 0) {
				sb.append(name.get(current) + " ");
				root--;
			}
			for (int i = 0; i < N; i++) {
				if (arr[i][current]) { // 부모라면
					arr[i][current] = false;
					parents[i]--;
					if (parents[i] == 0) { // 부모 수가 0명
						child[current]++; // 자식 수 추가
						que.offer(i);
						list.get(current).add(i); // 자식으로 추가
					}
				}
			}
		}
		sb.append("\n");
		for (int i = 0; i < N; i++) {
			sb.append(names[i] + " " + child[i] + " ");
			for (Integer child : list.get(i)) { // 0에서 N으로 검색하므로 정렬되어있음
				sb.append(names[child] + " ");
			}
			sb.append("\n");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
}
