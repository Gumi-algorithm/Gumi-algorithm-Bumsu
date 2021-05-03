import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_2877_4��7 {

	static int K;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		K = Integer.parseInt(br.readLine());
		int len = 0;
		for (int i = 1; i < 31; i++) { // �� ��° �ڸ����� �־�� �ϴ��� �˻�
			if (K < (1 << i) - 1) { // ��Ģ�� 2^n - 1���� ���� ��� n - 1�� ����
				len = i - 1;
				break;
			}
		}
		int start = (1 << len) - 1; // 4�θ� �̷���� ���ڴ� 2^len - 1��
		int n = K - start; // �������� K�� ����ŭ�� ���� �����ָ� ��
		for (int i = len - 1; i >= 0; i--) { //���̸�ŭ �˻�  
			if ((n & (1 << i)) == 0) { // ��Ʈ�� 0�̸� 4�� ǥ��
				sb.append(4);
			} else { // ��Ʈ�� 1�̸� 7�� ǥ��
				sb.append(7);
			}
		}
		System.out.println(sb);
	}
}
