import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SWEA수영장 {
	
	static int[] price, day;
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 1; t < T + 1; t++) {
			price = new int[4];
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < 4; i++) {
				price[i] = Integer.parseInt(st.nextToken());
			}
			day = new int[12];
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < 12; i++) {
				day[i] = Integer.parseInt(st.nextToken());
			}
			
			int[] dp = new int[13];
			for(int i = 1; i < 13; i++) {
				if(day[i - 1] == 0) {
					dp[i] = dp[i - 1];
				}else {
					dp[i] = Math.min(dp[i - 1] + price[1], dp[i - 1] + price[0] * day[i - 1]);
					if(i > 2) {
						dp[i] = Math.min(dp[i], dp[i - 3] + price[2]);
					}
				}
			}
			sb.append("#" + t + " " + Math.min(dp[12], price[3]) + "\n");
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
}
