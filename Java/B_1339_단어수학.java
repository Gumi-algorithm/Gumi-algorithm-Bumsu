import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class B_1339_단어수학 {
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[] alphabet = new int[26];
		int sum = 0;
		int N = Integer.parseInt(br.readLine());
		for(int i = 0; i < N; i++) {
			String data = br.readLine();
			int num = 1;
			for(int j = 0; j < data.length() - 1; j++) {
				num *= 10;
			}
			for(int j = 0; j < data.length(); j++) {
				alphabet[data.charAt(j) -'A'] += num;
				num /= 10;
			}
		}
		Arrays.sort(alphabet);
		int minus = 9;
		for(int i = 25; i > 15; i--) {
			sum += alphabet[i] * minus--;
		}
		System.out.println(sum);
	}
}
