import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_14225_�κм������� {

	static int N;
	static int[] arr;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		N = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		arr = new int[N];
		int total = 0;
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			total += arr[i];
		}
		boolean[] flag = new boolean[total + 2];
		for (int i = 1; i < (1 << N); i++) { // �� �� �ִ� ��� �κ� ����
			int val = 0;
			for (int j = 0; j < N; j++) {
				if ((i & (1 << j)) != 0) { // 1�̸� 
					val += arr[j]; // ���� ����
				}
			}
			flag[val] = true; // �� �� �ִ� �κм����� ���� true
		}
		int res = 0;
		for (int i = 1; i < total + 2; i++) { // 1���� �˻��ؼ� false�̸� �� ����
			if (!flag[i]) { 
				res = i;
				break;
			}
		}
		System.out.println(res);
	}
}
