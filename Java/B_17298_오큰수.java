import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class B_17298_오큰수 {

	static class Position {
		int idx, val;

		public Position(int idx, int val) {
			this.idx = idx;
			this.val = val;
		}
	}

	static int N;
	static int[] res;
	static Position[] arr;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		arr = new Position[N];
		res = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++) {
			arr[i] = new Position(i, Integer.parseInt(st.nextToken()));
		}
		Stack<Position> stack = new Stack<>();
		for (int i = 0; i < N; i++) {
			while (!stack.isEmpty() && stack.peek().val < arr[i].val) {
				res[stack.pop().idx] = arr[i].val;
			}
			stack.add(arr[i]);
		}
		for (int i = 0; i < N; i++) {
			if (res[i] == 0) {
				sb.append(-1 + " ");
			} else {
				sb.append(res[i] + " ");
			}
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
}
