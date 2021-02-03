import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B_15810_풍선공장 {

	public static void main(String[] args) throws IOException  {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] staff = new int[N];
		long min = 0;
		long mid = 0;
		long max = 0;
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++) {
			staff[i] = Integer.parseInt(st.nextToken());
			max = Math.max(staff[i], max);
		}
		max *= M;
		long time = max;
		while(min <= max) {
			long cnt = 0;
			mid = (max + min) / 2;
			for(int i = 0; i < N; i++) {
				cnt += mid / staff[i];
			}
			if(cnt >= M) {
				time = Math.min(time, mid);
				max = mid - 1;
			} else {
				min = mid + 1;
			}
		}
		System.out.println(time);
	}
}