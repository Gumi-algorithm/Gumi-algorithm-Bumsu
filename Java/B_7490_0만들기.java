import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_7490_0����� {

	static int N;
	static int[] arr;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N];
			for (int i = 0; i < N; i++) {
				arr[i] = i + 1;
			}
			dfs(1, 0, 1, "1", 1);
			sb.append("\n");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
	// ���ʴ�� Ƚ��, ������ (+, -) �˻��, ����� ��, ǥ����, �� ���� ��� �� ��
	static void dfs(int index, int pre, int result, String expr, int val) { 
		if (index == N) {
			if (result == 0) { // ���� 0 �� ���
				sb.append(expr + "\n");
			}
			return;
		}
		// �� �������� ���ڸ� �̾� ���� ����� ��
		int num = val * 10 + arr[index];
		// ���� ���� �� ���� ������������ 9�� ���ϰ� ���ο� ���� 1�� ���ؾ� ��
		val *= 9; 
		val += arr[index];
		// �� ������ ���
		if (pre == 0) { // �� �����ε� �� ���� + �� ��� 
			dfs(index + 1, 0, result + val, expr + " " + arr[index], num);
		} else {// �� �����ε� �� ���� - �� ��� 
			dfs(index + 1, 1, result - val, expr + " " + arr[index], num);
		}
		dfs(index + 1, 0, result + arr[index], expr + "+" + arr[index], arr[index]); // ���ϱ�
		dfs(index + 1, 1, result - arr[index], expr + "-" + arr[index], arr[index]); // ����
	}
}
