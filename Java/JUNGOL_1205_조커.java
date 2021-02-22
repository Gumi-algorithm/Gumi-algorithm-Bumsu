import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JUNGOL_1205_조커 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine(), " ");
		int[] card = new int[n];
		int chance = 0;
		for(int i = 0; i < n; i++) {
			card[i] = Integer.parseInt(st.nextToken());
			if(card[i] == 0) chance += 1;
		}
		int max = chance + 1; //최소로 이을 수 있는 수
		Arrays.sort(card);
		if(chance == n) {
			max = n;
		}
		else {
			for(int i = chance; i < n; i++) {
				int same = 0;
				for(int j = i + 1; j < n; j++) {
					if(card[j - 1] == card[j]) {
						same += 1; // 같은 숫자가 있는 경우 가진 숫자를 더 빼줘야 함
						continue;
					}
					int value = card[j] - card[i] - 1; // 필요한 숫자 수
					int cnt = j - i - 1 - same; // 가진 숫자 갯수
					if(value - cnt <= chance) {
						max = Math.max(max, card[j] - card[i] + 1 - value + cnt + chance);
					}
				}
			}
		}
		System.out.println(max);
		
	}
}