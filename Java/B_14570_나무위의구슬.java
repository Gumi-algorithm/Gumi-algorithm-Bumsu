import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_14570_나무위의구슬 {

	static class Node {
		int left, right;

		public Node(int left, int right) {
			this.left = left;
			this.right = right;
		}
	}

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		int N = Integer.parseInt(br.readLine());
		Node[] tree = new Node[N + 1];
		for (int i = 1; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			tree[i] = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		long K = Long.parseLong(br.readLine());

		int cur = 1;
		while (true) {
			if (tree[cur].left == -1 && tree[cur].right == -1) {
				break;
			} else if (tree[cur].left == -1) {
				cur = tree[cur].right;
			} else if (tree[cur].right == -1) {
				cur = tree[cur].left;
			} else if (K % 2 == 1) {
				cur = tree[cur].left;
				K = K / 2 + 1;
			} else {
				cur = tree[cur].right;
				K /= 2;
			}
		}
		System.out.println(cur);
	}
}
