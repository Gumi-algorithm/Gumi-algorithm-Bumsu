import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_14719_빗물 {

	static int H, W;
	static int[] arr;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());

		arr = new int[W];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < W; i++) {
			int h = Integer.parseInt(st.nextToken());
			arr[i] = h;
		}
		int res = 0;
		for (int i = 1; i < W - 1; i++) {
			int val = checkWall(i) - arr[i]; // 좌우 중 가장 낮은 벽 - 현재 검사하는 벽
			if (val > 0) { // 음수면 물을 담을 수 없음
				res += val;
			}
		}
		System.out.println(res);

	}

	static int checkWall(int x) {
		int left = 0, right = 0;
		for (int i = 0; i < x; i++) { // 왼쪽 벽
			left = Math.max(left, arr[i]); // 왼쪽 중 가장 높은 벽
		}
		for (int i = x + 1; i < W; i++) { // 오른쪽 벽
			right = Math.max(right, arr[i]); // 오른쪽 중 가장 높은 벽
		}
		return Math.min(left, right); // 왼쪽과 오른쪽 중 낮은 높이
	}
}
