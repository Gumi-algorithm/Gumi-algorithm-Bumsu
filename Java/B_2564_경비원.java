import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_2564_경비원 {

	static class Position {
		int y, x, d;

		public Position(int y, int x, int d) {
			this.y = y;
			this.x = x;
			this.d = d;
		}
	}

	static int W, H, N;
	static Position start;
	static Position[] arr;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(br.readLine());
		arr = new Position[N];
		for (int i = 0; i < N + 1; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int d = Integer.parseInt(st.nextToken());
			int y, x;
			if (d == 1) {
				y = H;
				x = Integer.parseInt(st.nextToken());
				d = 0;
			} else if (d == 2) {
				y = 0;
				x = Integer.parseInt(st.nextToken());
				d = 0;
			} else if (d == 3) {
				x = 0;
				y = H - Integer.parseInt(st.nextToken());
				d = 1;
			} else {
				x = W;
				y = H - Integer.parseInt(st.nextToken());
				d = 1;
			}
			if (i < N) {
				arr[i] = new Position(y, x, d);
			} else {
				start = new Position(y, x, d);
			}
		}
		int res = 0;
		for (int i = 0; i < N; i++) {
			if (arr[i].d != start.d) {
				res += Math.abs(arr[i].y - start.y) + Math.abs(arr[i].x - start.x);
			} else {
				if (start.d == 0) {
					if (start.y == arr[i].y) {
						res += Math.abs(arr[i].x - start.x);
					} else {
						res += H;
						res += Math.min(arr[i].x + start.x, 2 * W - arr[i].x - start.x);
					}
				} else {
					if (start.x == arr[i].x) {
						res += Math.abs(arr[i].y - start.y);
					} else {
						res += W;
						res += Math.min(arr[i].y + start.y, 2 * H - arr[i].y - start.y);
					}
				}
			}
		}
		System.out.println(res);
	}
}
