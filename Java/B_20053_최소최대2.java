import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_20053_최소최대2 {
	
	static int N;
	
	public static void main(String[] args) throws IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		for(int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine(), " ");
			int num;
			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;
			for(int i = 0; i < N; i++) {
				num = Integer.parseInt(st.nextToken());
				min = Math.min(min, num);
				max = Math.max(max, num);
			}
			sb.append(min + " " + max + "\n");
			
		}
		sb.setLength(sb.length() - 1);
		System.out.println(sb);
	}
}
