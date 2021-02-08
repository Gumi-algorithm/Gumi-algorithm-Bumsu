import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_10972_다음순열 {

	static int N;
	static int[] next;
	static int[] number;
	static boolean[] check;
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		number = new int[N];
		next = new int[N];
		check = new boolean[N + 1];
		int index = -1;
		for(int i = 0; i < N; i++) {
			number[i] = Integer.parseInt(st.nextToken());
		}
		for(int i = N - 1; i > 0; i--) {
			if(number[i] > number[i - 1]) {
				index = i - 1;
				break;
			}
		}
		if(index == -1) {
			sb.append(-1);
		}else {
			Swap(Division(index), index);
			int i = 1;
			while(index + i <= N - i) {
				Swap(index + i, N - i);
				i++;
			}
			for(i = 0; i < N; i++) {
				sb.append(number[i] + " ");
			}
			sb.setLength(sb.length() - 1);
		}
		System.out.println(sb);
	}
	static int Division(int index) {
		int change = 0;
		for(int i = N - 1; i > index; i--) {
			if(number[i] > number[index]) {
				change = i;
				break;
			}
		}
		return change;
	}
	static void Swap(int i, int j) {
		
		int temp = number[i];
		number[i] = number[j];
		number[j] = temp;
	}

}
