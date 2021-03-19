import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class B_20164_홀수홀릭호석 {

	static int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String num = br.readLine();
		getLength(num, 0);
		System.out.println(min + " " + max);
	}
	static void getLength(String num, int cnt) {
		int size = num.length();
		if(size == 1) {
			if(Integer.parseInt(num) % 2 == 1) cnt += 1;
			max = Math.max(max, cnt);
			min = Math.min(min, cnt);
		}
		if(size == 2) {
			cnt += two(num);
			max = Math.max(max, cnt);
			min = Math.min(min, cnt);
		}else {
			for(int i = 1; i < size - 1; i++) {
				for(int j = i + 1; j < size; j++) {
					int sum = Integer.parseInt(num.substring(0, i)) 
							+ Integer.parseInt(num.substring(i, j))
							+ Integer.parseInt(num.substring(j, size));
					getLength(Integer.toString(sum), cnt + count(num));
				}
			}
		}
		
	}
	static int count(String num) {
		int cnt = 0;
		for(int i = 0; i < num.length(); i++) {
			if((num.charAt(i) - '0') % 2 == 1) {
				cnt += 1;
			}
		}
		return cnt;
	}
	
	static int two(String num) {
		int cnt = 0, sum = 0;
		for(int i = 0; i < num.length(); i++) {
			sum += num.charAt(i) - '0';
			if((num.charAt(i) - '0') % 2 == 1) {
				cnt += 1;
			}
		}
		if(sum > 9) {
			cnt += two(Integer.toString(sum));
		}else {
			if(sum % 2 == 1) {
				cnt += 1;
			}
		}
		return cnt;
	}
}
