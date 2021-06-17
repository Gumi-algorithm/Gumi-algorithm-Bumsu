import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class B_20114_미아노트 {
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		Character[] ans = new Character[N];
		Arrays.fill(ans, '?');
		for(int i = 0; i < H; i++) {
			String str = br.readLine();
			for(int j = 0; j < N * W; j++) {
				if(str.charAt(j) != '?') {
					ans[j / W] = str.charAt(j);
				}
			}
		}
		for(int i = 0; i < N; i++) {
			sb.append(ans[i]);
		}
		System.out.println(sb);
	}
}
