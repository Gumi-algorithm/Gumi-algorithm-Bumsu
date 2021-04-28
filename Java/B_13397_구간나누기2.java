import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_13397_����������2 {

	static int N, M;
	static int[] arr;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine(), " ");
		arr = new int[N];
		int start = 0, end = 0;
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			end = Math.max(end, arr[i]);
		}
		int res = 0;
		while (start <= end) { // �̺�Ž��
			int mid = (start + end) / 2;
			if (isTrue(mid)) { // �����ϸ� ���ڸ� �ٿ���
				res = mid; 
				end = mid - 1;
			} else { // �Ұ����ϸ� ���ڸ� �ø�
				start = mid + 1; 
			}
		}
		System.out.println(res);
	}

	static boolean isTrue(int mid) {
		int cnt = 1, min = Integer.MAX_VALUE, max = 0;
		for (int i = 0; i < N; i++) {
			min = Math.min(min, arr[i]); // ���� �ּڰ�
			max = Math.max(max, arr[i]); // ���� �ִ�
			if (max - min > mid) { // �־��� ������ ������
				cnt += 1; // ����  1�߰�
				min = arr[i]; // min ���� arr[i]�� ����
				max = arr[i]; // max ���� arr[i]�� ����
			}
		}
		return cnt <= M; // ������ M�� ���ϸ� true �ƴϸ� false
	}
}
