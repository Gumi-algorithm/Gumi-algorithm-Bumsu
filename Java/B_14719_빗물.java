import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_14719_���� {

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
			int val = checkWall(i) - arr[i]; // �¿� �� ���� ���� �� - ���� �˻��ϴ� ��
			if (val > 0) { // ������ ���� ���� �� ����
				res += val;
			}
		}
		System.out.println(res);

	}

	static int checkWall(int x) {
		int left = 0, right = 0;
		for (int i = 0; i < x; i++) { // ���� ��
			left = Math.max(left, arr[i]); // ���� �� ���� ���� ��
		}
		for (int i = x + 1; i < W; i++) { // ������ ��
			right = Math.max(right, arr[i]); // ������ �� ���� ���� ��
		}
		return Math.min(left, right); // ���ʰ� ������ �� ���� ����
	}
}
